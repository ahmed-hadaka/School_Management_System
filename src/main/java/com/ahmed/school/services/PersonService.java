package com.ahmed.school.services;

import org.springframework.data.domain.Page;

import com.ahmed.school.models.Person;
import com.ahmed.school.models.Profile;

public interface PersonService {

	boolean isExistUser(String email);

	void createUser(Person person, int classId);

	Page<Person> getAllStudents(int curPage, String sortBy, String sortDir);

	Person getPerson(int personId);

	Person getPerson(String email);

	Profile initializeUserProfile(int userId);

	void updateUserProfile(Profile profile, int userId);

	void enrollCourse(int userId, int courseId);

	void withdrawFromCourse(int userId, int courseId);

	void withdrawAllFromCourse(int courseId);

	void deleteStudent(int studentId);

	void deleteCourseFromStudent(int studentId, int courseId);

	boolean addStudentToCourse(String studentEmail, int courseId);

	boolean addStudentToClass(String studentEmail, int classId);
}
