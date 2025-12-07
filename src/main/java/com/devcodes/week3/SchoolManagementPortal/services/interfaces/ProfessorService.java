package com.devcodes.week3.SchoolManagementPortal.services.interfaces;

import com.devcodes.week3.SchoolManagementPortal.dtos.ProfessorDTO;

import java.util.List;

public interface ProfessorService {

    ProfessorDTO getProfessorById(Long id);

    ProfessorDTO saveProfessorDetails(ProfessorDTO professorDTO);

    ProfessorDTO linkStudentToProfessor(Long professorId, Long studentId);

    List<ProfessorDTO> getAllProfessorsInformation();
}
