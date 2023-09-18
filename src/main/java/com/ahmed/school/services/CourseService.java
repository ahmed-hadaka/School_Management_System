package com.ahmed.school.services;

import org.springframework.data.domain.Page;

import com.ahmed.school.models.Course;
import com.ahmed.school.models.Person;

import jakarta.validation.Valid;

public interface CourseService {

	Page<Course> getUnEnrolledCourses(int studentId, int curPage, String sortBy, String sortDir);

	Page<Course> getEnrolledCourses(int studentId, int curPage, String sortBy, String sortDir);

	Page<Course> getAllCourses(int curPage, String sortBy, String sortDir);

	Page<Person> getCourseStudents(int curPage, int courseId, String sortBy, String sortDir);

	void deleteStudentFromCourse(int studentId, int courseId);

	void addNewCourse(@Valid Course course);

	void deleteCourse(int courseId);

	Course getCourseById(int courseId);
}
