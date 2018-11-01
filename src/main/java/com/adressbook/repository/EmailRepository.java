package com.adressbook.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.adressbook.logic.Email;

@Repository
public interface EmailRepository extends JpaRepository<Email, Long>{

	@Query("select e from Email e where e.person.personId = :id")
	public List<Email> findPersonEmailObjects(@Param("id") Long id);
	
	@Query("select e.email from Email e where e.person.personId = :id")
	public List<String> findPersonEmails(@Param("id") Long id);
}
