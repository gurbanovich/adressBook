package com.adressbook.service;

import java.util.List;

import com.adressbook.logic.AdressBookForm;

public interface PhoneBookService {

	public void createPerson(AdressBookForm abf);
	public void updatePerson(Long id, AdressBookForm abf);
	public void delPerson(Long id);
	public AdressBookForm getPersonData(Long id);
	public List<AdressBookForm> showAllContacts();	
	public boolean isPersonExist(AdressBookForm abf);
	
}
