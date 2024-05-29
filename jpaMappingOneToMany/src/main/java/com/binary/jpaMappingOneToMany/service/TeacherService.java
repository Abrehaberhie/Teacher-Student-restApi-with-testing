package com.binary.jpaMappingOneToMany.service;

import com.binary.jpaMappingOneToMany.model.Student;
import com.binary.jpaMappingOneToMany.model.Teacher;
import com.binary.jpaMappingOneToMany.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {
    @Autowired
    private TeacherRepository teacherRepository;

   public List<Teacher> getAllTeacher()
    {
        return teacherRepository.findAll();
    }
    public Teacher createTeacher(Teacher teacher)
    {
        return teacherRepository.save(teacher);
    }
    public Teacher getTeacherById(Integer id)
    {
        Optional<Teacher> optional= teacherRepository.findById(id);
        if(optional.isPresent())
        {
            return optional.get();
        }else{
            return null;
        }
    }


    public Teacher updateTeacher(Teacher teacher)
    {
        Teacher existingTeach=teacherRepository.findById(teacher.getId()).orElse(null);


        existingTeach.setId(teacher.getId());
        existingTeach.setFirstName(teacher.getFirstName());
        existingTeach.setLastName(teacher.getLastName());
        existingTeach.setTeachingSubject(teacher.getTeachingSubject());
        return teacherRepository.save(existingTeach);
    }

    public void deletTeacher(Integer id)
    {
        teacherRepository.deleteById(id);
    }
}
