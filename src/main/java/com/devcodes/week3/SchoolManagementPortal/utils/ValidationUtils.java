package com.devcodes.week3.SchoolManagementPortal.utils;

import com.devcodes.week3.SchoolManagementPortal.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class ValidationUtils {

    public void checkIfResourceExistsOrThrowError(Boolean isRecordAvailable, String message) {
        if(!isRecordAvailable) {
            throw new ResourceNotFoundException(message);
        }
    }
}