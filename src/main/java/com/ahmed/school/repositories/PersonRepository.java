package com.ahmed.school.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ahmed.school.models.Course;
import com.ahmed.school.models.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

	Optional<Person> findByEmail(String email);

	@Query(value = "SELECT c FROM Person c WHERE c.studyClass.classId = ?1")
	Page<Person> getClassStudents(int classId, Pageable pageable);

	Page<Person> findAllByCoursesContaining(Course course, Pageable pageable);

	boolean existsByEmail(String email);

}
