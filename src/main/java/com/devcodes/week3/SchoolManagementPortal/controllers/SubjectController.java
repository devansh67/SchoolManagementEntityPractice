package com.devcodes.week3.SchoolManagementPortal.controllers;

import com.devcodes.week3.SchoolManagementPortal.dtos.SubjectDTO;
import com.devcodes.week3.SchoolManagementPortal.services.interfaces.SubjectService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/subjects")
public class SubjectController {

    private final SubjectService subjectService;

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping("/{id}")
    public SubjectDTO getSubjectById(@PathVariable Long id) {
        return subjectService.getSubjectById(id);
    }

    @PostMapping
    public SubjectDTO saveSubject(@Valid @RequestBody SubjectDTO subjectDTO) {
        return subjectService.saveSubjectDetails(subjectDTO);
    }

    @PostMapping("/{subjectId}/professors/{professorId}")
    public SubjectDTO linkSubjectToProfessor(@PathVariable Long subjectId, @PathVariable Long professorId) {
        return subjectService.linkSubjectToProfessor(subjectId, professorId);
    }
}
