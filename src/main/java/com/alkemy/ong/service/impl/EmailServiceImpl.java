package com.alkemy.ong.service.impl;

import com.alkemy.ong.exception.EmailException;
import com.alkemy.ong.exception.EmailExistException;
import com.alkemy.ong.service.IEmailService;
import com.alkemy.ong.service.IWelcomeTemplateService;
import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;


@Service
@AllArgsConstructor
public class EmailServiceImpl implements IEmailService {

    private final JavaMailSender javaMailSender;
    private final IWelcomeTemplateService welcomeTemplateService;

    private static final String ORGANIZATION_EMAIL = "oalkemy@gmail.com";
    private static final String ENCODING="utf-8";

    @Override
    @Async
    public void sendEmail(String to, String body, String subject) throws EmailException, MessagingException {

        if (to.isEmpty()) throw new EmailException();
        if (body.isEmpty()) throw new EmailException();

        if (subject.isEmpty()) {
            throw new EmailException();
        } else {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,ENCODING);
            helper.setText(body,true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setFrom(ORGANIZATION_EMAIL);
            javaMailSender.send(mimeMessage);
        }

    }

    @Override
    public void sendWelcomeEmail(String to, String name, String surname) throws EmailExistException {
        String subject = welcomeTemplateService.getSubject();

        try {
            String template = welcomeTemplateService.setTemplate(name, surname);

            sendEmail(to, template, subject);

        } catch (EmailExistException | EmailException | MessagingException e) {
            e.printStackTrace();

        }
    }
}

