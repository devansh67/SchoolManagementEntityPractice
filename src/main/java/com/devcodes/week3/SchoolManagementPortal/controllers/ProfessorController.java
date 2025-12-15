package com.devcodes.week3.SchoolManagementPortal.controllers;

import com.devcodes.week3.SchoolManagementPortal.dtos.ProfessorDTO;
import com.devcodes.week3.SchoolManagementPortal.services.interfaces.ProfessorService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/professors")
public class ProfessorController {

    private final ProfessorService professorService;

    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }

    @GetMapping("/{id}")
    public ProfessorDTO getProfessorById(@PathVariable Long id) {
        return professorService.getProfessorById(id);
    }

    @PostMapping
    public ProfessorDTO saveProfessor(@Valid @RequestBody ProfessorDTO professorDTO) {
        return professorService.saveProfessorDetails(professorDTO);
    }

    @PostMapping("/{professorId}/students/{studentId}")
    public ProfessorDTO linkStudentToProfessor(@PathVariable Long professorId, @PathVariable Long studentId) {
        return professorService.linkStudentToProfessor(professorId, studentId);
    }

    @GetMapping
    public List<ProfessorDTO> getAllProfessors() {
        return professorService.getAllProfessorsInformation();
    }
}
