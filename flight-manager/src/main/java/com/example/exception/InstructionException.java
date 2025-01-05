package com.example.exception;

public class InstructionException extends RuntimeException{

    public InstructionException() {
    }

    public InstructionException(String message) {
        super(message);
    }
}
