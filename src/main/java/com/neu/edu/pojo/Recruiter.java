package com.neu.edu.pojo;



import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;



@Entity
public class Recruiter {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="RecruiterID")
	private int id;
	
	@Column(name = "Name")
	private String name;
	
	@Column(name="Email")
	private String email;
	
	@Column(name="Username")
	private String username;
	
	@Column(name="Password")
	private String password;
	
	@Column(name="Contact")
	private String contact;
	
	@Column(name="CompanyName")
	private String companyname;
	
	@Column(name="Website")
	private String website;
	
	@Column(name="Industry")
	private String industry;
	
	@Column(name="Status")
	private int status;
	
	@OneToMany
	@JoinColumn(name = "recruiterid")
	private List<Job> jobs;
	
	
	public Recruiter() {
		
	}
	

	public int getStatus() {
		return status;
	}


	public void setStatus(int status) {
		this.status = status;
	}


	public int getId() {
		return id;
	}

	public List<Job> getJobs() {
		return jobs;
	}

	public void setJobs(List<Job> jobs) {
		this.jobs = jobs;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getCompanyname() {
		return companyname;
	}

	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}
}
