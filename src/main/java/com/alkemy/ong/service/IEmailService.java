package com.alkemy.ong.service;

import com.alkemy.ong.exception.EmailException;
import com.alkemy.ong.exception.EmailExistException;

public interface IEmailService {

    void sendEmail(String to, String body, String subject) throws EmailException;

    void sendWelcomeEmail(String to, String name, String surname) throws EmailExistException;

}
