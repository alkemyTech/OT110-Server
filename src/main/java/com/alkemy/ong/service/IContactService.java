package com.alkemy.ong.service;

import com.alkemy.ong.dto.ContactRequestDto;
import com.alkemy.ong.model.Contact;

import java.util.List;

public interface IContactService {
    Contact addContact(ContactRequestDto contactRequestDto) throws Exception;
    List<ContactRequestDto> getAll();
}
