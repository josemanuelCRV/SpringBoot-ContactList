package com.jmclabs.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jmclabs.constant.ViewConstant;

// TODO: Auto-generated Javadoc
/**
 * The Class LoginController.
 */

@Controller
public class LoginController {

	/** The Constant LOG. */
	private static final Log LOG = LogFactory.getLog(LoginController.class);

	
	/**
	 * Show login form.
	 *
	 * @param model the model
	 * @param error the error
	 * @param logout the logout
	 * @return the string
	 */
	@GetMapping("/login")
	public String showLoginForm(Model model, @RequestParam(name = "error", required = false) String error,
			@RequestParam(name = "logout", required = false) String logout) {
		LOG.info("Method: showLoginForm() -- PARAMS: error: " + error + ", logout: " + logout);
		// pasamos el objeto error si existe y logout
		model.addAttribute("error", error);
		model.addAttribute("logout", logout);
		LOG.info("Returning to login view");
		return ViewConstant.LOGIN;
	}

	/**
	 * Login check. /loginsuccess
	 *
	 * @return the string
	 */
	@GetMapping({ "/loginsuccess", "/" })
	public String loginCheck() {
		LOG.info("Method: loginCheck()  --");
		LOG.info("Returning to contacts view");
		return "redirect:/contacts/showcontacts";
	}

}

/*
 * 
 * @GetMapping("/") public String redirectToLogin() {
 * LOG.info("Method: redirectToLogin()"); return "redirect:/login"; }
 * 
 * 
 * 
 * 
 * @GetMapping("/login") public String showLoginForm(Model
 * model, @RequestParam(name = "error", required = false) String error,
 * 
 * @RequestParam(name = "logout", required = false) String logout) {
 * LOG.info("Method: showLoginForm() -- PARAMS: error: " + error + ", logout: "
 * + logout); // pasamos el objeto error si existe y logout
 * model.addAttribute("error", error); model.addAttribute("logout", logout); //
 * le pasamos el objeto userCredentials que viene desde formulario
 * model.addAttribute("userCredentials", new UserCredential());
 * LOG.info("Returning to login view"); return ViewConstant.LOGIN;
 * 
 * }
 * 
 * 
 * @PostMapping("logincheck") public String loginCheck(@ModelAttribute(name =
 * "userCredentials") UserCredential userCredential) {
 * LOG.info("Method: loginCheck()  -- PARAMS: userCredentials: " +
 * userCredential.toString()); if (userCredential.getUsername().equals("user")
 * && userCredential.getPassword().equals("user")) {
 * LOG.info("Returning to contacts view"); return
 * "redirect:/contacts/showcontacts"; } LOG.info("Returning to login error");
 * return "redirect:/login?error"; }
 * 
 * 
 */
