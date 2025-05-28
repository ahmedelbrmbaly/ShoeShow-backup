package iti.jets.exceptions;



// If input fails business rule validations (e.g., total amount must be positive)
public class ValidationException extends RuntimeException{

    public ValidationException(String message) {
        super(message);
    }
}

