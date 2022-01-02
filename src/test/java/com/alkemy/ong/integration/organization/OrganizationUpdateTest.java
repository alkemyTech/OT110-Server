package com.alkemy.ong.integration.organization;

import com.alkemy.ong.dto.OrganizationRequest;
import com.alkemy.ong.dto.OrganizationResponse;
import com.alkemy.ong.model.Organization;
import com.alkemy.ong.security.RoleEnum;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OrganizationUpdateTest extends BaseOngTest{

    private final Long ID = generateOrganization().getId();
    private final String PATH = "/organization/public";
    private OrganizationRequest organizationRequest = new OrganizationRequest();

    @Before
    public void setUp(){organizationRequest= generateOrganizationRequest(); }

    @Test
    public void ReturnUnauthorizedIfUserIsNotADMIN() {
        login(RoleEnum.USER.getRoleName());

        ResponseEntity<Object> response = testRestTemplate.exchange(createURLWithPort(PATH),
                HttpMethod.PATCH, new HttpEntity<>(organizationRequest,headers), Object.class);

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
    }

    @Before
    public void AdminLogin()  {login(RoleEnum.ADMIN.getRoleName());}

    @Test
    public void ReturnBadRequestIfAnyAttributeIsNull() {
        organizationRequest.setName("");
        organizationRequest.setImage("");
        organizationRequest.setEmail("");
        organizationRequest.setWelcomeText("");

        ResponseEntity<OrganizationResponse> response = testRestTemplate.exchange(createURLWithPort(PATH),
                HttpMethod.PATCH, new HttpEntity<>(organizationRequest, headers), OrganizationResponse.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void UpdateOrganizationSuccess(){
        Organization organizationMod = generateOrganization();

        organizationMod.setId(1L);
        organizationMod.setName("ONG Modificacion");
        organizationMod.setImage("https://somosmasImageNoticiaModified.jpg");
        organizationMod.setEmail("somosMas@somosmasmod.com");
        organizationMod.setWelcomeText("Bienvenidos Mod");

        when(organizationRepository.findById(ID)).thenReturn(Optional.of(generateOrganization()));

        when(organizationRepository.findAll()).thenReturn(generateListOrganization(1));

        when(organizationRepository.save(isA(Organization.class))).thenReturn(organizationMod);

        organizationRequest.setName("ONG Modificacion");

        ResponseEntity<OrganizationResponse> response = testRestTemplate.exchange(
                createURLWithPort(PATH), HttpMethod.PATCH, new HttpEntity<>(organizationRequest, headers), OrganizationResponse.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());

    }


}
