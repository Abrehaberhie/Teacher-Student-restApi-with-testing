package com.binary.jpaMappingOneToMany.repository;

import com.binary.jpaMappingOneToMany.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {
}
