package com.neu.edu.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Restrictions;

import com.neu.edu.exception.EmployerException;
import com.neu.edu.exception.StudentException;
import com.neu.edu.pojo.Application;
import com.neu.edu.pojo.Job;
import com.neu.edu.pojo.Recruiter;
import com.neu.edu.pojo.Student;

public class StudentDAO extends DAO{

	public Student loginStudent(String username, String password) throws StudentException
	{
		Student student=null;
		try {
			begin();
			Query q = getSession().createQuery("from Student where Username = :username and Password = :password");
			q.setString("username", username);
			q.setString("password", password);			
			student = (Student) q.uniqueResult();
			commit();
			return student;
		} catch (HibernateException e) {
			rollback();
			throw new StudentException("Could not login student ");
		}
	}
	
	public List<Job> getAllJobs() throws StudentException
	{
		try {
			begin();
			Query q = getSession().createQuery("from Job");		
			List<Job> jobs= (List<Job>) q.list();
			commit();
			return jobs;
		}catch(HibernateException  e) {
			System.out.println(e);
			rollback();
			throw new StudentException("Could not get jobs ");
		}
	}
	
	public Job findJobs(int id) throws StudentException
	{
		try {
			/*Criteria crit=getSession().createCriteria(Job.class);
			Job job=new Job();
			job.setId(id);
			Example example= Example.create(job);
			crit.add(example);
			Job j= (Job) crit.uniqueResult();
			return j;*/
			begin();
			Query q=getSession().createQuery("from Job where jobId=:jid ");
			q.setInteger("jid",id);
			Job j= (Job) q.uniqueResult();
			commit();
			return j;
			
		}catch(HibernateException  e) {
			rollback();
			throw new StudentException("Could not find jobs ");
		}
	}
	
	public List<Student> getStudentList() throws StudentException{
		try {
			begin();
			Query q = getSession().createQuery("from Student where status = :sta");
			q.setInteger("sta", 0);
			List<Student> studentList = q.list();
			commit();
			return studentList;
		}catch(HibernateException e) {
			rollback();
			throw new StudentException("Could not get student list ");
		}
		
	}
	
	public Student getStudent(int id) throws StudentException
	{
		try {
			begin();
			Query q = getSession().createQuery("from Student where id = :idd");
			q.setInteger("idd", id);
			Student student = (Student) q.uniqueResult();
			commit();
			return student;
		} catch (HibernateException e) {
			rollback();
			throw new StudentException("Could not get student ");
		}
	}

	public List<Application> getShortlistedJobs(Student s) throws StudentException {
		try {
		Criteria crit=getSession().createCriteria(Application.class);
		crit.add(Restrictions.eq("status","shortlisted"));
		Criteria recrit= crit.createCriteria("student");
		recrit.add(Restrictions.eq("id",s.getId()));
     	List<Application> shortlistedjobs= crit.list();
     	return shortlistedjobs;
		}catch(HibernateException e)
		{
			rollback();
			throw new StudentException("Could not shortlist student ");
		}
	}
}
