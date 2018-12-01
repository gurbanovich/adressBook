package com.adressbook.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.adressbook.logic.PhoneNumber;

@Repository
public interface PhoneNumberRepository extends JpaRepository<PhoneNumber, Long>{
	
	@Query("select n from PhoneNumber n where n.person.personId = :id")
	public List<PhoneNumber> findPersonNumberObject(@Param("id") Long id);
	
	@Query("select n.number from PhoneNumber n where n.person.personId = :id")
	public List<String> findPersonNumbers(@Param("id") Long id);
}
