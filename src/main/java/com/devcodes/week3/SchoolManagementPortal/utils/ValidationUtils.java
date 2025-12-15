package com.devcodes.week3.SchoolManagementPortal.utils;

import com.devcodes.week3.SchoolManagementPortal.entities.AdmissionRecordEntity;
import com.devcodes.week3.SchoolManagementPortal.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ValidationUtils {

    public <T> T checkIfResourceExistsOrThrowError(Optional<T> optional, String message) {
        return optional.orElseThrow(() -> new ResourceNotFoundException(message));
    }
}