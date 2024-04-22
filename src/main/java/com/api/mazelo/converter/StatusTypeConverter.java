package com.api.mazelo.converter;

import com.api.mazelo.type.StatusType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class StatusTypeConverter implements AttributeConverter<StatusType, String> {
    @Override
    public String convertToDatabaseColumn(StatusType type) {
        if (type == null) {
            return null;
        } else {
            return type.name();
        }
    }

    @Override
    public StatusType convertToEntityAttribute(String type) {
        if (type == null) {
            return null;
        } else {
            return StatusType.valueOf(type.toUpperCase());
        }
    }
}
