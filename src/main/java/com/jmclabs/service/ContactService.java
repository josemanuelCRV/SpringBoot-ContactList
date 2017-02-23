package com.jmclabs.service;

import java.util.List;

import com.jmclabs.entity.Contact;
import com.jmclabs.model.ContactModel;


public interface ContactService { 

	
	public abstract ContactModel addContact(ContactModel contactModel);
	
	public abstract List<ContactModel> listAllContacts();
	
	public abstract Contact findContactById(int id);
	
	public ContactModel findContactByIdModel(int id);
	
	public abstract void removeContact(int id);
	
}
