package com.example.hrsystem.utils;

import com.example.hrsystem.employee.Employee;
import com.example.hrsystem.employee.EmployeeDAO;
import com.example.hrsystem.employee.Gender;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.HashSet;

@Component
public class Seeder {
    private EmployeeDAO repository;

    public Seeder(EmployeeDAO repository) {
        this.repository = repository;
    }


    @EventListener
    public void seed(ContextRefreshedEvent event) {
        seedEmployeeTable();
    }

    private void seedEmployeeTable() {

//        if(repository.count()!=0) return;
//
//        HashSet<String> expertiseSet = new HashSet<>();
//        expertiseSet.add("Machine Learning");
//        expertiseSet.add("Web");
//
//
//
//        Employee emp1 = new Employee("Abbas", 200.0,  LocalDateTime.of(LocalDate.of(2021, 12, 20), LocalTime.now()), Gender.MALE, LocalDateTime.of(LocalDate.of(2021, 12, 20), LocalTime.now()), expertiseSet, "HR", 1L);
//        Employee emp2 = new Employee("Ahmed", 200.0, LocalDateTime.of(LocalDate.of(2021, 12, 20), LocalTime.now()), Gender.MALE, LocalDateTime.of(LocalDate.of(2021, 12, 20), LocalTime.now()), expertiseSet, "HR", 1L);
//        Employee emp3 = new Employee("Amen", 200.0, LocalDateTime.of(LocalDate.of(2021, 12, 20), LocalTime.now()), Gender.MALE, LocalDateTime.of(LocalDate.of(2021, 12, 20), LocalTime.now()), expertiseSet, "HR", 1L);
//        Employee emp4 = new Employee("Ali", 200.0, LocalDateTime.of(LocalDate.of(2021, 12, 20), LocalTime.now()), Gender.MALE, LocalDateTime.of(LocalDate.of(2021, 12, 20), LocalTime.now()), expertiseSet, "HR", 1L);
//        Employee emp5 = new Employee("Aya", 200.0, LocalDateTime.of(LocalDate.of(2021, 12, 20), LocalTime.now()), Gender.FEMALE, LocalDateTime.of(LocalDate.of(2021, 12, 20), LocalTime.now()), expertiseSet, "HR", 1L);
//        Employee emp6 = new Employee("Wahdan", 200.0, LocalDateTime.of(LocalDate.of(2021, 12, 20), LocalTime.now()), Gender.MALE, LocalDateTime.of(LocalDate.of(2021, 12, 20), LocalTime.now()), expertiseSet, "HR", 1L);
//        Employee emp7 = new Employee("Ziad", 200.0, LocalDateTime.of(LocalDate.of(2021, 12, 20), LocalTime.now()), Gender.MALE, LocalDateTime.of(LocalDate.of(2021, 12, 20), LocalTime.now()), expertiseSet, "HR", 1L);
//
//        repository.save(emp1);
//        emp2.setManager(emp1);
//        repository.save(emp2);
//        emp3.setManager(emp1);
//        repository.save(emp3);
//        emp4.setManager(emp2);
//        repository.save(emp4);
//        emp5.setManager(emp3);
//        repository.save(emp5);
//        emp6.setManager(emp4);
//        repository.save(emp6);
//        emp7.setManager(emp3);
//        repository.save(emp7);


    }

}
