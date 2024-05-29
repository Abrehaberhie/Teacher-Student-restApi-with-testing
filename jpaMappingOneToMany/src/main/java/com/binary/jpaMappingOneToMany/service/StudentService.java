package com.binary.jpaMappingOneToMany.service;

import com.binary.jpaMappingOneToMany.model.Student;
import com.binary.jpaMappingOneToMany.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

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
        return studentRepository.save(student);
    }

    public Student updateStudent(Student student)
    {
        Student existingStu=studentRepository.findById(student.getId()).orElse(null);

        existingStu.setId(student.getId());
        existingStu.setSname(student.getSname());
        existingStu.setSclass(student.getSclass());
        return studentRepository.save(existingStu);
    }

    public void deletStudent(Integer id)
    {
        studentRepository.deleteById(id);
    }
}
