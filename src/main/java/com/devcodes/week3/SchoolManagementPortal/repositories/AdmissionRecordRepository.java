package com.devcodes.week3.SchoolManagementPortal.repositories;

import com.devcodes.week3.SchoolManagementPortal.entities.AdmissionRecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdmissionRecordRepository extends JpaRepository<AdmissionRecordEntity, Long> {

}
