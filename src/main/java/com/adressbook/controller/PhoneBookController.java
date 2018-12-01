package com.adressbook.controller;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.adressbook.exceptions.CustomErrorType;
import com.adressbook.logic.AdressBookForm;
import com.adressbook.service.PhoneBookService;

@Transactional
@Configuration
@RestController
@RequestMapping("/")
public class PhoneBookController {

	public static final Logger logger = LoggerFactory.getLogger(PhoneBookController.class);

	@Autowired
	private PhoneBookService pb;

	@PostMapping("/add_person")
	public @ResponseBody ResponseEntity<?> addPerson(@Valid @RequestBody AdressBookForm abf) {
		logger.info("Creating person : {}", abf);
		if (pb.isPersonExist(abf)) {
			logger.error("Unable to create. A Person with name {} already exist", abf.getFirstName());
			return new ResponseEntity(new CustomErrorType("Unable to create. A Person with name " + abf.getFirstName()
					+ " " + abf.getSecondName() + " already exist."), HttpStatus.CONFLICT);
		}
		pb.createPerson(abf);

		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}

	@PutMapping("/edit_person/{id}")
	public @ResponseBody ResponseEntity<?> editPerson(@PathVariable(value = "id") long id,
			@Valid @RequestBody AdressBookForm abf) {
		logger.info("Updating Person with id {}", id);
		AdressBookForm curPerson = pb.getPersonData(id);
		if (curPerson == null) {
			logger.error("Unable to update. Person with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Unable to update. Person with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}
		pb.updatePerson(id, abf);
		return new ResponseEntity<AdressBookForm>(curPerson, HttpStatus.OK);

	}

	@DeleteMapping("/delete_person/{id}")
	public ResponseEntity<?> deletePerson(@PathVariable(value = "id") long id) {
		logger.info("Fetching & Deleting Person with id {}", id);
		AdressBookForm curPerson = pb.getPersonData(id);
		if (curPerson == null) {
			logger.error("Unable to delete. Person with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Unable to delete. Person with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}
		pb.delPerson(id);
		return new ResponseEntity<AdressBookForm>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/show_person/{id}")
	public ResponseEntity<?> showPerson(@PathVariable(value = "id") long id) {
		logger.info("Fetching Person with id {}", id);
		AdressBookForm person = pb.getPersonData(id);
		if (person == null) {
			logger.error("Person with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Person with id " + id + " not found"), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<AdressBookForm>(person, HttpStatus.OK);
	}

	@RequestMapping("/show_persons")
	public ResponseEntity<List<AdressBookForm>> showAllPersons() {
		List<AdressBookForm> persons = pb.showAllContacts();
		if (persons.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<AdressBookForm>>(persons, HttpStatus.OK);
	}

}
