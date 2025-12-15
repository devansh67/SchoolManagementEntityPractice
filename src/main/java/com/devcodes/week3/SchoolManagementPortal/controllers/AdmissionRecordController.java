package com.devcodes.week3.SchoolManagementPortal.controllers;

import com.devcodes.week3.SchoolManagementPortal.dtos.AdmissionRecordDTO;
import com.devcodes.week3.SchoolManagementPortal.services.interfaces.AdmissionRecordService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admission-records")
public class AdmissionRecordController {

    private final AdmissionRecordService admissionRecordService;

    public AdmissionRecordController(AdmissionRecordService admissionRecordService) {
        this.admissionRecordService = admissionRecordService;
    }

    @GetMapping("/{id}")
    public AdmissionRecordDTO getAdmissionRecordById(@PathVariable Long id) {
        return admissionRecordService.getAdmissionRecordById(id);
    }

    @PostMapping
    public AdmissionRecordDTO saveAdmissionRecord(@Valid @RequestBody AdmissionRecordDTO admissionRecordDTO) {
        return admissionRecordService.saveAdmissionRecordDTO(admissionRecordDTO);
    }

    @PostMapping("/{admissionRecordId}/students/{studentId}")
    public AdmissionRecordDTO linkStudentToAdmissionRecord(@PathVariable Long admissionRecordId, @PathVariable Long studentId) {
        return admissionRecordService.linkStudentToAdmissionRecord(admissionRecordId, studentId);
    }
}
