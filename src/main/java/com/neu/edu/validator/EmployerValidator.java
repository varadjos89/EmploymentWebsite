package com.neu.edu.validator;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.neu.edu.dao.EmployerDAO;
import com.neu.edu.pojo.Recruiter;





@Component
public class EmployerValidator implements Validator {

	@Autowired
	EmployerDAO employerDAO;
	
	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return Recruiter.class.equals(clazz);

	}
	
	/*@Override
	public boolean supports(Class aClass) {
		return aClass.equals(Recruiter.class);
	}*/

	@Override
	public void validate(Object obj, Errors errors) {
		
		Recruiter recruiter = (Recruiter) obj;
		
		System.out.println("inside Recruiter valid222");
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "empty.name", "Name Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "empty.email", "Email Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "empty.username", "Username Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "empty.password", "Password Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "contact", "empty.contact", "Contact Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "companyname", "empty.companyname", "Company Name Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "website", "empty.website", "Website Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "industry", "empty.industry", "Industry Required");
		
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
		
		Pattern pass = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\\$%\\^&\\*])(?=.{8,})");
		Pattern name = Pattern.compile("^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$");
		Pattern username = Pattern.compile("^(?!.*__.*)(?!.*\\.\\..*)[a-z0-9_.]+$");
		Pattern url= Pattern.compile("^(http:\\/\\/www\\.|https:\\/\\/www\\.|http:\\/\\/|https:\\/\\/)?[a-z0-9]+([\\-\\.]{1}[a-z0-9]+)*\\.[a-z]{2,5}(:[0-9]{1,5})?(\\/.*)?$");
		
		
		/* if(name.matcher(recruiter.getName()).find()){
			 errors.rejectValue("name", "name-invalid", "enter valid name");
		 }
		 if(pass.matcher(recruiter.getPassword()).find()){
			 errors.rejectValue("password", "password-invalid", "enter valid password");
		 }
		 if(username.matcher(recruiter.getUsername()).find()){
			 errors.rejectValue("username", "username-invalid", "enter valid username");
		 }
		 if(url.matcher(recruiter.getWebsite()).find()){
			 errors.rejectValue("website", "website-invalid", "enter valid website url");
		 }*/
		 
		 
		 
		
	}



	

}

