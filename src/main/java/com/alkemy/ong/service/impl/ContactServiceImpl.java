package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.ContactRequestDto;
import com.alkemy.ong.dto.ContactResponseDto;
import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.model.Contact;
import com.alkemy.ong.repository.ContactRepository;
import lombok.AllArgsConstructor;

import com.alkemy.ong.service.IContactService;

import org.springframework.context.MessageSource;

import com.alkemy.ong.service.IEmailService;


import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


@Service
@AllArgsConstructor
public class ContactServiceImpl implements IContactService{

	private final ContactRepository contactRepository;
	private final IEmailService emailService;
	private final MessageSource messageSource;

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

	@Override
	public List<ContactResponseDto> getAll() {
		String contactListNotFound = messageSource.getMessage("contact.listEmpty", null, Locale.US);

		List<ContactResponseDto> contactResponseDto = new ArrayList<>();

		contactRepository.findAll().stream().forEach(contact -> {
			ContactResponseDto contactResponseDto1 = new ContactResponseDto();
			contactResponseDto1.setName(contact.getName());
			contactResponseDto1.setPhone(contact.getPhone());
			contactResponseDto1.setEmail(contact.getEmail());
			contactResponseDto1.setMessage(contact.getMessage());
			contactResponseDto.add(contactResponseDto1);
		});

		if (contactResponseDto.isEmpty()) {
			throw new NotFoundException(contactListNotFound);
		}

		return contactResponseDto;
	}

}
