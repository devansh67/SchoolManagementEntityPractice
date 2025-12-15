package com.devcodes.week3.SchoolManagementPortal.controllers;

import com.devcodes.week3.SchoolManagementPortal.dtos.StudentDTO;
import com.devcodes.week3.SchoolManagementPortal.services.interfaces.StudentService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/{id}")
    public StudentDTO getStudentById(@PathVariable Long id) {
        return studentService.getStudentById(id);
    }

    @GetMapping("/by-name")
    public StudentDTO getStudentByName(@RequestParam("name") String name) {
        return studentService.getStudentByName(name);
    }

    @PostMapping
    public StudentDTO saveStudent(@Valid @RequestBody StudentDTO studentDTO) {
        return studentService.saveStudentDetails(studentDTO);
    }

    @PostMapping("/{studentId}/subjects/{subjectId}")
    public StudentDTO assignStudentToSubject(@PathVariable Long studentId, @PathVariable Long subjectId) {
        return studentService.assignStudentToSubject(studentId, subjectId);
    }

    @GetMapping
    public List<StudentDTO> getAllStudents() {
        return studentService.getAllStudentsInformation();
    }
}
