package com.example.hrsystem.employee;

import com.example.hrsystem.employee.dtos.EmployeeCreationDTO;
import com.example.hrsystem.employee.dtos.EmployeeGetDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    private EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }


    @GetMapping(path = "/api/v1/get/")
    public List<Employee> getAllEmployees() {

        return service.getAllEmployees();
    }


    @GetMapping(path = "/api/v1/get/{emp_id}")
    public Employee getEmployee(@PathVariable("emp_id") Long emp_id) {
        return service.getEmployee(emp_id);
    }

    @GetMapping(path = "/api/v1/get/team/{team_id}")
    public List<EmployeeGetDTO> getEmployeesInSameTeam(@PathVariable("team_id") Long team_id) {
        return service.getEmployeesInTheSameTeam(team_id);
    }

    @GetMapping(path = "/api/v1/get/employee/manager/{emp_id}")
    public List<EmployeeGetDTO> getAllEmployeesUnderSpecificManager(@PathVariable("emp_id") Long emp_id) {
        return service.getEmployeesUnderSpecificManager(emp_id);
    }

    @GetMapping(path = "/api/v1/get/direct/{mng_id}")
    public List<EmployeeGetDTO> getDirectEmployeesUnderManager(@PathVariable("mng_id") Long mng_id) {
        return service.getDirectEmployees(mng_id);
    }



    @PostMapping(path = "/api/v1/add/")
    public void addEmployee(@RequestBody EmployeeCreationDTO employeeCreationDTO) {
        service.addEmployee(employeeCreationDTO);
    }

    @PostMapping(path = "/api/v1/add/all")
    public void addEmployees(@RequestBody List<Employee>employees) {
        service.addEmployees(employees);
    }


    @PutMapping(path = "/api/v1/edit/{emp_id}")
    public String updateEmployee(
            @PathVariable(value = "emp_id") Long employeeId,
            @RequestBody EmployeeCreationDTO employeeCreationDTO){
        return service.modifyEmployee(employeeId, employeeCreationDTO);

    }


    @DeleteMapping(path = "/api/v1/delete/{emp_id}")
    public String deleteEmployee(
            @PathVariable("emp_id") Long employeeId){
        return service.deleteEmployee(employeeId);

    }



}
