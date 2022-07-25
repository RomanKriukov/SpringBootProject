package com.example.springbootproject.exception;

public class NotFoundException extends RuntimeException{

    private static final String message = "NOT FOUND %s with %s = ";
    private Object value;
    private String varName;
    private String className;

    public NotFoundException(String className, String varName, Object value) {
        super(message);
        this.value = value;
        this.className = className;
        this.varName = varName;
    }

    @Override
    public String getMessage() {
        return String.format(super.getMessage(), className, varName) + value;
    }
}
