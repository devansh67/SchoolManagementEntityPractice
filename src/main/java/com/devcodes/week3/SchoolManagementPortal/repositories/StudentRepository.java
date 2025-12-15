package com.devcodes.week3.SchoolManagementPortal.repositories;

import com.devcodes.week3.SchoolManagementPortal.entities.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, Long> {

    Optional<StudentEntity> findByName(String name);
}
