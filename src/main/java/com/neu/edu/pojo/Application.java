package com.neu.edu.pojo;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Application {
	
	public Application() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ApplicationID")
	private int id;
	
	@Column(name="resume")
	private String resume;
	
	@Column(name="status")
	private String status;
	
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	private Job job;
	
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	private Student student;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getResume() {
		return resume;
	}

	public void setResume(String resume) {
		this.resume = resume;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
	
}
