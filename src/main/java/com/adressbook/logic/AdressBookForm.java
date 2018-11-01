package com.adressbook.logic;

import java.util.ArrayList;
import java.util.List;

public class AdressBookForm {

	private long personId;
	private String firstName;
	private String secondName;
	private String workPlace;
	private String adress;
	private String comments;
	private List<String> numbers;
	private List<String> emails;

	public AdressBookForm() {
	}

	public AdressBookForm(long personId, String firstName, String secondName, String workPlace, String adress, String comments,
			List<String> numbers, List<String> emails) {
		this.personId=personId;
		this.firstName = firstName;
		this.secondName = secondName;
		this.workPlace = workPlace;
		this.adress = adress;
		this.comments = comments;
		this.numbers = numbers;
		this.emails = emails;
	}

	public AdressBookForm(String firstName, String secondName, String workPlace, String adress, String comments,
			List<String> numbers, List<String> emails) {
		this.firstName = firstName;
		this.secondName = secondName;
		this.workPlace = workPlace;
		this.adress = adress;
		this.comments = comments;
		this.numbers = numbers;
		this.emails = emails;
	}
	public AdressBookForm(long personId, String firstName, String secondName, String number, String email) {
		this.personId=personId;
		this.firstName = firstName;
		this.secondName = secondName;
		this.numbers = new ArrayList<String>();
		numbers.add(number);
		this.emails = new ArrayList<String>();
		emails.add(email);
	}

	public long getPersonId() {
		return personId;
	}

	public void setPersonId(long personId) {
		this.personId = personId;
	}
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSecondName() {
		return secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	public String getWorkPlace() {
		return workPlace;
	}

	public void setWorkPlace(String workPlace) {
		this.workPlace = workPlace;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public List<String> getNumbers() {
		return numbers;
	}

	public void setNumbers(List<String> numbers) {
		this.numbers = numbers;
	}

	public List<String> getEmails() {
		return emails;
	}

	public void setEmails(List<String> emails) {
		this.emails = emails;
	}

	@Override
	public String toString() {
		return "Name: " + getFirstName() + " " + getSecondName() + "\nPlace of name: " + getWorkPlace() + "\nadress: "
				+ getAdress() + "\nphone numbers: " + getNumbers() + "\nemails: " + getEmails();
	}
}
