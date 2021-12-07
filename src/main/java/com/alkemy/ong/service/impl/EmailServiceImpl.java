
package com.alkemy.ong.service.impl;


import com.alkemy.ong.exception.EmailException;
import com.alkemy.ong.exception.EmailExistException;
import com.alkemy.ong.service.IEmailService;
import com.alkemy.ong.service.IWelcomeTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
public class EmailServiceImpl implements IEmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private IWelcomeTemplateService welcomeTemplateService;

    @Override
    public void sendEmail(String to, String body, String subject) throws EmailException {

        if (to.isEmpty()) {
            throw new EmailException();
        }
        if (body.isEmpty()) {
            throw new EmailException();
        }
        if (subject.isEmpty()) {
            throw new EmailException();
        } else {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom("oalkemy@gmail.com");
            simpleMailMessage.setTo(to);
            simpleMailMessage.setSubject(subject);
            simpleMailMessage.setText(body);
            javaMailSender.send(simpleMailMessage);
            System.out.println("Sent Email...");
        }

    }

    @Override
    public void sendWelcomeEmail(String to, String name, String surname) throws EmailExistException {


        String subject = welcomeTemplateService.getSubject();

        try {
            String template = welcomeTemplateService.setTemplate(name, surname);

            this.sendEmail(to, template, subject);

        } catch (EmailExistException | EmailException e) {
            e.printStackTrace();

        }
    }
}

