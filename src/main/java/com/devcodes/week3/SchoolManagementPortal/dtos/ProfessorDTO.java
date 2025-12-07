package com.devcodes.week3.SchoolManagementPortal.dtos;

import lombok.*;
import lombok.experimental.FieldDefaults;
import jakarta.validation.constraints.NotBlank;

import java.util.Set;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfessorDTO {
    Long id;

    @NotBlank(message = "Professor title is mandatory")
    String title;

    Set<StudentDTO> students;
}
