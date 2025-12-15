package com.devcodes.week3.SchoolManagementPortal.services.implementations;

import com.devcodes.week3.SchoolManagementPortal.dtos.SubjectDTO;
import com.devcodes.week3.SchoolManagementPortal.services.interfaces.SubjectService;
import org.springframework.stereotype.Service;

@Service
public class SubjectServiceImpl implements SubjectService {
    @Override
    public SubjectDTO linkSubjectToProfessor(Long subjectId, Long professorId) {
        return null;
    }

    @Override
    public SubjectDTO saveSubjectDetails(SubjectDTO subjectDTO) {
        return null;
    }

    @Override
    public SubjectDTO getSubjectById(Long id) {
        return null;
    }
}
