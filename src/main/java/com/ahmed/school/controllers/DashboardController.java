package com.ahmed.school.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ahmed.school.models.Person;
import com.ahmed.school.services.servicesImp.PersonServiceImp;

@Controller
public class DashboardController {

	private final PersonServiceImp personService;

	public DashboardController(PersonServiceImp personService) {
		this.personService = personService;
	}

	@GetMapping("/dashboard")
	public String getDashboardPage(Authentication authentication, Model model) {
		Person person = personService.getPerson(authentication.getName());
		model.addAttribute("person", person);
		return "dashboard";
	}
}
