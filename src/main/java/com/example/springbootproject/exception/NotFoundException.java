package com.example.springbootproject.exception;

public class NotFoundException extends RuntimeException{

    private String name;

    public NotFoundException(String message, String name) {
        super(message);
        this.name = name;
    }

    @Override
    public String getMessage() {
        return super.getMessage() + name;
    }
}
