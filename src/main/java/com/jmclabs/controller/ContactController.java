package com.jmclabs.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.jmclabs.constant.ViewConstant;
import com.jmclabs.model.ContactModel;
import com.jmclabs.service.ContactService;

// TODO: Auto-generated Javadoc
/**
 * The Class ContactController.
 */

@Controller
@RequestMapping("/contacts")
public class ContactController {

	/** The Constant LOG. */
	private static final Log LOG = LogFactory.getLog(ContactController.class);

	/** The contact service. */
	@Autowired
	@Qualifier("contactServiceImpl")
	private ContactService contactService;

	/**
	 * Cancel.
	 *
	 * @return the string --> redirect:
	 */
	@GetMapping("/cancel")
	public String cancel() {
		return "redirect:/contacts/showcontacts";
	}

	// @PreAuthorize("hasRole('ROLE_USER')")
	// @PreAuthorize("hasRole('ROLE_ADMIN')")
	// @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	// @PreAuthorize("hasRole('ROLE_USER') and hasRole('ROLE_ADMIN')")
	// @PreAuthorize("permitAll()")
	// a nivel de método o clase para un Controller o Service

	/**
	 * Redirect contact form.
	 * 
	 * @PreAuthorize
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the string
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@GetMapping("/contactform")
	private String redirectContactForm(@RequestParam(name = "id", required = false) int id, Model model) {

		ContactModel contactModel = new ContactModel();
		if (id != 0) {
			// actualizar
			contactModel = contactService.findContactByIdModel(id);
		}
		// añadir nuevo
		model.addAttribute("contactModel", contactModel);
		return ViewConstant.CONTACT_FORM;
	}

	/**
	 * Adds the contact.
	 * 
	 * Utilizamos el de añadir también parar actualizar. El flag "result",
	 * si viene con datos será para actualizar. En caso contrario para añadir
	 * nuevo contacto.
	 *
	 * @param contactModel
	 *            the contact model
	 * @param model
	 *            the model
	 * @return the string
	 */
	@PostMapping("/addcontact")
	public String addContact(@ModelAttribute(name = "contactModel") ContactModel contactModel, Model model) {
		LOG.info("Method: addContact()  -- PARAMS: contactModel: " + contactModel.toString());
		if (null != contactService.addContact(contactModel)) {
			model.addAttribute("result", 1);
		} else {
			model.addAttribute("result", 0);
		}
		return "redirect:/contacts/showcontacts";
	}

	/**
	 * Show contacts.
	 *
	 * Utilizamos SecurityContextHolder para recuperar del contexto el usuario y
	 * guardarlo en UserDetails de Spring . Obtener el usuario autenticado en el
	 * servidor para mostrarlo en la view
	 *
	 * @return the model and view
	 */
	@GetMapping("/showcontacts")
	public ModelAndView showContacts() {
		ModelAndView mav = new ModelAndView(ViewConstant.CONTACTS);
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		mav.addObject("username", user.getUsername());
		// recuperamos el username para la vista contacts enviada al view y la
		// iteramos en th:each
		mav.addObject("contacts", contactService.listAllContacts());
		return mav;
	}

	/**
	 * Removes the contact.
	 *
	 * @param id
	 *            the id
	 * @return the model and view
	 */
	@GetMapping("/removecontact")
	public ModelAndView removeContact(@RequestParam(name = "id", required = true) int id) {
		contactService.removeContact(id);
		return showContacts();

	}

}
