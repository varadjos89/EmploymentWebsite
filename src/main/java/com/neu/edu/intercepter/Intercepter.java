package com.neu.edu.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


public class Intercepter implements HandlerInterceptor{

	
	String errorPage;

	public String getErrorPage() {
		return errorPage;
	}

	public void setErrorPage(String errorPage) {
		this.errorPage = errorPage;
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println();
		System.out.println("Varad Subodh Joshi");
		System.out.println();
		
		HttpSession session = (HttpSession) request.getSession();
		
	    if(session.getAttribute("role") == null){
	    	if(request.getRequestURI().equals("/edu/student/loginPage.htm")){
	    		   System.out.println("1");
	           	   return true;
	    	}
	    	else if(request.getRequestURI().equals("/edu/")){
	    		System.out.println("2");
	           	   return true;
	    	}
	    	else if(request.getRequestURI().equals("/edu/employer/loginPage.htm")){
	    		System.out.println("2");
	           	   return true;
	    	}
	    	else if(request.getRequestURI().equals("/edu/faculty/loginPage.htm")){
	    		System.out.println("3");
	           	   return true;
	    	}
	    	else if(request.getRequestURI().equals("/edu/employer/registerPage.htm")){
	    		System.out.println("4");
	           	   return true;
	    	}
	    	else if(request.getRequestURI().equals("/edu/employer/register.htm")){
	    		System.out.println("5");
	           	   return true;
	    	}
	    	else if(request.getRequestURI().equals("/edu/student/dashboard.htm")){
	    		System.out.println("6");
	    		   session.setAttribute("role", "student");
	           	   return true;
	    	}
	    	else if(request.getRequestURI().equals("/edu/faculty/dashboard.htm")){
	    		System.out.println("7");
	    		   session.setAttribute("role", "faculty");
	           	   return true;
	    	}
	    	else if(request.getRequestURI().equals("/edu/employer/dashboard.htm")){
	    		System.out.println("8");
	    		   session.setAttribute("role", "employer");
	           	   return true;
	    	}
	    }
	    if(request.getRequestURI().equals("/edu/student/*")){
	    	if(session.getAttribute("role").equals("student"))
	    	{
	    		System.out.println("9");
        	   return true;
	    	}
	    	else {
	    		System.out.println("10");
	    	   return false;
	    	}
 	    }
	    
	    if(request.getRequestURI().equals("/edu/employer/*")){
	    	if(session.getAttribute("role").equals("employer"))
	    	{
	    		System.out.println("11");
        	   return true;
	    	}
	    	else {
	    		System.out.println("12");	
	    	   return false;
	    	}
 	    }
	    
	    if(request.getRequestURI().equals("/edu/faculty/*")){
	    	if(session.getAttribute("role").equals("faculty"))
	    	{
	    		System.out.println("13");
        	   return true;
	    	}
	    	else
	    	{
	    		System.out.println("14");
	    	   return false;
	    	}
 	    }
	    return false;	
	}
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

}
