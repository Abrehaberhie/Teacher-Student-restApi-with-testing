package com.binary.jpaMappingOneToMany.controller;

import com.binary.jpaMappingOneToMany.model.Teacher;
import com.binary.jpaMappingOneToMany.service.TeacherService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/teacher")
public class TeacherController {
@Autowired
private TeacherService teacherService;

    @PostMapping("/create")
    public ResponseEntity<?> saveTeacher(@RequestBody @Valid Teacher teacher)
    {
        return new ResponseEntity<>(teacherService.createTeacher(teacher), HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getTeacherById(@PathVariable("id") Integer id)
    {
        return new ResponseEntity<>(teacherService.getTeacherById(id),HttpStatus.FOUND);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Teacher>> getAllTeacher()
    {
        List<Teacher> list= new ArrayList<Teacher>();
        teacherService.getAllTeacher().forEach(list::add);


        return new ResponseEntity<>(list,HttpStatus.OK);

    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?>  updateTeacher(@RequestBody Teacher teacher, @PathVariable("id") Integer id)
    {
        return new ResponseEntity<>( teacherService.updateTeacher(teacher),HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletTeacherById(@PathVariable("id") Integer id)
    {
        teacherService.deletTeacher(id);

        return new ResponseEntity<String>("Data has been deleted",HttpStatus.NO_CONTENT);
    }

}
