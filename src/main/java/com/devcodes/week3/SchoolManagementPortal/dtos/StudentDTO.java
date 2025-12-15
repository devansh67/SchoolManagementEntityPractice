package com.devcodes.week3.SchoolManagementPortal.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class StudentDTO {

    Long id;

    @NotEmpty(message = "Student name is mandatory")
    @Size(min = 1, max = 25, message = "Student name should have minimum 1 characters and maximum of 25 characters")
    String name;

    Set<SubjectDTO> subjects;
}
