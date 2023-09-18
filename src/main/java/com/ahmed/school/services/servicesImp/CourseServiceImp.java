package com.ahmed.school.services.servicesImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ahmed.school.models.Course;
import com.ahmed.school.models.Person;
import com.ahmed.school.repositories.CourseRepository;
import com.ahmed.school.repositories.PersonRepository;
import com.ahmed.school.services.CourseService;

import jakarta.validation.Valid;

@Service
public class CourseServiceImp implements CourseService {

	private final PersonRepository personRepository;
	private final CourseRepository courseRepository;
	private final Environment environment;

	@Autowired
	public CourseServiceImp(PersonRepository personRepository, CourseRepository courseRepository,
			Environment environment) {
		this.personRepository = personRepository;
		this.courseRepository = courseRepository;
		this.environment = environment;
	}

	@Override
	public Page<Course> getUnEnrolledCourses(int studentId, int curPage, String sortBy, String sortDir) {
		int pageSize = Integer.parseInt(environment.getProperty("variables.pageSize"));
		Sort sort = sortDir.equals("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
		Pageable pageable = PageRequest.of(curPage - 1, pageSize, sort);
		Person person = personRepository.findById(studentId).orElseThrow();

		Page<Course> courses = courseRepository.findAllByPersonsNotContaining(person, pageable);

		return courses;
	}

	@Override
	public Page<Course> getEnrolledCourses(int studentId, int curPage, String sortBy, String sortDir) {
		int pageSize = Integer.parseInt(environment.getProperty("variables.pageSize"));
		Sort sort = sortDir.equals("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
		Pageable pageable = PageRequest.of(curPage - 1, pageSize, sort);

		Person person = personRepository.findById(studentId).orElseThrow();

		Page<Course> courses = courseRepository.findAllByPersonsContaining(person, pageable);

		return courses;
	}

	@Override
	public Page<Course> getAllCourses(int curPage, String sortBy, String sortDir) {
		int pageSize = Integer.parseInt(environment.getProperty("variables.pageSize"));
		Sort sort = sortDir.equals("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
		Pageable pageable = PageRequest.of(curPage - 1, pageSize, sort);

		return courseRepository.findAll(pageable);
	}

	@Override
	public Page<Person> getCourseStudents(int curPage, int courseId, String sortBy, String sortDir) {
		Course course = courseRepository.findById(courseId).orElseThrow();
		int pageSize = Integer.parseInt(environment.getProperty("variables.pageSize"));
		Sort sort = sortDir.equals("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
		Pageable pageable = PageRequest.of(curPage - 1, pageSize, sort);

		Page<Person> students = personRepository.findAllByCoursesContaining(course, pageable);
		return students;
	}

	@Override
	public void deleteStudentFromCourse(int studentId, int courseId) {
		Course course = courseRepository.findById(courseId).orElseThrow();
		course.getPersons().removeIf(p -> p.getPersonId() == studentId);
		courseRepository.save(course);
	}

	@Override
	public void addNewCourse(@Valid Course course) {
		courseRepository.save(course);
	}

	@Override
	public void deleteCourse(int courseId) {
		courseRepository.deleteById(courseId);
	}

	@Override
	public Course getCourseById(int courseId) {
		return courseRepository.findById(courseId).orElseThrow();
	}
}
