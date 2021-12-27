package com.example.hrsystem.employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface EmployeeDAO extends JpaRepository<Employee, Long> {


    @Procedure(procedureName = "getAllEmployeesUnderManagerByID")
    Collection<Employee> getAllLevelsEmployeesUnderManager( Long empId);

    @Procedure(procedureName = "getOneLevelEmployeesUnderManagerByID")
    Collection<Employee> getDirectEmployees(Long empId);

    @Query("SELECT e FROM Employee e WHERE e.team = ?1")
    Collection<Employee> getEmployeeByTeamEquals(Long id);

    @Query("SELECT e from Employee e where e.name = ?1")
    Optional<Employee>getEmployeeByName(String name);
}
