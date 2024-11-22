package com.kritica.restapi.exception;

public class ResourcesNotFound extends RuntimeException{

    private String resourceName;
    private String fieldName;
    private Long fieldId;
    private String message;

    public ResourcesNotFound(){

    }
    public ResourcesNotFound(String resourceName, String fieldName, String message) {
        super(String.format("%s not found with %s: %s", resourceName, message, fieldName));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.message = message;

    }

    public ResourcesNotFound(String resourceName, Long fieldId, String message) {
        super(String.format("%s not found with %s: %d", resourceName, message, fieldId));
        this.resourceName = resourceName;
        this.fieldId = fieldId;
        this.message = message;

    }
}