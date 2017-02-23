package com.jmclabs.component;

import org.springframework.stereotype.Component;

import com.jmclabs.entity.Contact;
import com.jmclabs.model.ContactModel;

@Component("contactConverter")
public class ContactConverter {

	public Contact convertContactModel2ContactEntity(ContactModel contactModel) {
		Contact contactEntity = new Contact();
		contactEntity.setId(contactModel.getId());
		contactEntity.setFirstname(contactModel.getFirstname());
		contactEntity.setLastname(contactModel.getLastname());
		contactEntity.setCity(contactModel.getCity());
		contactEntity.setTelephone(contactModel.getTelephone());
		return contactEntity;
	}

	public ContactModel convertContactEntity2ContactModel(Contact contactEntity) {
		ContactModel contactModel = new ContactModel();
		contactModel.setId(contactEntity.getId());
		contactModel.setFirstname(contactEntity.getFirstname());
		contactModel.setLastname(contactEntity.getLastname());
		contactModel.setCity(contactEntity.getCity());
		contactModel.setTelephone(contactEntity.getTelephone());
		return contactModel;
	}

}
