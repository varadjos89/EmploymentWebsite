package com.neu.edu.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Restrictions;

import com.neu.edu.exception.FacultyException;
import com.neu.edu.exception.JobException;
import com.neu.edu.pojo.Job;
import com.neu.edu.pojo.Recruiter;


public class JobDAO extends DAO {
	
	public void addingJob(Job job) throws JobException
	{
		try {
		begin();
		getSession().save(job);
		commit();
		}catch(HibernateException e) {
			e.printStackTrace();
			rollback();
			throw new JobException("Could not add job ");
		}
	}
	
	public List<Job> getJobs(Recruiter recruiter) throws JobException
	{
		try {
			Criteria crit=getSession().createCriteria(Job.class);
			Criteria recrit= crit.createCriteria("recruiter");
			recrit.add(Restrictions.eq("id", recruiter.getId()));
			List<Job> jobs= crit.list();
			return jobs;
		}catch(HibernateException  e) {
			System.out.println(e);
			throw new JobException("Could not get jobs ");
		}
	}

}
