package com.binary.jpaMappingOneToMany.repository;

import com.binary.jpaMappingOneToMany.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,Integer> {
    public Optional<Member> findByEmail(String Email);
}
