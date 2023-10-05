package com.ahmed.school.services.servicesImp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ahmed.school.models.Address;
import com.ahmed.school.models.Course;
import com.ahmed.school.models.Person;
import com.ahmed.school.models.Role;
import com.ahmed.school.models.StudyClass;
import com.ahmed.school.repositories.ClassRepository;
import com.ahmed.school.repositories.CourseRepository;
import com.ahmed.school.repositories.PersonRepository;
import com.ahmed.school.repositories.RoleRepository;
import com.ahmed.school.services.PersonService;
import com.ahmed.school.util.Profile;

@Service
@Transactional(readOnly = true)
public class PersonServiceImp implements PersonService {

	private final CourseRepository courseRepository;
	private final PersonRepository personRepository;
	private final ClassRepository classRepository;
	private final RoleRepository roleRepository;
	private final PasswordEncoder passwordEncoder;
	private final Environment environment;

	@Autowired
	public PersonServiceImp(PersonRepository personRepository, ClassRepository classRepository,
			RoleRepository roleRepository, CourseRepository courseRepository, PasswordEncoder passwordEncoder,
			Environment environment) {
		this.personRepository = personRepository;
		this.classRepository = classRepository;
		this.roleRepository = roleRepository;
		this.courseRepository = courseRepository;
		this.passwordEncoder = passwordEncoder;
		this.environment = environment;
	}

	@Override
	public boolean isExistUser(String email) {
		return personRepository.existsByEmail(email);
	}

	@Override
	@Transactional // override the default(read-only) to: read-write
	public void createUser(Person person, int classId) {
		StudyClass studyClass = classRepository.findById(classId).orElseThrow();
		Role role = roleRepository.findById(2).orElseThrow();

		person.setStudyClass(studyClass);
		person.setRole(role);

		person.setPassword(passwordEncoder.encode(person.getPassword()));

		personRepository.save(person);
	}

	@Override
	public Page<Person> getAllStudents(int curPage, String sortBy, String sortDir) {
		int pageSize = Integer.parseInt(environment.getProperty("variables.pageSize"));
		Sort sort = sortDir.equals("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
		Pageable pageable = PageRequest.of(curPage - 1, pageSize, sort);

		Page<Person> students = personRepository.findAll(pageable);
		return students;
	}

	@Override
	public Person getPerson(int personId) {
		return personRepository.findById(personId).orElseThrow();
	}

	@Override
	public Person getPerson(String email) {
		return personRepository.findByEmail(email).orElseThrow();
	}

	@Override
	public Profile initializeUserProfile(int userId) {

		Person person = getPerson(userId);
		Profile profile = new Profile();

		profile.setName(person.getName());
		profile.setEmail(person.getEmail());
		profile.setMobileNumber(person.getMobileNumber());

		if (person.getAddress() != null) {

			Address address = person.getAddress();

			profile.setAddress1(address.getAddress1());
			profile.setAddress2(address.getAddress2());
			profile.setCity(address.getCity());
			profile.setStatus(address.getStatus());
			profile.setZipCode(address.getZipCode());
		}
		return profile;
	}

	@Override
	@Transactional
	public void updateUserProfile(Profile profile, int userId) {
		Person person = getPerson(userId);

		person.setName(profile.getName());
		person.setMobileNumber(profile.getMobileNumber());

		Address address;
		if (person.getAddress() != null)
			address = person.getAddress();
		else {
			address = new Address();
			person.setAddress(address);
		}

		address.setAddress1(profile.getAddress1());
		address.setAddress2(profile.getAddress2());
		address.setCity(profile.getCity());
		address.setStatus(profile.getStatus());
		address.setZipCode(profile.getZipCode());

		personRepository.save(person);
	}

	@Override
	@Transactional
	public void enrollCourse(int userId, int courseId) {

		Person person = getPerson(userId);
		Course course = courseRepository.findById(courseId).orElseThrow();

		person.getCourses().add(course);
		personRepository.save(person);

	}

	@Override
	@Transactional
	public void withdrawFromCourse(int userId, int courseId) {
		Person person = getPerson(userId);
		person.getCourses().removeIf(c -> c.getCourseId() == courseId);
		personRepository.save(person);
	}

	@Override
	@Transactional
	public void withdrawAllFromCourse(int courseId) {
		List<Person> persons = personRepository.findAll();
		for (Person person : persons) {
			person.getCourses().removeIf(c -> c.getCourseId() == courseId);
			personRepository.save(person);
		}
	}

	@Override
	@Transactional
	public void deleteStudent(int studentId) {
		personRepository.deleteById(studentId);
	}

	@Override
	@Transactional
	public void deleteCourseFromStudent(int studentId, int courseId) {
		Person person = getPerson(studentId);
		person.getCourses().removeIf(c -> c.getCourseId() == courseId);
		personRepository.save(person);
	}

	@Override
	@Transactional
	public boolean addStudentToCourse(String studentEmail, int courseId) {
		Optional<Person> person = personRepository.findByEmail(studentEmail);

		if (person.isPresent()) {
			enrollCourse(person.get().getPersonId(), courseId);
			return true;
		} else
			return false;
	}

	@Override
	@Transactional
	public boolean addStudentToClass(String studentEmail, int classId) {
		StudyClass studyClass = classRepository.findById(classId).orElse(new StudyClass());
		Optional<Person> person = personRepository.findByEmail(studentEmail);

		if (person.isPresent()) {
			person.get().setStudyClass(studyClass);
			personRepository.save(person.get());
			return true;
		}
		return false;
	}

}
