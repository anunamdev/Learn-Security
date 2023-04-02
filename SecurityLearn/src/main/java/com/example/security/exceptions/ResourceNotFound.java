package com.example.security.exceptions;

public class ResourceNotFound extends RuntimeException{

   String resourceName;
   String fieldName;
   int fieldValue;

    public ResourceNotFound(String resourceName, String fieldName, int fieldValue) {
        super(String.format("%s Not found with %s : %s",resourceName,fieldName,fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
    public ResourceNotFound(String resourceName, String fieldName, String fieldValue) {
        super(String.format("%s Not found with %s : %s",resourceName,fieldName,fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
//        this.fieldValue = fieldValue;
    }
}
