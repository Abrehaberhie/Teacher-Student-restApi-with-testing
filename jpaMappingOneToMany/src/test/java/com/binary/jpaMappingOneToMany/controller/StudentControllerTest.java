package com.binary.jpaMappingOneToMany.controller;


import com.binary.jpaMappingOneToMany.model.Student;
import com.binary.jpaMappingOneToMany.service.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.http.server.reactive.MockServerHttpRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class StudentControllerTest {
    @InjectMocks
    private StudentController studentController;
    private MockMvc mockMvc;
    @Mock
    private StudentService studentService;
    private static ObjectMapper mapper = new ObjectMapper();

    @Test
    public void saveStudentSuccess() throws Exception {
        // Arrange
        Student student = new Student();  // Assuming you have a Student class
        student.setId(1);
        student.setSname("John Doe");

        when(studentService.createStudent(any(Student.class))).thenReturn(student);

        mockMvc = MockMvcBuilders.standaloneSetup(studentController).build();

        String json = mapper.writeValueAsString(student);

        // Act and Assert
        mockMvc.perform(post("/createStudent")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("testing get All cars")

    public void getAllStudentsSuccess() {
        List<Student> expectedStudents = new ArrayList<>();
        Student student1 = new Student();
        student1.setId(1);
        student1.setSname("john");
        student1.setSclass("grade4");
        Student student2 = new Student();
        student2.setId(1);
        student2.setSname("john2");
        student2.setSclass("grade42");
        expectedStudents.add(student1);
        expectedStudents.add(student2);
        expectedStudents.add(new Student());
        when(studentService.getAllStudents()).thenReturn(expectedStudents);
        ResponseEntity<List<Student>> result = studentController.getAllStudents();
        Assertions.assertEquals(3, result.getBody().size());
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void getStudentByIdSuccess() throws Exception {
        // Arrange
        Student student = new Student();  // Assuming you have a Student class
        student.setId(1);
        student.setSname("John Doe");

        when(studentService.getStudentById(1)).thenReturn(student);

        mockMvc = MockMvcBuilders.standaloneSetup(studentController).build();


        mockMvc.perform(get("/getStudents/1"))
                .andExpect(status().isFound());

    }

    @Test
    public void updateStudentSuccess() throws Exception {
        // Arrange
        Student student = new Student();  // Assuming you have a Student class
        student.setId(1);
        student.setSname("John Doe");

        when(studentService.updateStudent(any(Student.class))).thenReturn(student);

        mockMvc = MockMvcBuilders.standaloneSetup(studentController).build();

        String json = mapper.writeValueAsString(student);

        // Act and Assert
        mockMvc.perform(put("/updateStudents")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());

    }
    @Test
    public void deletStudentByIdSuccess() throws Exception {
        // Arrange
        int studentId = 1;
        doNothing().when(studentService).deletStudent(studentId);

        mockMvc = MockMvcBuilders.standaloneSetup(studentController).build();

        // Act and Assert
        mockMvc.perform(delete("/deletStudent/{id}", studentId))
                .andExpect(status().isNoContent());

    }
}