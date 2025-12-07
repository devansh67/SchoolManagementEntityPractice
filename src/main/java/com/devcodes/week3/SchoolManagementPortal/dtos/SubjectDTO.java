package com.devcodes.week3.SchoolManagementPortal.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubjectDTO {
    Long id;

    @NotBlank(message = "Subject title is mandatory")
    String title;
}
