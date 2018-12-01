package com.adressbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.adressbook.logic.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

	@Query("select p from Person p where p.personId = :id")
	public Person findPersonById(@Param("id") Long id);

	@Query("select p from Person p where p.firstName = :firstName and p.secondName = :secondName")
	public Person findPersonByName(@Param("firstName") String firstName, @Param("secondName") String secondName);
}
