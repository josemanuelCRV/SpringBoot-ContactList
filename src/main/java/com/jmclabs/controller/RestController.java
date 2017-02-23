package com.jmclabs.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jmclabs.model.ContactModel;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/rest")
public class RestController {

	@GetMapping("/checkrest")
	public ResponseEntity<String> checkRest() {

		return new ResponseEntity<String>("OK!", HttpStatus.OK);
	}

	@GetMapping("/checkrestmodel")
	public ResponseEntity<ContactModel> checkRestModel() {

		ContactModel cm = new ContactModel(2, "Mike", "Halys", "888555", "Madrid");
		return new ResponseEntity<ContactModel>(cm, HttpStatus.OK);
	}

}
