package com.example.hrsystem.department;

import com.example.hrsystem.department.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentDAO extends JpaRepository<Department,Long> {

}
