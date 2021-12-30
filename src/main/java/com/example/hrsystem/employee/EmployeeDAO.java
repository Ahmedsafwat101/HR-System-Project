package com.example.hrsystem.employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface EmployeeDAO extends JpaRepository<Employee, Long> {


    @Query(value =
            "WITH RECURSIVE cte(emp_idd,level) AS (\n" +
                    "    SELECT emp_id,\n" +
                    "           0 as level\n" +
                    "    FROM employee\n" +
                    "    WHERE emp_id = ?1 \n" +
                    "    UNION ALL\n" +
                    "    SELECT t.emp_id,\n" +
                    "           level + 1\n" +
                    "    FROM employee t\n" +
                    "             JOIN cte c\n" +
                    "                  ON t.mng_id = c.emp_idd)\n" +
                    "\n" +
                    "SELECT t.emp_id,\n" +
                    "       t.emp_name,\n" +
                    "       t.dept_name,\n" +
                    "       t.emp_salary,\n" +
                    "       t.emp_gender,\n" +
                    "       emp_dob,\n" +
                    "       emp_grad_date,\n" +
                    "       team_id,\n" +
                    "       t.mng_id\n" +
                    "FROM cte c\n" +
                    "         JOIN employee t\n" +
                    "              ON emp_idd = t.emp_id\n" +
                    "where level != 0"
            , nativeQuery = true)
    Collection<Employee> getAllLevelsEmployeesUnderManager(Long empId);

    @Query(value =
            "WITH RECURSIVE cte(emp_idd,level) AS (\n" +
                    "    SELECT emp_id,\n" +
                    "           0 as level\n" +
                    "    FROM employee\n" +
                    "    WHERE emp_id = ?1 \n" +
                    "    UNION ALL\n" +
                    "    SELECT t.emp_id,\n" +
                    "           level + 1\n" +
                    "    FROM employee t\n" +
                    "             JOIN cte c\n" +
                    "                  ON t.mng_id = c.emp_idd)\n" +
                    "\n" +
                    "SELECT t.emp_id,\n" +
                    "       t.emp_name,\n" +
                    "       t.dept_name,\n" +
                    "       t.emp_salary,\n" +
                    "       t.emp_gender,\n" +
                    "       emp_dob,\n" +
                    "       emp_grad_date,\n" +
                    "       team_id,\n" +
                    "       t.mng_id\n" +
                    "FROM cte c\n" +
                    "         JOIN employee t\n" +
                    "              ON emp_idd = t.emp_id\n" +
                    "where level = 1"
            , nativeQuery = true)
    Collection<Employee> getDirectEmployees(Long empId);

    @Query("SELECT e FROM Employee e WHERE e.team = ?1")
    Collection<Employee> getEmployeeByTeamEquals(Long id);

    @Query("SELECT e from Employee e where e.name = ?1")
    Optional<Employee> getEmployeeByName(String name);
}
