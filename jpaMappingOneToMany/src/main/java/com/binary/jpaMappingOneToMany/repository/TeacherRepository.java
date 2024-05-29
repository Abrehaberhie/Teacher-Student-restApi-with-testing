package com.binary.jpaMappingOneToMany.repository;

import com.binary.jpaMappingOneToMany.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher,Integer> {
}
