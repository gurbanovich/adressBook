package com.adressbook.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adressbook.logic.AdressBookForm;
import com.adressbook.logic.Email;
import com.adressbook.logic.Person;
import com.adressbook.logic.PhoneNumber;
import com.adressbook.repository.EmailRepository;
import com.adressbook.repository.PersonRepository;
import com.adressbook.repository.PhoneNumberRepository;

@Service
public class PhoneBookServiceImpl implements PhoneBookService {

	@Autowired
	private PersonRepository pRep;

	@Autowired
	private PhoneNumberRepository numRep;

	@Autowired
	private EmailRepository emailRep;

	public void createPerson(AdressBookForm abf) {
		Person per = new Person(abf.getFirstName(), abf.getSecondName(), abf.getAdress(), abf.getAdress(),
				abf.getComments());
		pRep.saveAndFlush(per);

		for (String num : abf.getNumbers()) {
			numRep.saveAndFlush(new PhoneNumber(num, per));
		}
		for (String email : abf.getEmails()) {
			emailRep.saveAndFlush(new Email(email, per));
		}
	}

	public void updatePerson(Long id, AdressBookForm abf) {
		Person person = pRep.findPersonById(id);
		List<PhoneNumber> numbers = numRep.findPersonNumberObject(id);
		List<Email> emails = emailRep.findPersonEmailObjects(id);
		for (PhoneNumber num : numbers) {
			numRep.delete(num);
			;
		}
		for (Email email : emails) {
			emailRep.delete(email);
		}
		person.setFirstName(abf.getFirstName());
		person.setSecondName(abf.getSecondName());
		;
		person.setWorkPlace(abf.getWorkPlace());
		;
		person.setAdress(abf.getAdress());
		person.setComments(abf.getComments());
		pRep.saveAndFlush(person);
		for (String num : abf.getNumbers()) {
			numRep.saveAndFlush(new PhoneNumber(num, person));
		}
		for (String email : abf.getEmails()) {
			emailRep.saveAndFlush(new Email(email, person));
		}
	}

	public void delPerson(Long id) {
		Person person = pRep.findPersonById(id);
		List<PhoneNumber> numbers = numRep.findPersonNumberObject(id);
		List<Email> emails = emailRep.findPersonEmailObjects(id);
		pRep.delete(person);
		for (PhoneNumber num : numbers) {
			numRep.delete(num);
			;
		}
		for (Email email : emails) {
			emailRep.delete(email);
		}
	}

	public AdressBookForm getPersonData(Long id) {
		Person person = pRep.findPersonById(id);
		List<String> numbers = numRep.findPersonNumbers(id);
		List<String> emails = emailRep.findPersonEmails(id);
		AdressBookForm abf = new AdressBookForm(person.getPersonId(), person.getFirstName(), person.getSecondName(),
				person.getWorkPlace(), person.getAdress(), person.getComments(), numbers, emails);
		return abf;
	}

	public List<AdressBookForm> showAllContacts() {
		List<AdressBookForm> results = new LinkedList<AdressBookForm>();
		List<Person> persons = pRep.findAll();
		for (Person p : persons) {
			String number = null;
			String email = null;
			AdressBookForm result = null;
			List<String> numbers = numRep.findPersonNumbers(p.getPersonId());
			List<String> emails = emailRep.findPersonEmails(p.getPersonId());
			for (String s : numbers) {
				number = s;
				break;
			}
			for (String s : emails) {
				email = s;
				break;
			}
			result = new AdressBookForm(p.getPersonId(), p.getFirstName(), p.getSecondName(), number, email);
			results.add(result);
		}
		return results;
	}

	public boolean isPersonExist(AdressBookForm abf) {
		String firstName = abf.getFirstName();
		String secondName = abf.getSecondName();
		return pRep.findPersonByName(firstName, secondName) != null;
	}

}
