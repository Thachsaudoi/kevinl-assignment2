package com.example.assignment2.models;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Integer>{
    List<Student> findBygpa(float gpa);
    List<Student> findByNameAndHair(String name, String hair);
    Optional<Student> findByUid(int userUid);
    
}
