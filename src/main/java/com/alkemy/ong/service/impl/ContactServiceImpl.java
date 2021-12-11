package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.ContactRequestDto;
import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.model.Contact;
import com.alkemy.ong.repository.ContactRepository;
import lombok.AllArgsConstructor;

import com.alkemy.ong.service.IContactService;
import org.modelmapper.ModelMapper;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ContactServiceImpl implements IContactService{

	private final ContactRepository contactRepository;

	private final MessageSource messageSource;

	@Override
	public Contact addContact(ContactRequestDto contactRequestDto) throws Exception {
		Contact contact = new Contact();
		contact.setName(contactRequestDto.getName());
		contact.setEmail(contactRequestDto.getEmail());
		contact.setMessage(contactRequestDto.getMessage());
		contact.setPhone(contact.getPhone());

		return contactRepository.save(contact);


	}

	@Override
	public List<ContactRequestDto> getAll() {
		String contactListNotFound = messageSource.getMessage("contact.listEmpty",null, Locale.US);

		List<ContactRequestDto> listContact = contactRepository.findAll()
				.stream()
				.map(this::mapContactToContactRequestDto)
				.collect(Collectors.toList());
		if(listContact.isEmpty()){
			throw new NotFoundException(contactListNotFound);
		}
		return listContact;
	}

	private ContactRequestDto mapContactToContactRequestDto(Contact contact){
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(contact, ContactRequestDto.class);
	}

}
