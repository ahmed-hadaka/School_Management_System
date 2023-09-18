package com.ahmed.school.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ahmed.school.models.StudyClass;

@Repository
public interface ClassRepository extends JpaRepository<StudyClass, Integer> {

}
