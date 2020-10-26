package com.neu.edu.validator;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.neu.edu.pojo.Student;





@Component
public class StudentValidator implements Validator {

	
	
	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return Student.class.equals(clazz);

	}
	
	/*@Override
	public boolean supports(Class aClass) {
		return aClass.equals(Recruiter.class);
	}*/

	@Override
	public void validate(Object obj, Errors errors) {
		
		Student student = (Student) obj;
		
		System.out.println("inside Student valid"+student.getUsername());
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "empty.username", "Username Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "empty.password", "Password Required");
		
		/*String username = recruiter.getUsername();
		System.out.println(username);
		boolean b = employerDAO.validateUsername(username);
		boolean e = employerDAO.validateEmail(recruiter.getEmail());
		if(b){
			errors.rejectValue("username", "username-invalid", "Username already exists; enter different value");
		}
		if(e){
			errors.rejectValue("email", "email-invalid", "Email ID already exists; enter different value");
		}*/
		
		
		 
		 
		
	}



	

}

