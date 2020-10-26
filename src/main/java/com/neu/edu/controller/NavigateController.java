package com.neu.edu.controller;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.neu.edu.dao.ApplicationDAO;
import com.neu.edu.dao.JobDAO;
import com.neu.edu.dao.StudentDAO;
import com.neu.edu.pojo.Application;
import com.neu.edu.pojo.Faculty;
import com.neu.edu.pojo.Job;
import com.neu.edu.pojo.Recruiter;
import com.neu.edu.pojo.Student;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/*")
public class NavigateController {
	
	@Autowired
	JobDAO jobDao;
	
	@Autowired
	StudentDAO studentDao;
	
	@Autowired
	ApplicationDAO applicationDao;
	
	private static final Logger logger = LoggerFactory.getLogger(NavigateController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Locale locale, Model model) {
		
		return "index";
	}
	
	@RequestMapping(value = "/student/loginPage.htm", method=RequestMethod.GET)
	protected ModelAndView studentlogin(HttpServletRequest request) throws Exception {
		return new ModelAndView("student-login", "student", new Student());
	}
	
	@RequestMapping(value = "/employer/loginPage.htm", method=RequestMethod.GET)
	protected ModelAndView employerlogin(HttpServletRequest request) throws Exception {
		return new ModelAndView("employer-login", "recruiter", new Recruiter());
	}
	
	@RequestMapping(value = "/faculty/loginPage.htm", method=RequestMethod.GET)
	protected ModelAndView facultylogin(HttpServletRequest request) throws Exception {
		return new ModelAndView("faculty-login", "faculty", new Faculty());
	}
	
	@RequestMapping(value = "/employer/registerPage.htm", method=RequestMethod.GET)
	protected ModelAndView employerregister(HttpServletRequest request) throws Exception {
		return new ModelAndView("employer-register", "recruiter", new Recruiter());
	}
	
	
	@RequestMapping(value = "/employer/add/jobPage.htm", method=RequestMethod.GET)
	protected ModelAndView addposting(HttpServletRequest request) throws Exception {
		return new ModelAndView("add-posting", "job", new Job());
	}
	
	@RequestMapping(value = "/employer/add/job.htm", method=RequestMethod.POST)
	protected ModelAndView addJob(@ModelAttribute("job") Job job,
			HttpServletRequest request) throws Exception {
		ModelAndView mv=new ModelAndView("add-posting");
		HttpSession session=request.getSession();
		Recruiter recruiter= (Recruiter) session.getAttribute("recruiter");
		job.setRecruiter(recruiter);
		jobDao.addingJob(job);
		return mv;
	}
	
	@RequestMapping(value = "/student/view/job.htm", method=RequestMethod.POST)
	protected ModelAndView viewJob(HttpServletRequest request) throws Exception {
		Job job=studentDao.findJobs(Integer.parseInt(request.getParameter("id")));
        String s=request.getParameter("status");
		ModelAndView mv=new ModelAndView("student-view-job","job",job);
		mv.addObject("job",job);
		mv.addObject("status",s);
		return mv;
	}
	
	@RequestMapping(value = "/student/view/applied/job.htm", method=RequestMethod.POST)
	protected ModelAndView viewAppliedJob(HttpServletRequest request) throws Exception {
		Application ap=(Application) applicationDao.getAppliedJob(Integer.parseInt(request.getParameter("id")));
		ModelAndView mv=new ModelAndView("student-view-applied-job","job",ap);
		return mv;
	}
	
}
