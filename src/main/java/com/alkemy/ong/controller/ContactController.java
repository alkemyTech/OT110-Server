package com.alkemy.ong.controller;

import com.alkemy.ong.dto.ContactRequestDto;
import com.alkemy.ong.model.Contact;
import com.alkemy.ong.security.SecurityConstant;
import com.alkemy.ong.service.IContactService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/contacts")
@AllArgsConstructor
public class ContactController {

    private final IContactService contactService;

    @PostMapping
    @PreAuthorize(SecurityConstant.ADMIN)
    public ResponseEntity<Contact> addContact(@Valid @RequestBody ContactRequestDto contactRequest) throws Exception {
        return new ResponseEntity<>(contactService.addContact(contactRequest), HttpStatus.OK);
    }

}
