package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.ContactRequestDto;
import com.alkemy.ong.model.Contact;
import com.alkemy.ong.repository.ContactRepository;
import lombok.AllArgsConstructor;

import com.alkemy.ong.service.IContactService;
import com.alkemy.ong.service.IEmailService;

import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ContactServiceImpl implements IContactService{

	private final ContactRepository contactRepository;
	private final IEmailService emailService;

	@Override
	public Contact addContact(ContactRequestDto contactRequestDto) throws Exception {
		Contact contact = new Contact();
		contact.setName(contactRequestDto.getName());
		contact.setEmail(contactRequestDto.getEmail());
		contact.setMessage(contactRequestDto.getMessage());
		contact.setPhone(contact.getPhone());
		Contact contactSaved = contactRepository.save(contact);
		emailService.sendWelcomeEmail(contactSaved.getEmail(), contactSaved.getName(), "");
		return contactSaved;


	}
	
}
