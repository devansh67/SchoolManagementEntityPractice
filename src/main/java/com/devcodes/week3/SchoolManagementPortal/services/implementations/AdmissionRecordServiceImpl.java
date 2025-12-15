package com.devcodes.week3.SchoolManagementPortal.services.implementations;

import com.devcodes.week3.SchoolManagementPortal.dtos.AdmissionRecordDTO;
import com.devcodes.week3.SchoolManagementPortal.entities.AdmissionRecordEntity;
import com.devcodes.week3.SchoolManagementPortal.entities.StudentEntity;
import com.devcodes.week3.SchoolManagementPortal.exceptions.ResourceNotFoundException;
import com.devcodes.week3.SchoolManagementPortal.repositories.AdmissionRecordRepository;
import com.devcodes.week3.SchoolManagementPortal.repositories.StudentRepository;
import com.devcodes.week3.SchoolManagementPortal.services.interfaces.AdmissionRecordService;
import com.devcodes.week3.SchoolManagementPortal.utils.ValidationUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdmissionRecordServiceImpl implements AdmissionRecordService {

    private final AdmissionRecordRepository admissionRecordRepository;
    private final ModelMapper modelMapper;
    private final StudentRepository studentRepository;
    private final ValidationUtils validationUtils;

    public AdmissionRecordServiceImpl(AdmissionRecordRepository admissionRecordRepository, ModelMapper modelMapper, StudentRepository studentRepository, ValidationUtils validationUtils) {
        this.admissionRecordRepository = admissionRecordRepository;
        this.modelMapper = modelMapper;
        this.studentRepository = studentRepository;
        this.validationUtils = validationUtils;
    }

    @Override
    public AdmissionRecordDTO getAdmissionRecordById(Long id) {
        Optional<AdmissionRecordEntity> admissionRecordEntity = admissionRecordRepository.findById(id);
        validationUtils.checkIfResourceExistsOrThrowError(
                admissionRecordEntity,
                "Admission record is not available with the provided id"
                );
        return modelMapper.map(admissionRecordEntity, AdmissionRecordDTO.class);
    }

    @Override
    public AdmissionRecordDTO saveAdmissionRecordDTO(AdmissionRecordDTO admissionRecordDTO) {
        AdmissionRecordEntity admissionRecordEntity = admissionRecordRepository.save(modelMapper.map(admissionRecordDTO, AdmissionRecordEntity.class));
        return modelMapper.map(admissionRecordEntity, AdmissionRecordDTO.class);
    }

    @Override
    public AdmissionRecordDTO linkStudentToAdmissionRecord(Long admissionRecordId, Long studentId) {
        StudentEntity studentEntity = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found " + studentId));

        AdmissionRecordEntity admissionRecordEntity = admissionRecordRepository.findById(admissionRecordId)
                .orElseThrow(() -> new ResourceNotFoundException("Admission record not found " + admissionRecordId));


        admissionRecordEntity.setStudent(studentEntity);
        AdmissionRecordEntity savedEntity = admissionRecordRepository.save(admissionRecordEntity);
        return modelMapper.map(savedEntity, AdmissionRecordDTO.class);
    }
}
