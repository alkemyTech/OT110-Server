package com.alkemy.ong.service;

import com.alkemy.ong.exception.EmailExistException;

public interface IWelcomeTemplateService {

	String setTemplate(String name, String surname) throws EmailExistException;

	String getSubject();

}
