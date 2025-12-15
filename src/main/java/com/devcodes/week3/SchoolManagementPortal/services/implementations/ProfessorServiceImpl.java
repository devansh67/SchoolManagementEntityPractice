package com.devcodes.week3.SchoolManagementPortal.services.implementations;

import com.devcodes.week3.SchoolManagementPortal.dtos.ProfessorDTO;
import com.devcodes.week3.SchoolManagementPortal.entities.ProfessorEntity;
import com.devcodes.week3.SchoolManagementPortal.entities.StudentEntity;
import com.devcodes.week3.SchoolManagementPortal.repositories.ProfessorRepository;
import com.devcodes.week3.SchoolManagementPortal.repositories.StudentRepository;
import com.devcodes.week3.SchoolManagementPortal.services.interfaces.ProfessorService;
import com.devcodes.week3.SchoolManagementPortal.utils.ValidationUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProfessorServiceImpl implements ProfessorService {
    private final ProfessorRepository professorRepository;
    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtils validationUtils;

    public ProfessorServiceImpl(ProfessorRepository professorRepository, StudentRepository studentRepository, ModelMapper modelMapper, ValidationUtils validationUtils) {
        this.professorRepository = professorRepository;
        this.studentRepository = studentRepository;
        this.modelMapper = modelMapper;
        this.validationUtils = validationUtils;
    }

    public ProfessorDTO getProfessorById(Long id) {
        Optional<ProfessorEntity> professorEntity = professorRepository.findById(id);

        validationUtils.checkIfResourceExistsOrThrowError(
                professorEntity,
                "Professor with the given id is not found: " + id
        );

        return modelMapper.map(professorEntity, ProfessorDTO.class);
    }

    @Override
    public ProfessorDTO saveProfessorDetails(ProfessorDTO professorDTO) {
        ProfessorEntity newProfessor = modelMapper.map(professorDTO, ProfessorEntity.class);
        ProfessorEntity savedProfessor = professorRepository.save(newProfessor);
        return modelMapper.map(savedProfessor, ProfessorDTO.class);
    }

    @Override
    public ProfessorDTO linkStudentToProfessor(Long professorId, Long studentId) {

        StudentEntity student = validationUtils.checkIfResourceExistsOrThrowError(
                studentRepository.findById(studentId),
                "Student with given studentId is not found"
        );

        ProfessorEntity professor = validationUtils.checkIfResourceExistsOrThrowError(
                professorRepository.findById(professorId),
                "Professor with the given professorId is not found"
        );

        return updateProfessorWithGivenStudent(professor, student);
    }

    @Override
    public List<ProfessorDTO> getAllProfessorsInformation() {
        return professorRepository
                .findAll()
                .stream()
                .map(professorEntity -> modelMapper.map(professorEntity, ProfessorDTO.class))
                .toList();
    }

    private ProfessorDTO updateProfessorWithGivenStudent(ProfessorEntity professorEntity, StudentEntity studentEntity) {
        professorEntity.getStudents().add(studentEntity);
        return modelMapper.map(professorEntity, ProfessorDTO.class);
    }
}
