package com.example.springbootproject.exception;

public class NotFoundException extends RuntimeException{

    private static final String message = "NOT FOUND %s WITH NAME = ";
    private String name;
    private String className;

    public NotFoundException(String className, String name) {
        super(message);
        this.name = name;
        this.className = className;
    }

    @Override
    public String getMessage() {
        return String.format(super.getMessage(), className) + name;
    }
}
