package com.neu.edu.pojo;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;



@Entity
public class Job {
	
	public Job() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="JobID")
	private int id;
	
	@Column(name="Title")
	private String title;
	
	@Column(name="Openings")
	private int openings;
	
	@Column(name="Term")
	private String term;
	
	@Column(name="Length")
	private int length;
	
	@Column(name="Description")
	private String description;
	
	@Column(name="Wage")
	private int wage;
	
	@Column(name="Deadline")
	private Date date;
	
	@Column(name="Location")
	private String location;
	
	@Column(name="Organization")
	private String organization;
	
	@ManyToOne
	@JoinColumn(name = "recruiterid")
	private Recruiter recruiter;
	
	@OneToMany
	@JoinColumn(name = "jobid")
	private List<Application> applications;
	
	
	public List<Application> getApplications() {
		return applications;
	}

	public void setApplications(List<Application> applications) {
		this.applications = applications;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getOpenings() {
		return openings;
	}

	public void setOpenings(int openings) {
		this.openings = openings;
	}

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getWage() {
		return wage;
	}

	public void setWage(int wage) {
		this.wage = wage;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Recruiter getRecruiter() {
		return recruiter;
	}

	public void setRecruiter(Recruiter recruiter) {
		this.recruiter = recruiter;
	}

}
