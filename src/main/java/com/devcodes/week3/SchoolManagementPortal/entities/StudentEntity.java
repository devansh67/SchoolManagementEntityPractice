package com.devcodes.week3.SchoolManagementPortal.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.HashSet;
import java.util.Set;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "student")
public class StudentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;

    // INVERSE SIDE: mappedBy = "students" means ProfessorEntity owns this relationship
    @ManyToMany(mappedBy = "students", fetch = FetchType.LAZY)
    @JsonIgnore
    Set<ProfessorEntity> professors = new HashSet<>();


    // OWNING SIDE: Student owns the join table for Student-Subject relationship
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "student_subject_mapping",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id")
    )
    Set<SubjectEntity> subjects = new HashSet<>();

    // INVERSE SIDE: One Student has One AdmissionRecord
    // mappedBy = "student" means the foreign key is managed by AdmissionRecordEntity
    // CASCADE: When a student is deleted, their admission record is also deleted
    @OneToOne(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    AdmissionRecordEntity admissionRecord;
}
