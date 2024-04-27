package com.example.luckySystem.exceptions;

public class EmployeeIDAlreadyExistsException extends RuntimeException {
    public EmployeeIDAlreadyExistsException(String message) {
        super(message);
    }
}
