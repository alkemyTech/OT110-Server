package com.alkemy.ong.service.impl;

import com.alkemy.ong.exception.EmailExistException;
import com.alkemy.ong.service.IWelcomeTemplateService;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Service
public class WelcomeTemplateServiceImpl implements IWelcomeTemplateService{

    @Autowired
    private Configuration config;

    @Autowired
    private MessageSource messageSource;

    @Override
    public String setTemplate(String name, String surname) throws EmailExistException {
        Map<String, Object> model = new HashMap<>();

        model.put("name", name);
        model.put("surname", surname);

        try {

            Template t = config.getTemplate("email-template.ftl");

            return FreeMarkerTemplateUtils.processTemplateIntoString(t, model);

        }catch (TemplateException | IOException e){
            String messageError = messageSource.getMessage("welcomeMessageTemplate.failure", null, Locale.US);

            throw new EmailExistException(messageError);

        }
    }
    
    @Override
    public String getSubject(){

        return messageSource.getMessage("welcomeMessageTemplate.emailSubject",null, Locale.US);
    }


}