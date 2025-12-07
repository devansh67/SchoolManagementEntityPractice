package com.devcodes.week3.SchoolManagementPortal.services.interfaces;


import com.devcodes.week3.SchoolManagementPortal.dtos.AdmissionRecordDTO;

public interface AdmissionRecordService {
    AdmissionRecordDTO getAdmissionRecordById(Long id);

    AdmissionRecordDTO saveAdmissionRecordDTO(AdmissionRecordDTO admissionRecordDTO);

    AdmissionRecordDTO linkStudentToAdmissionRecord(Long admissionRecordId, Long studentId);
}
