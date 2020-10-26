package com.neu.edu.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.apache.commons.mail.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.neu.edu.dao.ApplicationDAO;
import com.neu.edu.dao.EmployerDAO;
import com.neu.edu.dao.JobDAO;
import com.neu.edu.pojo.Application;
import com.neu.edu.pojo.Job;
import com.neu.edu.pojo.Recruiter;
import com.neu.edu.validator.EmployerLoginValidator;
import com.neu.edu.validator.EmployerValidator;

@Controller
@RequestMapping("/employer/*")
public class EmployerController {
	
	@Autowired
	EmployerDAO employerDao;
	
	@Autowired
	JobDAO jobDao;
	
	@Autowired
	ApplicationDAO applicationDao; 
	
	@Autowired
	@Qualifier("employerValidator")
	EmployerValidator validator;
	
	@Autowired
	@Qualifier("employerLoginValidator")
	EmployerLoginValidator loginValidator;
	
   @InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator); 
		binder.setValidator(loginValidator);
	}
	

	@RequestMapping(value = "/register.htm", method=RequestMethod.POST)
	protected ModelAndView registerEmployer(@ModelAttribute("recruiter") Recruiter recruiter,
			BindingResult result,HttpServletRequest request) throws Exception {
		validator.validate(recruiter, result);
		
		if(result.hasErrors()) {
			ModelAndView mv=new ModelAndView("employer-register");
			return mv;
		}
		ModelAndView mv=new ModelAndView("employer-login","recruiter", new Recruiter());
		employerDao.addEmployer(recruiter);
		return mv;
	}
	
	@RequestMapping(value = "/dashboard.htm", method=RequestMethod.POST)
	protected ModelAndView loginEmployer(@ModelAttribute("recruiter") Recruiter recruiter,
			BindingResult result,HttpServletRequest request) throws Exception {
		loginValidator.validate(recruiter, result);
		if(result.hasErrors()) {
			ModelAndView mv=new ModelAndView("employer-login","recruiter", new Recruiter());
			return mv;
		}
		String username= recruiter.getUsername();
		String password= recruiter.getPassword();
		Recruiter r= employerDao.loginEmployer(username, password);
		if(r==null)
		{
			return new ModelAndView("employer-login","recruiter", new Recruiter());
		}
		if(r.getStatus()==0) {
			return new ModelAndView("employer-login","recruiter", new Recruiter());
		}
		else
		{
		HttpSession session=request.getSession();
		session.setAttribute("recruiter", r);
		List<Job> jlist= jobDao.getJobs(r);
		ModelAndView mv=new ModelAndView("employer-home","list",jlist);
		return mv;
		}
	}
	
	/*@RequestMapping(value = "/dashboard.htm", method=RequestMethod.POST)
	protected ModelAndView loginEmployer(HttpServletRequest request) throws Exception {
		String username= request.getParameter("username");
		String password= request.getParameter("password");
		Recruiter r= employerDao.loginEmployer(username, password);
		
		if(r.getStatus()==0||r==null) {
			return new ModelAndView("employer-login");
		}
		else
		{
		HttpSession session=request.getSession();
		session.setAttribute("recruiter", r);
		List<Job> jlist= jobDao.getJobs(r);
		ModelAndView mv=new ModelAndView("employer-home","list",jlist);
		return mv;
		}
	}*/
	
	@RequestMapping(value = "/validate.htm", method=RequestMethod.GET)
	protected @ResponseBody String validate(HttpServletRequest request) throws Exception {
		String username= request.getParameter("username");
		Boolean val=employerDao.validateUsername(username);
		if(val)
			return "1";
		return "0";
	}
	
	@RequestMapping(value = "/show/jobs.htm", method=RequestMethod.GET)
	protected ModelAndView showJobs(HttpServletRequest request) throws Exception {
		
		HttpSession session=request.getSession();
		Recruiter recruiter= (Recruiter) session.getAttribute("recruiter");
		List<Job> jlist= jobDao.getJobs(recruiter);
		ModelAndView mv=new ModelAndView("employer-home","list",jlist);
		return mv;
	}
	
	@RequestMapping(value = "/show/applications.htm", method=RequestMethod.GET)
	protected ModelAndView showApplications(HttpServletRequest request) throws Exception {
		HttpSession session=request.getSession();
		Recruiter r= (Recruiter) session.getAttribute("recruiter");
		
		Job j = null;
		List<Application> applications = null;
		List<Job> jobs=r.getJobs();
		if(jobs.size()>0)
		{
		   j=(Job)jobs.get(0);
		   applications= employerDao.getApplications(j);
		}
		
		ModelAndView mv=new ModelAndView("employer-view-applications");
		mv.addObject("joblist",jobs);
		mv.addObject("applicationlist",applications);
		
		return mv;
	}
	
	
	@RequestMapping(value = "/show/applications/list.htm", method=RequestMethod.GET)
	protected ModelAndView showApplicationsList(HttpServletRequest request) throws Exception {
		HttpSession session=request.getSession();
		Recruiter r= (Recruiter) session.getAttribute("recruiter");
		
		int job_id= Integer.parseInt(request.getParameter("listofjobs"));
		List<Application> applications = null;
		List<Job> jobs=r.getJobs();
		for(Job j:jobs)
		{
			if(j.getId()==job_id)
			{
				applications= employerDao.getApplications(j);
			}
		}		
		
		ModelAndView mv=new ModelAndView("employer-view-applications");
		mv.addObject("joblist",jobs);
		mv.addObject("applicationlist",applications);
		
		return mv;
	}
	
	@RequestMapping(value = "/shortlist/candidates", method=RequestMethod.POST)
	protected ModelAndView shortlistCandidates(HttpServletRequest request) throws Exception {
		
		HttpSession session=request.getSession();
		Recruiter r= (Recruiter) session.getAttribute("recruiter");
		Application application= applicationDao.getAppliedJob(Integer.parseInt(request.getParameter("id")));
		application.setStatus("shortlisted");
		employerDao.setShortlistedCandidate(application);
		
		String semail= application.getStudent().getEmail();
		
		Email email = new SimpleEmail();
		email.setHostName("smtp.googlemail.com");
		email.setSmtpPort(465);
		//User your gmail username and password
		email.setAuthenticator(new DefaultAuthenticator("varadjos92@gmail.com", "65230442"));
		email.setSSLOnConnect(true);
		email.setFrom("no-reply@msis.neu.edu");
		email.setSubject("TestMail");
		email.setMsg("Hello "+application.getStudent().getName()+"Your application has been selected");
		email.addTo(semail);
		email.send();
		
		List<Job> jobs=r.getJobs();
		Job j=(Job)jobs.get(0);
		List<Application> applications= employerDao.getApplications(j);
		
		ModelAndView mv=new ModelAndView("employer-view-applications");
		mv.addObject("joblist",jobs);
		mv.addObject("applicationlist",applications);
		
		return mv;
	}
	
	
	@RequestMapping(value = "/logout.htm", method=RequestMethod.GET)
	protected ModelAndView logout(HttpServletRequest request) throws Exception {
		
		HttpSession session=request.getSession();
		session.invalidate();
		ModelAndView mv=new ModelAndView("index");
		return mv;
	}
}
