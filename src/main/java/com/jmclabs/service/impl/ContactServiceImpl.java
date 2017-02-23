package com.jmclabs.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.jmclabs.component.ContactConverter;
import com.jmclabs.entity.Contact;
import com.jmclabs.model.ContactModel;
import com.jmclabs.repository.ContactRepository;
import com.jmclabs.service.ContactService;

@Service("contactServiceImpl")
public class ContactServiceImpl implements ContactService {

	@Autowired
	@Qualifier("contactRepository")
	private ContactRepository contactRepository;

	@Autowired
	@Qualifier("contactConverter")
	private ContactConverter contactConverter;

	@Override
	public ContactModel addContact(ContactModel contactModel) {
		// creamos un converter de contactModel a ContactEntity
		// pasamos un model a una entity para guardarlo en db

		// devueve entidad contacto
		Contact contactEntity = contactRepository
				.save(contactConverter.convertContactModel2ContactEntity(contactModel));

		// convertimos la entidad anterior a un modelo
		return contactConverter.convertContactEntity2ContactModel(contactEntity);
	}

	// recorre y transforma todas las entity a model
	@Override
	public List<ContactModel> listAllContacts() {
		List<Contact> contactsEntity = contactRepository.findAll();
		List<ContactModel> contactModel = new ArrayList<ContactModel>();
		// cada contact de la entity
		for (Contact contact : contactsEntity) {
			// añade cada contacto de la entidad y lo pasa a un model
			contactModel.add(contactConverter.convertContactEntity2ContactModel(contact));
		}
		return contactModel;
	}

	@Override
	public Contact findContactById(int id) {
		return contactRepository.findById(id);
	}

	public ContactModel findContactByIdModel(int id) {
		return contactConverter.convertContactEntity2ContactModel(findContactById(id));
	}

	@Override
	public void removeContact(int id) {
		Contact contact = findContactById(id);
		if (null != contact) {
			contactRepository.delete(contact);
		}

	}

}
