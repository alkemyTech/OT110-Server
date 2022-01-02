package com.alkemy.ong.integration.contact;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.isA;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.alkemy.ong.common.BaseContactTest;
import com.alkemy.ong.dto.ContactRequestDto;
import com.alkemy.ong.dto.ContactResponseDto;
import com.alkemy.ong.model.Contact;
import com.alkemy.ong.security.RoleEnum;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ContactCreateGeneralTest extends BaseContactTest {
	
	 private final String PATH = "/contacts";
	 private ContactRequestDto contactRequest = new ContactRequestDto();
	
	 @Before
	 public void setUp(){
	        contactRequest = contactRequest();
	 }
	 
    @Test
    public void ReturnUnauthorizedIfUserIsNotAdmin() {

        login(RoleEnum.USER.getRoleName());
        ResponseEntity<Object> response = testRestTemplate.exchange(createURLWithPort(PATH),
                HttpMethod.POST, new HttpEntity<>(contactRequest, headers), Object.class);
        assertEquals(response.getStatusCode(), HttpStatus.UNAUTHORIZED);
    }
    

    @Before
    public void AdminLogin() {
        login(RoleEnum.ADMIN.getRoleName());
    }
    
    @Test
    public void ReturnBadRequestIfAnyAttributeIsNull() {
    	
        contactRequest.setName("");
        contactRequest.setEmail("");
        contactRequest.setMessage("");
        
        ResponseEntity<ContactResponseDto> response = testRestTemplate.exchange(createURLWithPort(PATH),
                HttpMethod.POST, new HttpEntity<>(contactRequest, headers), ContactResponseDto.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
    
    @Test
    public void CreateContactSuccess() {

        Mockito.when(contactRepository.save(isA(Contact.class))).thenReturn(generateContact());

        ResponseEntity<Object> response =
                testRestTemplate.exchange(createURLWithPort(PATH), HttpMethod.POST, new HttpEntity<>(contactRequest, headers), Object.class);
        
        assertEquals(response.getStatusCode(), HttpStatus.CREATED);
    }
   
                 
    
}