package com.example.hrsystem.employee;

import com.example.hrsystem.employee.dtos.EmployeeCreationDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional

class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private EmployeeDAO employeeDAO;

    private Set<String> expertiseSet;
    private EmployeeCreationDTO testEmployee;
    private String objAsJson;


    @BeforeEach
    void setUp() throws JsonProcessingException {
        expertiseSet = new HashSet<>();
        expertiseSet.add("Machine Learning");
        expertiseSet.add("Web");

        testEmployee = new EmployeeCreationDTO(
                "Test1",
                50000.0,
                "Engineering",
                2L, Gender.MALE,
                LocalDateTime.of(LocalDate.of(2021, 12, 20), LocalTime.now())
                , LocalDateTime.of(LocalDate.of(2021, 12, 20), LocalTime.now()),
                expertiseSet,
                1L);


        objAsJson = objectMapper.writeValueAsString(testEmployee);
    }

    @Test
    void addEmployee() throws Exception {

        mockMvc.perform(post("/api/v1/add/").content(objAsJson)
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk()).andReturn();


        Employee employee = employeeDAO.findById(8L).get();
        assertThat(employee.getName()).isEqualTo("Test1");
    }

    @Test
    public void getEmployee() throws Exception {

        mockMvc.perform(get("/api/v1/get/1")
                        .accept(MediaType.APPLICATION_JSON_VALUE)).
                andExpect(status().isOk()).
                andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("name", is("Abbas")));
    }

    @Test
    @Transactional
    public void updateEmployee() throws Exception {

        mockMvc.perform(post("/api/v1/add/").content(objAsJson)
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk()).andReturn();

        String emp_id = "8";
        String updatedName = "emp8";
        testEmployee.setName(updatedName);


        objAsJson = objectMapper.writeValueAsString(testEmployee);

        System.out.println("obj:" + objAsJson);

        mockMvc.perform(put("/api/v1/edit/" + emp_id).content(objAsJson)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk()).andReturn();


        mockMvc.perform(get("/api/v1/get/" + emp_id)
                        .accept(MediaType.APPLICATION_JSON_VALUE)).
                andExpect(status().isOk()).
                andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("name", is(updatedName)));
    }

    @Test
    public void deleteEmployee() throws Exception {

        String emp_id = "2";

        MvcResult result = mockMvc.perform(delete("/api/v1/delete/" + emp_id)
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk()).andReturn();

        assertThat(result.getResponse().getContentAsString()).isEqualTo("Successfully Deleted!");
    }

    @Test
    public void getAllEmployeesUnderSpecificManager() throws Exception {
        String mngId = "1";
        mockMvc.perform(get("/api/v1/get/employee/manager/" + mngId)
                        .accept(MediaType.APPLICATION_JSON_VALUE)).
                andExpect(status().isOk()).andReturn();
    }

    @Test
    public void getAllEmployeesInTheSameTeam() throws Exception {
        String teamId = "1";
        mockMvc.perform(get("/api/v1/get/team/" + teamId)
                        .accept(MediaType.APPLICATION_JSON_VALUE)).
                andExpect(status().isOk()).andReturn();
    }


    @Test
    public void getDirectEmployeesUnderSpecificManager() throws Exception {
        String emp_id = "1";
        mockMvc.perform(get("/api/v1/get/direct/" + emp_id)
                        .accept(MediaType.APPLICATION_JSON_VALUE)).
                andExpect(status().isOk()).andReturn();
    }


}