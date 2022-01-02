package com.alkemy.ong.common;

import com.alkemy.ong.dto.ContactRequestDto;
import com.alkemy.ong.model.Contact;
import com.alkemy.ong.repository.ContactRepository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.test.mock.mockito.MockBean;

public class BaseContactTest extends BaseGeneralTest{
	
	@MockBean
    protected ContactRepository contactRepository;
	
    protected ContactRequestDto contactRequest() {
    	ContactRequestDto contactRequestDto = new ContactRequestDto();
    	contactRequestDto.setName("Carlos");
    	contactRequestDto.setEmail("Carlos@hotmail.com");
    	contactRequestDto.setMessage("mensaje de prueba");
        return contactRequestDto;
    }

    protected List<Contact> generateContacts(int count){
        List<Contact> contacts = new ArrayList<>(count);
        for(int i=1; i<=count;i++){
            contacts.add(generateContact());
        }
        return contacts;
       
    }
    
    protected Contact generateContact() {
        Contact contact = new Contact();
        contact.setId(1L);
        contact.setName("Marta");
        contact.setEmail("marta.sanchez@hotmail.com");
        contact.setMessage("mensaje de prueba");
        contact.setEnabled(Boolean.TRUE);
      
        return contact;
    }

}

