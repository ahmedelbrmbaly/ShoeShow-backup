package iti.jets.exceptions;

import java.time.LocalDateTime;

public record ErrorResponseDTO (
    LocalDateTime timestamp,
    String message,
    String path,
    int status,
    String error
) {}
