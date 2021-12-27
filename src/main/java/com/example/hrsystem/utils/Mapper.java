package com.example.hrsystem.utils;

import com.example.hrsystem.employee.Employee;
import com.example.hrsystem.employee.Gender;
import com.example.hrsystem.employee.dtos.EmployeeCreationDTO;
import com.example.hrsystem.employee.dtos.EmployeeGetDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Component
public class Mapper {
    public EmployeeGetDTO toDto(Employee employee) {
        Long empId = employee.getId();
        String empName = employee.getName();
        Long empTeam = employee.getTeam();
        String empDepartment = employee.getDepartment();
        Gender empGender = employee.getGender();
        LocalDateTime employeeGradDate = employee.getGradDate();
        LocalDateTime employeeDateOfBirth = employee.getDob();
        Double empSalary = employee.getSalary();
        Set<String> empExpert = new HashSet<>(employee.getExperties());

        Long mngId = (employee.getManager() == null) ? 0L : employee.getManager().getId();
        return new EmployeeGetDTO(empId, new EmployeeCreationDTO(empName, empSalary, empDepartment, empTeam, empGender, employeeDateOfBirth, employeeGradDate, empExpert, mngId));
    }

    public Employee toEmployee(EmployeeCreationDTO employeeCreationDTO, Employee manager) {

        String empName = employeeCreationDTO.getName();
        Long empTeam = employeeCreationDTO.getTeam();
        String empDepartment = employeeCreationDTO.getDepartment();
        Gender empGender = employeeCreationDTO.getGender();
        LocalDateTime employeeGradDate = employeeCreationDTO.getGradDate();
        LocalDateTime employeeDateOfBirth = employeeCreationDTO.getDob();
        Double empSalary = employeeCreationDTO.getSalary();
        Set<String> empExpert = new HashSet<>(employeeCreationDTO.getExpertises());

        Employee employee = new Employee(empName, empSalary, employeeDateOfBirth, empGender, employeeGradDate, empExpert, empDepartment, empTeam);
        employee.setManager(manager);
        return employee;
    }

}
