package com.neu.edu.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import com.neu.edu.exception.EmployerException;
import com.neu.edu.pojo.Application;
import com.neu.edu.pojo.Job;
import com.neu.edu.pojo.Recruiter;

public class EmployerDAO extends DAO {
	
	public void addEmployer(Recruiter recruiter) throws EmployerException
	{
		try {
		begin();
		recruiter.setStatus(0);
		getSession().save(recruiter);
		commit();
		}catch(HibernateException e) {
			rollback();
			throw new EmployerException("Could not register employer ");
		}
	}
	
	public Recruiter loginEmployer(String username, String password) throws EmployerException
	{
		try {
			begin();
			Query q = getSession().createQuery("from Recruiter where Username = :username and Password = :password");
			q.setString("username", username);
			q.setString("password", password);			
			Recruiter recruiter = (Recruiter) q.uniqueResult();
			commit();
			return recruiter;
		} catch (HibernateException e) {
			rollback();
			throw new EmployerException("Could not login employer");
		}
	}
	
	public Recruiter getEmployer(int id) throws EmployerException
	{
		try {
			begin();
			Query q = getSession().createQuery("from Recruiter where id = :idd");
			q.setInteger("idd", id);
			Recruiter recruiter1 = (Recruiter) q.uniqueResult();
			commit();
			return recruiter1;
		} catch (HibernateException e) {
			rollback();
			throw new EmployerException("Could not fetch employer");
		}
	}
	
	public void setShortlistedCandidate(Application application) throws EmployerException
	{
		try {
			begin();
			getSession().save(application);
			commit();
		} catch (HibernateException e) {
			rollback();
			throw new EmployerException("Could not shortlist candidates");
		}
	}
	
	
	public List<Application> getApplications(Job job) throws EmployerException
	{
		try {
			Criteria crit=getSession().createCriteria(Application.class);
			Criteria recrit= crit.createCriteria("job");
			recrit.add(Restrictions.eq("id",job.getId()));
	     	List<Application> applicationlist= crit.list();
			return applicationlist;
		} catch (HibernateException e) {
			System.out.println(e);
			throw new EmployerException("Could not get applications");
		}
	}

	public boolean validateUsername(String username) throws EmployerException {
		try {
			begin();
			Query q = getSession().createQuery("from Recruiter where username = :name");
			q.setString("name", username);
			Recruiter recruiter = (Recruiter) q.uniqueResult();
			commit();
			if(recruiter==null)
				return false;
		}catch(HibernateException e) {
			rollback();
			throw new EmployerException("Could not validate employer");
		}
		return true;
	}

	public boolean validateEmail(String email) throws EmployerException {
		try {
			begin();
			Query q = getSession().createQuery("from Recruiter where email = :ename");
			q.setString("ename", email);
			Recruiter recruiter = (Recruiter) q.uniqueResult();
			commit();
			if(recruiter==null)
				return false;
		}catch(HibernateException e) {
			rollback();
			throw new EmployerException("Could not validate employer");
		}
		return true;
	}
	
	public List<Recruiter> getEmployerList() throws EmployerException {
		try {
			begin();
			Query q = getSession().createQuery("from Recruiter where status = :sta");
			q.setInteger("sta", 0);
			List<Recruiter> recruiterList = q.list();
			commit();
			return recruiterList;
		}catch(HibernateException e) {
			rollback();
			throw new EmployerException("Could not get employer list");
		}
	}
	
	

}
