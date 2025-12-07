package com.devcodes.week3.SchoolManagementPortal.services.interfaces;

import com.devcodes.week3.SchoolManagementPortal.dtos.SubjectDTO;

public interface SubjectService {
    SubjectDTO linkSubjectToProfessor(Long subjectId, Long professorId);

    SubjectDTO saveSubjectDetails(SubjectDTO subjectDTO);

    SubjectDTO getSubjectById(Long id);
}
