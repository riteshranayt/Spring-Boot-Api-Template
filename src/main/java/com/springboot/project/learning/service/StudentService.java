package com.springboot.project.learning.service;

import com.springboot.project.learning.dto.StudentDtoRequest;
import com.springboot.project.learning.dto.StudentDtoResponse;

import java.util.List;
import java.util.Map;

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

    StudentDtoResponse updatePartialStudent(Long id, Map<String, Object> request);
}
