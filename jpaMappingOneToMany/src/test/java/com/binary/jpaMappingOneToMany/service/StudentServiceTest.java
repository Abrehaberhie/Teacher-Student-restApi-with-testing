package com.binary.jpaMappingOneToMany.service;

import static org.hamcrest.CoreMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.binary.jpaMappingOneToMany.model.Student;
import com.binary.jpaMappingOneToMany.repository.StudentRepository;
import com.binary.jpaMappingOneToMany.service.StudentService;
//import com.binary.jpaMappingOneToMany.entity.Student;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.matchers.NotNull;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {
    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;
    @Test
    public void createStudentSuccess() {
        // Arrange
        Student student = new Student();
        student.setId(1);
        student.setSname("John Doe");


        when(studentRepository.save(student)).thenReturn(student);

        // Act
        Student result = studentService.createStudent(student);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("John Doe", result.getSname());

    }

    @Test
    public void getAllStudentsSuccess() {
        // Arrange
        Student student1 = new Student();  // Assuming you have a Student class
        student1.setId(1);
        student1.setSname("John Doe");

        Student student2 = new Student();
        student2.setId(2);
        student2.setSname("Jane Doe");

        List<Student> students = Arrays.asList(student1, student2);

        when(studentRepository.findAll()).thenReturn(students);

        // Act
        List<Student> result = studentService.getAllStudents();

        // Assert
        assertEquals(2, result.size());

    }
    @Test
    public void getStudentByIdFound() {
        // Arrange
        int studentId = 1;
        Student student = new Student();  // Assuming you have a Student class
        student.setId(studentId);
        student.setSname("John Doe");

        when(studentRepository.findById(studentId)).thenReturn(Optional.of(student));

        // Act
        Student result = studentService.getStudentById(studentId);

        // Assert
        assertNotNull(result);
        assertEquals(studentId, result.getId());
        assertEquals("John Doe", result.getSname());
    }
    @Test
    public void updateStudentSuccess() {
        // Arrange
        Student existingStudent = new Student();
        existingStudent.setId(1);
        existingStudent.setSname("Existing Student");


        Student updatedStudent = new Student();
        updatedStudent.setId(1);
        updatedStudent.setSname("Updated Student");


        when(studentRepository.findById(1)).thenReturn(Optional.of(existingStudent));

        when(studentRepository.save(Mockito.any(Student.class))).thenReturn(updatedStudent);
        // Act
        Student result = studentService.updateStudent(updatedStudent);

        // Assert
        assertNotNull(result);


    }

    @Test
    public void testDeleteStudent() {
        // Arrange
       Student student = new Student();
       student.setSname("alex");

        // Act
       when(studentRepository.findById(1)).thenReturn(Optional.ofNullable(student));
        // Assert

        assertAll(()->studentService.getStudentById(1));
    }

}
