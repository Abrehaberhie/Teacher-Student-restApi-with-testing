package com.binary.jpaMappingOneToMany.controller;

import com.binary.jpaMappingOneToMany.model.Student;
import com.binary.jpaMappingOneToMany.service.StudentService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping("/createStudent")
    public ResponseEntity<?> saveStudent(@RequestBody @Valid Student student)
    {
        return new ResponseEntity<>(studentService.createStudent(student), HttpStatus.CREATED);
    }
    @GetMapping("getStudents/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable("id") Integer id)
    {
        return new ResponseEntity<>(studentService.getStudentById(id),HttpStatus.FOUND);
    }
    @GetMapping("/getAllStudents")
    public ResponseEntity<List<Student>> getAllStudents()
    {
        List<Student> list= new ArrayList<Student>();

        studentService.getAllStudents().forEach(list::add);
        return new ResponseEntity<>(list,HttpStatus.OK);

    }
    @PutMapping("/updateStudents")
    public ResponseEntity<?>  updateStudent(@RequestBody Student student)
    {
        return new ResponseEntity<>(studentService.updateStudent(student),HttpStatus.OK);
    }

    @DeleteMapping("/deletStudent/{id}")
    public ResponseEntity<String> deletStudentById(@PathVariable("id") Integer id)
    {
        studentService.deletStudent(id);

        return new ResponseEntity<String>("Data has been deleted",HttpStatus.NO_CONTENT);
    }
}
