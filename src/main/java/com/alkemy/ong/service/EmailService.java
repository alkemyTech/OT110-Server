
package com.alkemy.ong.service;


import com.alkemy.ong.exception.EmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;


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

}

