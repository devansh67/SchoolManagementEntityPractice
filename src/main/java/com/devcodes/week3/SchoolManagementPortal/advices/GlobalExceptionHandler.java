package com.devcodes.week3.SchoolManagementPortal.advices;

import com.devcodes.week3.SchoolManagementPortal.exceptions.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * Handles ResourceNotFoundException - when a requested resource is not found
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<?>> handleResourceNotFound(ResourceNotFoundException exception) {
        logger.warn("Resource not found: {}", exception.getMessage());
        ApiError apiError = ApiError.builder()
                .status(HttpStatus.NOT_FOUND)
                .message(exception.getMessage())
                .build();
        return buildErrorResponseEntity(apiError);
    }

    /**
     * Handles NoSuchElementException - when trying to access an element that doesn't exist
     */
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ApiResponse<?>> handleNoSuchElement(NoSuchElementException exception) {
        logger.warn("Element not found: {}", exception.getMessage());
        ApiError apiError = ApiError.builder()
                .status(HttpStatus.NOT_FOUND)
                .message("Requested resource not found")
                .build();
        return buildErrorResponseEntity(apiError);
    }

    /**
     * Handles validation errors from @Valid annotations
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<?>> handleInputValidationError(MethodArgumentNotValidException exception) {
        List<String> errors = exception
                .getBindingResult()
                .getAllErrors()
                .stream()
                .map(error -> error.getDefaultMessage())
                .collect(Collectors.toList());

        logger.warn("Validation error: {}", errors);
        ApiError apiError = ApiError
                .builder()
                .status(HttpStatus.BAD_REQUEST)
                .message("Validation failed")
                .subErrors(errors)
                .build();
        return buildErrorResponseEntity(apiError);
    }

    /**
     * Handles IllegalArgumentException - when invalid arguments are passed
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse<?>> handleIllegalArgument(IllegalArgumentException exception) {
        logger.warn("Illegal argument: {}", exception.getMessage());
        ApiError apiError = ApiError.builder()
                .status(HttpStatus.BAD_REQUEST)
                .message(exception.getMessage())
                .build();
        return buildErrorResponseEntity(apiError);
    }

    /**
     * Handles ObjectOptimisticLockingFailureException - when concurrent updates conflict
     */
    @ExceptionHandler(ObjectOptimisticLockingFailureException.class)
    public ResponseEntity<ApiResponse<?>> handleOptimisticLockingFailure(ObjectOptimisticLockingFailureException exception) {
        logger.error("Optimistic locking failure: {}", exception.getMessage());
        ApiError apiError = ApiError.builder()
                .status(HttpStatus.CONFLICT)
                .message("The resource was modified by another transaction. Please refresh and try again.")
                .build();
        return buildErrorResponseEntity(apiError);
    }

    /**
     * Handles DataIntegrityViolationException - when database constraints are violated
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiResponse<?>> handleDataIntegrityViolation(DataIntegrityViolationException exception) {
        logger.error("Data integrity violation: {}", exception.getMessage());
        ApiError apiError = ApiError.builder()
                .status(HttpStatus.CONFLICT)
                .message("Data integrity violation. The operation cannot be completed.")
                .build();
        return buildErrorResponseEntity(apiError);
    }

    /**
     * Handles all other unhandled exceptions
     * This should be the last handler as it catches all exceptions
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> handleInternalServerError(Exception exception) {
        logger.error("Unexpected error occurred", exception);
        ApiError apiError = ApiError.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .message("An unexpected error occurred. Please try again later.")
                .build();
        return buildErrorResponseEntity(apiError);
    }

    /**
     * Helper method to build error response entity
     */
    private ResponseEntity<ApiResponse<?>> buildErrorResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(new ApiResponse<>(apiError), apiError.getStatus());
    }
}
