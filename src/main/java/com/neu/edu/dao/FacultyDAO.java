package com.neu.edu.dao;

import javax.xml.ws.FaultAction;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.neu.edu.exception.EmployerException;
import com.neu.edu.exception.FacultyException;
import com.neu.edu.pojo.Faculty;
import com.neu.edu.pojo.Recruiter;
import com.neu.edu.pojo.Student;


public class FacultyDAO extends DAO {

	public Faculty loginFaculty(String username, String password) throws FacultyException {
		try {
			begin();
			Query q = getSession().createQuery("from Faculty where Username = :username and Password = :password");
			q.setString("username", username);
			q.setString("password", password);			
			Faculty faculty = (Faculty) q.uniqueResult();
			commit();
			return faculty;
		} catch (HibernateException e) {
			rollback();
			throw new FacultyException("Could not login faculty ");
		}
	}
	
	public void approveEmployerRequest(Recruiter recruiter) throws FacultyException
	{
		try {
		begin();
		getSession().save(recruiter);
		commit();
		}catch(HibernateException e) {
			rollback();
			throw new FacultyException("Could not approve employer ");
		}
	}
	
	public void denyEmployerRequest(Recruiter recruiter) throws FacultyException
	{
		try {
		begin();
		getSession().delete(recruiter);
		commit();
		}catch(HibernateException e) {
			rollback();
			throw new FacultyException("Could not approve employer ");
		}
	}
	
	
	public void grantStudentAccess(Student student) throws FacultyException
	{
		try {
		begin();
		getSession().save(student);
		commit();
		}catch(HibernateException e) {
			rollback();
			throw new FacultyException("Could not grant access to student ");
		}
	}

}
