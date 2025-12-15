package com.devcodes.week3.SchoolManagementPortal.services.interfaces;

import com.devcodes.week3.SchoolManagementPortal.dtos.StudentDTO;

import java.util.List;

public interface StudentService {

    StudentDTO getStudentById(Long id);

    StudentDTO getStudentByName(String name);

    StudentDTO assignStudentToSubject(Long studentId, Long subjectId);

    StudentDTO saveStudentDetails(StudentDTO studentDTO);

    List<StudentDTO> getAllStudentsInformation();
}