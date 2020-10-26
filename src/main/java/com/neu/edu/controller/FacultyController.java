package com.neu.edu.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.neu.edu.dao.EmployerDAO;
import com.neu.edu.dao.FacultyDAO;
import com.neu.edu.dao.StudentDAO;
import com.neu.edu.pojo.Faculty;
import com.neu.edu.pojo.Recruiter;
import com.neu.edu.pojo.Student;
import com.neu.edu.validator.FacultyValidator;

@Controller
@RequestMapping("/faculty/*")
public class FacultyController {
	
	@Autowired
	FacultyDAO facultyDAO;
	
	@Autowired
	EmployerDAO employerDao;
	
	@Autowired
	StudentDAO studentDao;
	
	@Autowired
	@Qualifier("facultyValidator")
	FacultyValidator loginValidator;
	
   @InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(loginValidator);
	}
	
	@RequestMapping(value = "/dashboard.htm", method=RequestMethod.POST)
	protected ModelAndView loginStudent(@ModelAttribute("faculty") Faculty fac,
			BindingResult result,HttpServletRequest request) throws Exception {
		
		loginValidator.validate(fac, result);
		if(result.hasErrors()) {
			return new ModelAndView("faculty-login", "faculty", new Faculty());
		}
		
		String username= request.getParameter("username");
		String password= request.getParameter("password");

		Faculty faculty=facultyDAO.loginFaculty(username, password);
        HttpSession session=request.getSession();
		session.setAttribute("faculty", faculty);
		List<Recruiter> rlist= employerDao.getEmployerList();
		
		
		ModelAndView mv=new ModelAndView("faculty-home");
		mv.addObject("rlist", rlist);
	
		if(faculty==null)
			return new ModelAndView("faculty-login");
		return mv;
	}
	
	@RequestMapping(value = "/dashboard.htm", method=RequestMethod.GET)
	protected ModelAndView showEmployerRequests(HttpServletRequest request) throws Exception {
		
		
		
		List<Recruiter> rlist= employerDao.getEmployerList();
		
		
		ModelAndView mv=new ModelAndView("faculty-home");
		mv.addObject("rlist", rlist);
		return mv;
	}
		
	@RequestMapping(value = "/approve/request.htm", method=RequestMethod.POST)
	protected ModelAndView approveRequests(HttpServletRequest request) throws Exception {
		
		int id= Integer.parseInt(request.getParameter("id"));
		Recruiter recruiter=employerDao.getEmployer(id);
		recruiter.setStatus(1);
		facultyDAO.approveEmployerRequest(recruiter);
		
        List<Recruiter> rlist= employerDao.getEmployerList();
		
		
		ModelAndView mv=new ModelAndView("faculty-home");
		mv.addObject("rlist", rlist);
		return mv;
	}
	
	@RequestMapping(value = "/deny/request.htm", method=RequestMethod.POST)
	protected ModelAndView denyRequests(HttpServletRequest request) throws Exception {
		
		int id= Integer.parseInt(request.getParameter("id"));
		Recruiter recruiter=employerDao.getEmployer(id);
		facultyDAO.denyEmployerRequest(recruiter);
		
        List<Recruiter> rlist= employerDao.getEmployerList();
		
		
		ModelAndView mv=new ModelAndView("faculty-home");
		mv.addObject("rlist", rlist);
		return mv;
	}
	
	@RequestMapping(value = "/add/studentPage.htm", method=RequestMethod.GET)
	protected ModelAndView redirectStudentPage(HttpServletRequest request) throws Exception {
		List<Student> slist= studentDao.getStudentList();
		ModelAndView mv=new ModelAndView("faculty-add-students");
		mv.addObject("slist", slist);
		return mv;
	}
	
	@RequestMapping(value = "/add/student.htm", method=RequestMethod.POST)
	protected ModelAndView addStudent(HttpServletRequest request) throws Exception {
		
		int id= Integer.parseInt(request.getParameter("id"));
		Student student=studentDao.getStudent(id);
		student.setStatus(1);
		facultyDAO.grantStudentAccess(student);
		
		List<Student> slist= studentDao.getStudentList();
		ModelAndView mv=new ModelAndView("faculty-add-students");
		mv.addObject("slist", slist);
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
