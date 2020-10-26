package com.neu.edu.controller;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.neu.edu.dao.ApplicationDAO;
import com.neu.edu.dao.JobDAO;
import com.neu.edu.dao.StudentDAO;
import com.neu.edu.pojo.Application;
import com.neu.edu.pojo.Job;
import com.neu.edu.pojo.Recruiter;
import com.neu.edu.pojo.Student;
import com.neu.edu.validator.StudentValidator;

@Controller
@RequestMapping("/student/*")
public class StudentController {
	
	@Autowired
	StudentDAO studentDao;
	
	@Autowired
	ApplicationDAO applicationDao; 
	
	@Autowired
	@Qualifier("studentValidator")
	StudentValidator loginValidator;
	
   @InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(loginValidator);
	}
	
	@RequestMapping(value = "/dashboard.htm", method=RequestMethod.POST)
	protected ModelAndView loginStudent(@ModelAttribute("student") Student stud,
			BindingResult result,HttpServletRequest request) throws Exception {
		
		loginValidator.validate(stud, result);
		if(result.hasErrors()) {
			return new ModelAndView("student-login", "student", new Student());
		}
		
		String username= stud.getUsername();
		String password= stud.getPassword();
		Student student=studentDao.loginStudent(username, password);
        HttpSession session=request.getSession();
		
		if(student==null)
			return new ModelAndView("student-login", "student", new Student());
		if(student.getStatus()==0||student==null) {
			return new ModelAndView("student-login", "student", new Student());
		}
		else {
		session.setAttribute("student", student);
		List<Job> jlist= studentDao.getAllJobs();
		List<Application> alist= applicationDao.getAllAppliedJobs(student);
		
		ModelAndView mv=new ModelAndView("student-home");
		mv.addObject("jlist", jlist);
		mv.addObject("alist", alist);
		
		request.setAttribute("jlist", jlist);
		request.setAttribute("alist", alist);
			
		return mv;
		}
	}
	
	@RequestMapping(value = "/dashboard.htm", method=RequestMethod.GET)
	protected ModelAndView showJobs(HttpServletRequest request) throws Exception {
		HttpSession session=request.getSession();
		Student student=(Student) session.getAttribute("student");
		
		List<Job> jlist= studentDao.getAllJobs();
		List<Application> alist= applicationDao.getAllAppliedJobs(student);
		
		ModelAndView mv=new ModelAndView("student-home");
		mv.addObject("jlist", jlist);
		mv.addObject("alist", alist);
		return mv;
	}
	
	@RequestMapping(value = "/myapplications.htm", method=RequestMethod.GET)
	protected ModelAndView myApplications(HttpServletRequest request) throws Exception {
		HttpSession session=request.getSession();
		Student s= (Student) session.getAttribute("student");
		List<Application> alist= applicationDao.getAllAppliedJobs(s);
		ModelAndView mv=new ModelAndView("student-applied-jobs","alist",alist);
		return mv;
	}
	

	
	
	@RequestMapping(value = "/apply.htm", method=RequestMethod.GET)
	protected ModelAndView applyJob(HttpServletRequest request) throws Exception {
		HttpSession session=request.getSession();
		Job job=studentDao.findJobs(Integer.parseInt(request.getParameter("id")));
		Student s= (Student) session.getAttribute("student");
		Application application=new Application();
		application.setJob(job);
		application.setResume(request.getParameter("name"));
		application.setStatus("applied");
		application.setStudent(s);
		applicationDao.applyJob(application);
		
		List<Job> jlist= studentDao.getAllJobs();
		List<Application> alist= applicationDao.getAllAppliedJobs(s);
		
		ModelAndView mv=new ModelAndView("student-home");
		mv.addObject("jlist", jlist);
		mv.addObject("alist", alist);
		return mv;
	}
	
	
	
	@RequestMapping(value = "/shortlisted/jobs.htm", method=RequestMethod.GET)
	protected ModelAndView shortlistedJobs(HttpServletRequest request) throws Exception {
		HttpSession session=request.getSession();
		Student s= (Student) session.getAttribute("student");
		
		List<Application> shortlistedApplications= studentDao.getShortlistedJobs(s);
		ModelAndView mv=new ModelAndView("student-view-shortlisted-applications","shortlisted",shortlistedApplications);
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
