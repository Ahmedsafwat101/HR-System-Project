package com.example.hrsystem.employee.dtos;

import com.example.hrsystem.employee.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.lang.Nullable;

import java.time.LocalDateTime;
import java.util.Set;

@AllArgsConstructor
@Data
public class EmployeeCreationDTO {

    private String name;

    private Double salary;

    private String department;

    private Long team;

    private Gender gender;

    private LocalDateTime dob;

    private LocalDateTime gradDate;

    private Set<String> expertises;

    @Nullable
    private Long mngId;
}
