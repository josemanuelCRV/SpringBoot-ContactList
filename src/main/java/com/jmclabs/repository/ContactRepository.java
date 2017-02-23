package com.jmclabs.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jmclabs.entity.Contact;

@Repository("contactRepository")
public interface ContactRepository extends JpaRepository<Contact, Serializable> {

	public abstract Contact findById(int id);
	
	
	
}
