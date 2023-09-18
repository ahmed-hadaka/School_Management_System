package com.ahmed.school.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ahmed.school.models.Course;
import com.ahmed.school.models.Person;

public interface CourseRepository extends JpaRepository<Course, Integer> {

	Page<Course> findAllByPersonsContaining(Person student, Pageable pageable);

	Page<Course> findAllByPersonsNotContaining(Person student, Pageable pageable);

}
