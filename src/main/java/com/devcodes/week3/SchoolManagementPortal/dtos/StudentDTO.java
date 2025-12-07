package com.devcodes.week3.SchoolManagementPortal.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentDTO {

    Long id;

    @NotEmpty(message = "Student title is mandatory")
    @Size(min = 1, max = 25, message = "Student title should have minimum 1 characters and maximum of 25 characters")
    String title;

    Set<SubjectDTO> subjects;
}
