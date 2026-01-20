package com.springboot.project.learning.controller;

import com.springboot.project.learning.dto.StudentDtoRequest;
import com.springboot.project.learning.dto.StudentDtoResponse;
import com.springboot.project.learning.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
public class StudentController {

    private final StudentService studentService;

    StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    @GetMapping("/students")
    public List<StudentDtoResponse> GetAllStudents(){
        return studentService.getAllStudents();
    }

    @GetMapping("/student/{id}")
    public StudentDtoResponse getStudentById(@PathVariable Long id){
        return studentService.getStudentById(id);
    }

    @PostMapping("/students")
    public StudentDtoResponse createStudent(@RequestBody @Valid StudentDtoRequest studentDtoRequest){
        return studentService.createStudent(studentDtoRequest);
    }

    @PutMapping("/student/{id}")
    public StudentDtoResponse updateStudent(@PathVariable Long id, @RequestBody @Valid StudentDtoRequest studentDtoRequest){
        return studentService.updateStudent(id, studentDtoRequest);
    }

    @DeleteMapping("/student/{id}")
    public void deleteStudent(@PathVariable Long  id){
        studentService.deleteStudent(id);
    }

    @PatchMapping("/student/{id}")
    public StudentDtoResponse updatePartialStudent(@PathVariable Long id, @RequestBody Map<String, Object> reqest){
        return studentService.updatePartialStudent(id, reqest);
    }
}
