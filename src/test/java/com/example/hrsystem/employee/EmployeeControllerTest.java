package com.example.hrsystem.employee;

import com.example.hrsystem.employee.dtos.EmployeeCreationDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.*;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.HashSet;
import java.util.Set;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration
@Transactional
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionDbUnitTestExecutionListener.class})
@ActiveProfiles("test")
class EmployeeControllerTest {


    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private EmployeeDAO employeeDAO;
    private String objAsJson;

    @Autowired
    private EmployeeService service;




    @Test
    @DatabaseSetup(value = "classpath:dbunit/employeeSeed.xml")
    public void addEmployeeReturn200() throws Exception {
        Set<String> expertiseSet = new HashSet<>();
        expertiseSet.add("Web");

        EmployeeCreationDTO emp8 = new EmployeeCreationDTO("Zizo",
                5000.0, "HR", 1L, Gender.MALE, LocalDateTime.of(1986, Month.APRIL, 8, 12, 30),
                LocalDateTime.of(1986, Month.APRIL, 8, 12, 30),
                expertiseSet, 1L);

        objAsJson = objectMapper.writeValueAsString(emp8);

        mockMvc.perform(post("/api/v1/add/").content(objAsJson)
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
    }


    @Test
    @DatabaseSetup(value = "classpath:dbunit/employeeSeed.xml")
    public void deleteEmployeeReturn200() throws Exception {
        String emp_id = "2";
        int expectedNumOfEmployees = 6;


        MvcResult result = mockMvc.perform(delete("/api/v1/delete/" + emp_id)
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        assertThat(result.getResponse().getContentAsString()).isEqualTo("Successfully Deleted!");
    }


    @Test
    @DatabaseSetup(value = "classpath:dbunit/employeeSeed.xml")
    //@ExpectedDatabase(value = "classpath:dbunit/getDirectEmployeesUnderTheSameManagerExpected.xml")
    public void getDirectEmployeesUnderSpecificManager() throws Exception {

        String emp_id = "1";
        int expectedNumOfEmployees = 2;
        mockMvc.perform(get("/api/v1/get/direct/" + emp_id)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$", hasSize(expectedNumOfEmployees))).andReturn();

    }

    @Test
    @DatabaseSetup(value = "classpath:dbunit/employeeSeed.xml")
    //@ExpectedDatabase(value = "classpath:dbunit/getAllEmployeesInTheSameTeamExpected.xml")
    public void getAllEmployeesInTheSameTeam() throws Exception {
        String teamId = "1";
        int expectedNumOfEmployees = 6;
        mockMvc.perform(get("/api/v1/get/team/" + teamId)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$", hasSize(expectedNumOfEmployees))).andReturn();
    }


    @Test
    @DatabaseSetup(value = "classpath:dbunit/employeeSeed.xml")
    public void updateEmployee() throws Exception {

        String emp_id = "1";
        Set<String> expertiseSet = new HashSet<>();
        expertiseSet.add("Web");


        EmployeeCreationDTO updateEmployee = new EmployeeCreationDTO("Zizo",
                5000.0, "HR", 1L, Gender.MALE, LocalDateTime.of(1986, Month.APRIL, 8, 12, 30),
                LocalDateTime.of(1986, Month.APRIL, 8, 12, 30),
                expertiseSet, 1L);

        objAsJson = objectMapper.writeValueAsString(updateEmployee);


        mockMvc.perform(put("/api/v1/edit/" + emp_id).content(objAsJson)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();


        Employee employee = employeeDAO.findById(1L).get();
        assertNotNull(employee);
        assertEquals("Zizo", employee.getName());
    }


}