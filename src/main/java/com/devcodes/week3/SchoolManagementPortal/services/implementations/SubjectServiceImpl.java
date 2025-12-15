package com.devcodes.week3.SchoolManagementPortal.services.implementations;

import com.devcodes.week3.SchoolManagementPortal.dtos.SubjectDTO;
import com.devcodes.week3.SchoolManagementPortal.entities.ProfessorEntity;
import com.devcodes.week3.SchoolManagementPortal.entities.SubjectEntity;
import com.devcodes.week3.SchoolManagementPortal.repositories.ProfessorRepository;
import com.devcodes.week3.SchoolManagementPortal.repositories.SubjectRepository;
import com.devcodes.week3.SchoolManagementPortal.services.interfaces.SubjectService;
import com.devcodes.week3.SchoolManagementPortal.utils.ValidationUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class SubjectServiceImpl implements SubjectService {

    private final SubjectRepository subjectRepository;
    private final ProfessorRepository professorRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtils validationUtils;

    public SubjectServiceImpl(SubjectRepository subjectRepository,
                              ProfessorRepository professorRepository,
                              ModelMapper modelMapper,
                              ValidationUtils validationUtils) {
        this.subjectRepository = subjectRepository;
        this.professorRepository = professorRepository;
        this.modelMapper = modelMapper;
        this.validationUtils = validationUtils;
    }

    @Override
    public SubjectDTO linkSubjectToProfessor(Long subjectId, Long professorId) {
        SubjectEntity subjectEntity = validationUtils.checkIfResourceExistsOrThrowError(
                subjectRepository.findById(subjectId),
                "Subject with the given subjectId is not found"
        );

        ProfessorEntity professorEntity = validationUtils.checkIfResourceExistsOrThrowError(
                professorRepository.findById(professorId),
                "Professor with the given professorId is not found"
        );

        subjectEntity.setProfessor(professorEntity);
        SubjectEntity savedSubject = subjectRepository.save(subjectEntity);
        return modelMapper.map(savedSubject, SubjectDTO.class);
    }

    @Override
    public SubjectDTO saveSubjectDetails(SubjectDTO subjectDTO) {
        // For creation we are not forcing a professor; SubjectEntity.professor should be nullable.
        SubjectEntity subjectEntity = modelMapper.map(subjectDTO, SubjectEntity.class);
        SubjectEntity savedSubject = subjectRepository.save(subjectEntity);
        return modelMapper.map(savedSubject, SubjectDTO.class);
    }

    @Override
    public SubjectDTO getSubjectById(Long id) {
        SubjectEntity subjectEntity = validationUtils.checkIfResourceExistsOrThrowError(
                subjectRepository.findById(id),
                "Subject with the given id is not found"
        );
        return modelMapper.map(subjectEntity, SubjectDTO.class);
    }
}
