package com.xy.common.exception;

public class NoDefinitionException extends Exception {

    private String name;

    public NoDefinitionException() {
    }

    public NoDefinitionException(String name) {
        super("No named '" + name + "' available");
        this.name = name;
    }

    public NoDefinitionException(String name, String message) {
        super("No named '" + name + "' available");
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
