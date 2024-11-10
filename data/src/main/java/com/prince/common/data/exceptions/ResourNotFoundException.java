package com.prince.data.commons.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourNotFoundException extends  RuntimeException{
    String resourceName;
    String fieldName;
    long fieldValue;

    public ResourNotFoundException(String resourceName, String fieldName, long fieldValue){
        super(String.format("%s not found with %s : %s", resourceName, fieldName, fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
}
