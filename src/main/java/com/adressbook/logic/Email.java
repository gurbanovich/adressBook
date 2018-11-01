package com.adressbook.logic;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "emails")
public class Email implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "email_id", nullable = false)
	public long emailId;

	@Column(name = "email")
	private String email;

	@ManyToOne
	@JoinColumn(name = "person_id")
	private Person person;
	
	public Email() {}
	
	public Email(String email) {
		this.email=email;
	}
	
	public Email(String email, Person person) {
		this.email=email;
		this.person=person;
	}
	
	public long getEmailId() {
		return emailId;
	}
	
	public void setEmailId(long emailId) {
		this.emailId=emailId;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email=email;
	}
	
	public Person getPerson() {
		return person;
	}
	
	public void setPerson(Person person) {
		this.person=person;
	}
}
