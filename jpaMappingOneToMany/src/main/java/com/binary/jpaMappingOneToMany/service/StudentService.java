package com.binary.jpaMappingOneToMany.service;

import com.binary.jpaMappingOneToMany.model.Student;
import com.binary.jpaMappingOneToMany.model.Teacher;
import com.binary.jpaMappingOneToMany.repository.StudentRepository;
import com.binary.jpaMappingOneToMany.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    public List<Student> getAllStudents()
    {
        return studentRepository.findAll();
    }
    /*public Student getStudentById(Integer id)
    {
        Optional<Student> optional= studentRepository.findById(id);
        if(optional.isPresent())
        {
            return optional.get();
        }else{
            return null;
        }
    }
*/
    public Student getStudentById(Integer id)
    {
        Optional<Student> optional= studentRepository.findById(id);
        if(optional.isPresent())
        {
            return optional.get();
        }else{
            return null;
        }
    }


    public Student createStudent(Student student)
    {
        if(student.getTeacher() != null && student.getTeacher().getId() == null){
            Teacher savedTeacher = teacherRepository.save(student.getTeacher());
            student.setTeacher(savedTeacher);
        }

        return studentRepository.save(student);
    }

    public Student updateStudent(Student student)
    {
        Student existingStu=studentRepository.findById(student.getId()).orElse(null);

        existingStu.setId(student.getId());
        existingStu.setSname(student.getSname());
        existingStu.setSclass(student.getSclass());
        if(student.getTeacher() != null && student.getTeacher().getId() == null){
            Teacher savedTeacher = teacherRepository.save(student.getTeacher());
            existingStu.setTeacher(savedTeacher);
        }
        return studentRepository.save(existingStu);
    }

    public void deletStudent(Integer id)
    {
        studentRepository.deleteById(id);
    }
}
