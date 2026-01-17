package com.springboot.project.learning.repository;

import com.springboot.project.learning.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
