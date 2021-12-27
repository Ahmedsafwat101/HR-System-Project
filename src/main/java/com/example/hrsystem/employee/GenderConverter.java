package com.example.hrsystem.employee;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
class GenderConverter implements AttributeConverter<Gender, String> {

    @Override
    public String convertToDatabaseColumn(Gender gender) {
        if (gender == null) {
            return null;
        }
        return gender.getGender();
    }

    @Override
    public Gender convertToEntityAttribute(String genderType) {
        if (genderType == null) return null;
        return Stream.of(Gender.values())
                .filter(g -> g.getGender().equals(genderType))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
