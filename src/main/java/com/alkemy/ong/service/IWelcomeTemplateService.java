package com.alkemy.ong.service;

import com.alkemy.ong.exception.EmailException;

public interface IWelcomeTemplateService {

	String setTemplate(String name, String surname) throws EmailException;

	String getSubject();

}
