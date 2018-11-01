package com.adressbook.controller;

import java.util.LinkedList;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.adressbook.logic.AdressBookForm;
import com.adressbook.service.PhoneBookService;

@Transactional
@Configuration
@RestController
@RequestMapping("/")
public class PhoneBookController {

	@Autowired
	private PhoneBookService pb;

	@PostMapping("/add_person")
	public @ResponseBody void addPerson(@Valid @RequestBody AdressBookForm abf) {
		pb.createPerson(abf);
		//return abf.toString();
	}

	/*
	 * @PostMapping("/add_number") public @ResponseBody String
	 * addNumber(@Valid @RequestBody AdressBookForm abf) {
	 * 
	 * }
	 * 
	 * @PostMapping("/add_email") public @ResponseBody String
	 * addEmail(@Valid @RequestBody AdressBookForm abf) {
	 * 
	 * }
	 */

	@PutMapping("/edit_person/{id}")
	public String editPerson(@PathVariable(value = "id") long id, @Valid @RequestBody AdressBookForm abf) {
		pb.updatePerson(id, abf);
		return abf.toString();
	}

	@DeleteMapping("/delete_person/{id}")
	public String deletePerson(@PathVariable(value = "id") long id) {
		pb.delPerson(id);
		return "Contact is deleted";
	}

	@RequestMapping("/show_person")
	public List<AdressBookForm> showPerson(/*@PathVariable(value = "id") long id*/) {
	    List<AdressBookForm> list = new LinkedList<AdressBookForm>();
	    list.add(pb.getPersonData(1l));
		return list;
	}

	@RequestMapping("/show_persons")
	public List<AdressBookForm> showAllPersons() {
		return pb.showAllContacts();
	}

}
