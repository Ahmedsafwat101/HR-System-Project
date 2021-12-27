package com.example.hrsystem.team;

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
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_id")
    private Long id;

    @Getter @Setter
    @OneToMany(mappedBy = "team")
    private Set<Employee> teamMembers = new HashSet<>();

}
