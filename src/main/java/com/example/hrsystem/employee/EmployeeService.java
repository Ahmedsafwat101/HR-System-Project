package com.example.hrsystem.employee;

import com.example.hrsystem.employee.dtos.EmployeeCreationDTO;
import com.example.hrsystem.employee.dtos.EmployeeGetDTO;
import com.example.hrsystem.utils.Mapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    private EmployeeDAO repository;
    private Mapper mapper;

    public EmployeeService(EmployeeDAO repository, Mapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public void addEmployee(EmployeeCreationDTO employeeCreationDTO) {
        Long mngId = employeeCreationDTO.getMngId();
        //checkIfExists(mngId);
        Employee manager = repository.getById(mngId);
        Employee employeeToSave = mapper.toEmployee(employeeCreationDTO, manager);
        repository.save(employeeToSave);
    }

    public void addEmployees(List<Employee> employees) {
        repository.saveAll(employees);
    }

    public Employee getEmployee(Long employeeId) {
        checkIfExists(employeeId);
        return repository.findById(employeeId).get();
    }


    public String modifyEmployee(Long employeeId, EmployeeCreationDTO employeeCreationDTO) {
        Long mngId = employeeCreationDTO.getMngId();

        checkIfExists(employeeId);
        checkIfExists(mngId);

        Employee manager = repository.getById(employeeCreationDTO.getMngId());
        Employee updatedEmployee = repository.getById(employeeId);

        updatedEmployee.setManager(manager);
        updatedEmployee.setName(employeeCreationDTO.getName());
        updatedEmployee.setExperties(employeeCreationDTO.getExpertises());
        updatedEmployee.setTeam(employeeCreationDTO.getTeam());
        updatedEmployee.setDepartment(employeeCreationDTO.getDepartment());
        updatedEmployee.setDob(employeeCreationDTO.getDob());
        updatedEmployee.setGradDate(employeeCreationDTO.getGradDate());
        updatedEmployee.setSalary(employeeCreationDTO.getSalary());

        repository.save(updatedEmployee);
        return "Successfully Updated!";
    }


    public List<Employee> getAllEmployees() {
        return repository.findAll();
    }

    @Transactional
    public List<EmployeeGetDTO> getEmployeesInTheSameTeam(Long teamId) {

        List<EmployeeGetDTO> employees = repository.getEmployeeByTeamEquals(teamId).stream().map(mapper::toDto).collect(Collectors.toList());
        if (employees.isEmpty()) throw new IllegalStateException("Team " + teamId + " doesn't exist");
        System.out.println("employees:" + employees);
        return employees;
    }

    @Transactional
    public String deleteEmployee(Long employeeId) {

        checkIfExists(employeeId);

        Employee employee = getEmployee(employeeId);

        Collection<Employee> employees = repository.getDirectEmployees(employee.getId());

        Employee manager = employee.getManager();


        if (manager == null && !employees.isEmpty())
            throw new IllegalStateException("Employee with id " + employeeId + " doesn't have manager so, Can't delete it ");

        employees.forEach(emp -> {
            emp.setManager(manager);
            repository.save(emp);

        });

        repository.deleteById(employee.getId());

        return "Successfully Deleted!";
    }

    @Transactional
    public List<EmployeeGetDTO> getEmployeesUnderSpecificManager(Long emp_id) {
        checkIfExists(emp_id);
        List<EmployeeGetDTO> employees = repository.getAllLevelsEmployeesUnderManager(emp_id).stream().map(mapper::toDto).collect(Collectors.toList());
        System.out.println("employees:" + employees);
        return employees;
    }

    private void checkIfExists(Long employeeId) {
        Boolean exists = repository.existsById(employeeId);
        if (!exists)
            throw new IllegalStateException("Employee with id " + employeeId + " doesn't exist");
    }

    @Transactional
    public List<EmployeeGetDTO> getDirectEmployees(Long emp_id) {
        checkIfExists(emp_id);
        List<EmployeeGetDTO> employees = repository.getDirectEmployees(emp_id).stream().map(mapper::toDto).collect(Collectors.toList());
        System.out.println("employees:" + employees);
        return employees;
    }
}
