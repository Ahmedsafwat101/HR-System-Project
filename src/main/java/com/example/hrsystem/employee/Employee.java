package com.example.hrsystem.employee;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
@RequiredArgsConstructor
@NoArgsConstructor
@Data
public class Employee {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "emp_id")
    private Long id;

    @NonNull
    @Column(name = "emp_name")
    private String name;


    @NonNull
    @Column(name = "emp_salary")
    private Double salary;


    @NonNull
    @Column(name = "emp_dob")
    private LocalDateTime dob;

    @NonNull
    @Column(name = "emp_gender")
    private Gender gender;

    @NonNull
    @Column(name = "emp_grad_date")
    private LocalDateTime gradDate;


    @NonNull
    @ElementCollection
    @Column(name = "emp_experties")
    private Set<String> experties;

    @NonNull
    @Column(name = "dept_name")
    private String department;

    @NonNull
    @Column(name = "team_id")
    private Long team;

    //Self Referencing Relation

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mng_id")
    private Employee manager;

    @OneToMany(mappedBy = "manager")
    @JsonBackReference
    private Set<Employee> employees = new HashSet<>();

    public Employee(Long id, @NonNull String name, @NonNull Double salary, @NonNull LocalDateTime dob, @NonNull Gender gender, @NonNull LocalDateTime gradDate, @NonNull Set<String> experties, @NonNull String department, @NonNull Long team) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.dob = dob;
        this.gender = gender;
        this.gradDate = gradDate;
        this.experties = experties;
        this.department = department;
        this.team = team;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dob=" + dob +
                ", gender=" + gender +
                ", gradDate=" + gradDate +
                ", experties=" + experties +
                ", department='" + department + '\'' +
                ", team=" + team +
                '}';
    }
}
