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
@Table(name = "phone_numbers")
public class PhoneNumber implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "number_id", nullable = false)
	public long numberId;

	@Column(name = "number")
	private String number;

	@ManyToOne
	@JoinColumn(name = "person_id")
	private Person person;
	
	public PhoneNumber() {}
	
	public PhoneNumber(String number) {
		this.number=number;
	}
	
	public PhoneNumber(String number, Person person) {
		this.number=number;
		this.person=person;
	}
	
	public long getNumberId() {
		return numberId;
	}
	
	public void setNumberId(long numberId) {
		this.numberId=numberId;
	}
	
	public String getNumber() {
		return number;
	}
	
	public void setNumber(String number) {
		this.number=number;
	}
	
	public Person getPerson() {
		return person;
	}
	
	public void setPerson(Person person) {
		this.person=person;
	}
}
