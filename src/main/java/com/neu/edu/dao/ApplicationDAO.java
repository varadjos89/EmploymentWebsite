package com.neu.edu.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Restrictions;

import com.neu.edu.exception.ApplicationException;
import com.neu.edu.pojo.Application;
import com.neu.edu.pojo.Job;
import com.neu.edu.pojo.Recruiter;
import com.neu.edu.pojo.Student;

public class ApplicationDAO extends DAO {
	
	public void applyJob(Application application) throws ApplicationException
	{
		try {
		begin();
		getSession().save(application);
		commit();
		}catch(HibernateException e) {
			rollback();
			throw new ApplicationException("Could not apply for job");
		}
	}
	
	public List<Application> getAllAppliedJobs(Student student) throws ApplicationException
	{
		try {
			Criteria crit=getSession().createCriteria(Application.class);
			Criteria recrit= crit.createCriteria("student");
			recrit.add(Restrictions.eq("id",student.getId()));
	     	List<Application> appliedjobs= crit.list();
			return appliedjobs;
		}catch(HibernateException  e) {
			rollback();
			throw new ApplicationException("Could not get applied jobs");			
		}
	}
	
	public List<Application> getAllApplications(Recruiter recruiter) throws ApplicationException
	{
		try {
			Criteria crit=getSession().createCriteria(Application.class);
			Application application=new Application();
			Example appExample=Example.create(application);
			Criteria jobCrit=crit.createCriteria("job");
			Job job =new Job();
			job.setRecruiter(recruiter);
			jobCrit.add(Example.create(job));
			crit.add(appExample);
			List<Application> lapp= crit.list();		
			return lapp;
		}catch(HibernateException  e) {
			rollback();
			throw new ApplicationException("Could not get all applications");
		}
	}
	
	public Application getAppliedJob(int id) throws ApplicationException
	{
		try {
			Criteria crit=getSession().createCriteria(Application.class);
			crit.add(Restrictions.eq("id",id));
	     	Application appliedjob= (Application) crit.uniqueResult();
			return appliedjob;
		}catch(HibernateException  e) {
			rollback();
			throw new ApplicationException("Could not get applied jobs");
		}
	}

}
