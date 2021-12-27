package com.example.hrsystem.employee.dtos;

import com.example.hrsystem.employee.Employee;
import com.example.hrsystem.employee.Gender;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@AllArgsConstructor
public class EmployeeGetDTO {

    private Long empId;
    private EmployeeCreationDTO creationDTO;

}
