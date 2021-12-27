package com.example.hrsystem.employee;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

public enum Gender {
    MALE("male"),
    FEMALE("female");

    private String genderType;

    Gender(String genderType) {
        this.genderType = genderType;
    }

    public String getGender() {
        return genderType;
    }
}

