package com.springboot.project.learning.service;

import com.springboot.project.learning.dto.StudentDtoRequest;
import com.springboot.project.learning.dto.StudentDtoResponse;

import java.util.List;

public interface StudentService {

    // GET ALL
    List<StudentDtoResponse> getAllStudents();

    // GET
    StudentDtoResponse getStudentById(Long id);

    // CREATE
    StudentDtoResponse createStudent(StudentDtoRequest studentDtoRequest);

    // UPDATE
    StudentDtoResponse updateStudent(Long id, StudentDtoRequest studentDtoRequest);

    // DELETE
    void deleteStudent(Long id);
}
