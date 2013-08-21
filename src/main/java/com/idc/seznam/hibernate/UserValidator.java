package com.idc.seznam.hibernate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.idc.seznam.HomeController;


public class UserValidator implements Validator {

	private static final Logger logger = LoggerFactory
			.getLogger(HomeController.class);
	
	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		User newUser = (User) target;
		//String firstName = newUser.getFirstName(); 
      
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "a", "Cannot do without a first name!");
        
		
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "required.lastxName");
		
		//logger.info("Jsem se dostal pres validationUtility.rejectIfEmptyOrWhitespace");
		
//		if ((newUser.getFirstName() == null)
//				|| (newUser.getFirstName().length() == 0)) {
//			
//			errors.rejectValue("firstName", "First name cannot be null");
//		}
		
		if ((newUser.getLastName() == null)
				|| (newUser.getLastName().length() == 0)) {
			errors.rejectValue("lastName", "required.lastxName");
		}
	}

}