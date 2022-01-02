package com.alkemy.ong.integration.organization;

import com.alkemy.ong.model.Organization;
import com.alkemy.ong.security.RoleEnum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OrganizationListTest extends BaseOngTest{

    private final String PATH = "/organization/public";

    @Test
    public void ReturnForbiddenIfUserIsNotLogged() {
        ResponseEntity<Object> response = testRestTemplate.exchange(createURLWithPort(PATH), HttpMethod.GET,
                new HttpEntity<>(headers), Object.class);
        assertEquals( HttpStatus.FORBIDDEN, response.getStatusCode());
    }

    @Test
    public void ListOrganizationSuccess(){
        List<Organization> organization = generateListOrganization(1);
        when(organizationRepository.findAll()).thenReturn(organization);

        login(RoleEnum.USER.getRoleName());

        ResponseEntity<List> response = testRestTemplate.exchange(createURLWithPort(PATH),
                HttpMethod.GET,
                new HttpEntity<>(headers),
                List.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());


    }


}
