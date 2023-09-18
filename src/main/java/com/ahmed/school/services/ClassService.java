package com.ahmed.school.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.ahmed.school.models.Person;
import com.ahmed.school.models.StudyClass;

import jakarta.validation.Valid;

public interface ClassService {

	Page<StudyClass> getAllClasses(int curPage, String sortBy, String sortDir);

	List<StudyClass> getAllClasses();

	Page<Person> getClassStudents(int classId, int curPage, String sortBy, String sortDir);

	void addNewClass(@Valid StudyClass studyClass);

	StudyClass getClassById(int classId);

}
