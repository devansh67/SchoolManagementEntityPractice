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
@Table(name = "subject")
public class SubjectEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String title;

    // OWNING SIDE: Subject owns the foreign key (professor_id in subject table)
    // Many Subjects can (optionally) belong to One Professor (Many-to-One relationship)
    // professor is nullable so we can create a Subject first, then link a Professor later.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "professor_id")
    @JsonIgnore
    ProfessorEntity professor;

    // INVERSE SIDE: mappedBy = "subjects" means StudentEntity owns this relationship
    @ManyToMany(mappedBy = "subjects", fetch = FetchType.LAZY)
    @JsonIgnore
    Set<StudentEntity> students = new HashSet<>();
}
