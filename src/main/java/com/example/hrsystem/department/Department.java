package com.example.hrsystem.department;

import com.example.hrsystem.employee.Employee;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
@RequiredArgsConstructor
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dept_id")
    private Long id;

    @Getter @Setter
    @OneToMany(mappedBy = "department")
    private Set<Employee> departmentMembers = new HashSet<>();

}
