package com.devcodes.week3.SchoolManagementPortal.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "professor")
public class ProfessorEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    Long id;

    String title;

    // OWNING SIDE: Professor owns the join table for Professor-Student relationship
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "professor_student_mapping",
            joinColumns = @JoinColumn(name = "professor_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    Set<StudentEntity> students = new HashSet<>();

    // INVERSE SIDE: One Professor can teach Many Subjects
    // mappedBy = "professor" means SubjectEntity owns the foreign key (professor_id)
    // CASCADE: Using PERSIST and MERGE only (not ALL) to avoid accidental deletion of subjects
    @OneToMany(mappedBy = "professor", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonIgnore
    List<SubjectEntity> subjectList = new ArrayList<>();

}
