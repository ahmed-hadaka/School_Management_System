package com.ahmed.school.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ahmed.school.models.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

}
