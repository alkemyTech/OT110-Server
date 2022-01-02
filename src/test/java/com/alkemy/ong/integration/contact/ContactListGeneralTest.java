package com.alkemy.ong.integration.contact;

import com.alkemy.ong.common.BaseContactTest;
import com.alkemy.ong.model.Contact;
import com.alkemy.ong.repository.ContactRepository;
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
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ContactListGeneralTest extends BaseContactTest {
	
    private final String PATH = "/contacts";
    //private ContactRepository contactRepository;
    
    @Test
    public void ReturnForbiddenIfUserIsNotLogged() {
        ResponseEntity<Object> response = testRestTemplate.exchange(createURLWithPort(PATH), HttpMethod.GET,
                new HttpEntity<>(headers), Object.class);
        assertEquals( HttpStatus.FORBIDDEN, response.getStatusCode());
    }
    
    @Test
    public void ReturnUnauthorizedIfUserIsNotAdmin() {
        login(RoleEnum.USER.getRoleName());
        ResponseEntity<Object> response = testRestTemplate.exchange(createURLWithPort(PATH), HttpMethod.GET,
                new HttpEntity<>(headers), Object.class);
        assertEquals(response.getStatusCode(), HttpStatus.UNAUTHORIZED);
    }

   
	@Test
    public void ListContactSuccess() {
       
		List<Contact> contacts =  generateContacts(3);
        when(contactRepository.findAll()).thenReturn(contacts);

        login(RoleEnum.ADMIN.getRoleName());

        ResponseEntity<Object> response = testRestTemplate.exchange(createURLWithPort(PATH), HttpMethod.GET,
        		new HttpEntity<>(headers), Object.class);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }
    
   
}
