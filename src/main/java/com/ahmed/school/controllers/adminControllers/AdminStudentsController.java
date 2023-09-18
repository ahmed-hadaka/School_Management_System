package com.ahmed.school.controllers.adminControllers;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ahmed.school.models.Person;
import com.ahmed.school.services.servicesImp.PersonServiceImp;

@Controller
@RequestMapping("/admin")
public class AdminStudentsController {

	private final PersonServiceImp personService;

	public AdminStudentsController(PersonServiceImp personService) {
		this.personService = personService;
	}

	@GetMapping("/viewAllStudents/{curPage}")
	public String viewStudents(@PathVariable int curPage, @RequestParam String sortBy, @RequestParam String sortDir,
			Model model) {

		Page<Person> students = personService.getAllStudents(curPage, sortBy, sortDir);
		sortDir = sortDir.equals("asc") ? "desc" : "asc";

		model.addAttribute("students", students.getContent());
		model.addAttribute("pagesCount", students.getTotalPages());
		model.addAttribute("sortBy", sortBy);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("curPage", curPage);

		return "admin/view-all-students";
	}

	@GetMapping("/studentDetails")
	public String getStudentDetails(@RequestParam(required = true) int studentId, Model model) {
		Person student = personService.getPerson(studentId);
		model.addAttribute("student", student);
		return "admin/view-student-details";
	}

	@GetMapping("/deleteStudent")
	public String deleteStudent(@RequestParam(required = true) int studentId) {

		personService.deleteStudent(studentId);

		return "redirect:/admin/viewAllStudents/1?sortBy=name&sortDir=asc";
	}

}
