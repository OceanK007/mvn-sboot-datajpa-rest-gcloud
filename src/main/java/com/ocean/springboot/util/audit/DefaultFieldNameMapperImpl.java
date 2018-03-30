package com.ocean.springboot.util.audit;

public class DefaultFieldNameMapperImpl implements FieldNameMapper
{
    public String getLabel(String fieldName) 
    {
        //Return fieldName as is
        //TODO: Format camelCase to humanized
        return fieldName;
    }
}
