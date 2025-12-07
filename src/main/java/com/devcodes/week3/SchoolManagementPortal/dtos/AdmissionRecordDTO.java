package com.devcodes.week3.SchoolManagementPortal.dtos;

import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.Range;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdmissionRecordDTO {
    Long id;

    @PositiveOrZero(message = "Salary has to be equal to or greater than 0")
    @Range(min = 10000, max = 1000000, message = "Salary should be between 10K and 10L")
    Double fees;

    StudentDTO student;
}
