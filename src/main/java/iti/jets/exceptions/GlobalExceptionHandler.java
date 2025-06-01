package iti.jets.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponseDTO> handleBadCredentials(BadCredentialsException ex, HttpServletRequest request) {
        ErrorResponseDTO error = new ErrorResponseDTO(
                LocalDateTime.now(),
                ex.getMessage(),
                request.getRequestURI(),
                401,
                "Unauthorized"
        );
        LOGGER.error("Bad credentials: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponseDTO> handleAccessDenied(AccessDeniedException ex, HttpServletRequest request) {
        ErrorResponseDTO error = new ErrorResponseDTO(
                LocalDateTime.now(),
                ex.getMessage(),
                request.getRequestURI(),
                403,
                "Access Denied"
        );
        LOGGER.error("Access denied: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
    }
    @ExceptionHandler(AuthorizationDeniedException.class)
    public ResponseEntity<ErrorResponseDTO> handleAuthorizationDenied(AuthorizationDeniedException ex, HttpServletRequest request) {
        ErrorResponseDTO errorResponse = new ErrorResponseDTO(
                LocalDateTime.now(),
                ex.getMessage(),
                request.getRequestURI(),
                403,
                "Forbidden"
        );
        LOGGER.error("Authorization denied: {}", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDTO> handleValidationErrors(MethodArgumentNotValidException ex, HttpServletRequest request) {
        StringBuilder errorMessages = new StringBuilder();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errorMessages.append(error.getField()).append(": ").append(error.getDefaultMessage()).append("; ")
        );

        ErrorResponseDTO errorResponse = new ErrorResponseDTO(
                LocalDateTime.now(),
                errorMessages.toString().trim(),
                request.getRequestURI(),
                HttpStatus.BAD_REQUEST.value(),
                "Validation Failed"
        );

        LOGGER.error("Validation failed: {}", errorMessages.toString().trim());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    ResponseEntity<ErrorResponseDTO> handleResourceNotFound(ResourceNotFoundException ex, HttpServletRequest request) {
        ErrorResponseDTO errorResponse = new ErrorResponseDTO(
                LocalDateTime.now(),
                ex.getMessage(),
                request.getRequestURI(),
                404,
                "Not Found");
        LOGGER.error("Resource not found: {}", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ConflictException.class)
    ResponseEntity<ErrorResponseDTO> handleConflict(ConflictException ex, HttpServletRequest request) {
        ErrorResponseDTO errorResponse = new ErrorResponseDTO(
                LocalDateTime.now(),
                ex.getMessage(),
                request.getRequestURI(),
                409,
                "Conflict");
        LOGGER.error("Conflict: {}", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ValidationException.class)
    ResponseEntity<ErrorResponseDTO> handleValidation(ValidationException ex, HttpServletRequest request) {
        ErrorResponseDTO errorResponse = new ErrorResponseDTO(
                LocalDateTime.now(),
                ex.getMessage(),
                request.getRequestURI(),
                400,
                "Bad Request");
        LOGGER.error("Validation error: {}", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnauthorizedException.class)
    ResponseEntity<ErrorResponseDTO> handleUnauthorized(UnauthorizedException ex, HttpServletRequest request) {
        ErrorResponseDTO errorResponse = new ErrorResponseDTO(
                LocalDateTime.now(),
                ex.getMessage(),
                request.getRequestURI(),
                401,
                "Unauthorized");
        LOGGER.error("Unauthorized: {}", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(BadRequestException.class)
    ResponseEntity<ErrorResponseDTO> handleBadRequest(BadRequestException ex, HttpServletRequest request) {
        ErrorResponseDTO errorResponse = new ErrorResponseDTO(
                LocalDateTime.now(),
                ex.getMessage(),
                request.getRequestURI(),
                400,
                "Bad Request");
        LOGGER.error("Bad request: {}", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ForbiddenException.class)
    ResponseEntity<ErrorResponseDTO> handleForbidden(ForbiddenException ex, HttpServletRequest request) {
        ErrorResponseDTO errorResponse = new ErrorResponseDTO(
                LocalDateTime.now(),
                ex.getMessage(),request.getRequestURI(),
                403,
                "Forbidden");
        LOGGER.error("Forbidden: {}", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(FileStorageException.class)
    ResponseEntity<ErrorResponseDTO> handleFileStorageException(FileStorageException ex, HttpServletRequest request) {
        ErrorResponseDTO errorResponse = new ErrorResponseDTO(
                LocalDateTime.now(),
                ex.getMessage(),
                request.getRequestURI(),
                500,
                "Upload Failed");
        LOGGER.error("File storage error: {}", ex.getMessage(), ex);
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    ResponseEntity<ErrorResponseDTO> handleGenericException(Exception ex, HttpServletRequest request) {
        ErrorResponseDTO errorResponse = new ErrorResponseDTO(
                LocalDateTime.now(),
                "An unexpected error occurred: " + ex.getMessage(),
                request.getRequestURI(),
                500,
                "Internal Server Error");
        LOGGER.error("Internal server error: {}", ex.getMessage(), ex);
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
