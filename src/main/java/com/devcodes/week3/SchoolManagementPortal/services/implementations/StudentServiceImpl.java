package com.devcodes.week3.SchoolManagementPortal.services.implementations;

import com.devcodes.week3.SchoolManagementPortal.dtos.StudentDTO;
import com.devcodes.week3.SchoolManagementPortal.entities.StudentEntity;
import com.devcodes.week3.SchoolManagementPortal.entities.SubjectEntity;
import com.devcodes.week3.SchoolManagementPortal.repositories.StudentRepository;
import com.devcodes.week3.SchoolManagementPortal.repositories.SubjectRepository;
import com.devcodes.week3.SchoolManagementPortal.services.interfaces.StudentService;
import com.devcodes.week3.SchoolManagementPortal.utils.ValidationUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtils validationUtils;

    public StudentServiceImpl(StudentRepository studentRepository, SubjectRepository subjectRepository, ModelMapper modelMapper, ValidationUtils validationUtils) {
        this.studentRepository = studentRepository;
        this.subjectRepository = subjectRepository;
        this.modelMapper = modelMapper;
        this.validationUtils = validationUtils;
    }

    @Override
    public StudentDTO getStudentById(Long id) {
        StudentEntity studentEntity = validationUtils.checkIfResourceExistsOrThrowError(studentRepository.findById(id), "No student found for the given id");

        return modelMapper.map(studentEntity, StudentDTO.class);
    }

    @Override
    public StudentDTO getStudentByName(String name) {
        Optional<StudentEntity> studentEntity = studentRepository.findByName(name);
        validationUtils.checkIfResourceExistsOrThrowError(studentEntity, "No student found for the given name");

        return modelMapper.map(studentEntity, StudentDTO.class);
    }

    @Override
    public StudentDTO assignStudentToSubject(Long studentId, Long subjectId) {
        StudentEntity studentEntity = validationUtils.checkIfResourceExistsOrThrowError(studentRepository.findById(studentId), "No student found for the given studentId");

        SubjectEntity subjectEntity = validationUtils.checkIfResourceExistsOrThrowError(subjectRepository.findById(subjectId), "No subject found for the given subjectId");

        studentEntity
                .getSubjects()
                .add(subjectEntity);
        return modelMapper.map(studentEntity, StudentDTO.class);
    }

    @Override
    public StudentDTO saveStudentDetails(StudentDTO studentDTO) {
        StudentEntity studentEntity = modelMapper.map(studentDTO, StudentEntity.class);
        StudentEntity savedStudentEntity = studentRepository.save(studentEntity);

        return modelMapper.map(savedStudentEntity, StudentDTO.class);
    }

    @Override
    public List<StudentDTO> getAllStudentsInformation() {
        return studentRepository
                .findAll()
                .stream()
                .map(studentEntity -> modelMapper.map(studentEntity, StudentDTO.class))
                .toList();
    }
}