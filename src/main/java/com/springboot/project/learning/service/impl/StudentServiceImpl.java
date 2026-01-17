package com.springboot.project.learning.service.impl;

import com.springboot.project.learning.dto.StudentDtoRequest;
import com.springboot.project.learning.dto.StudentDtoResponse;
import com.springboot.project.learning.entity.Student;
import com.springboot.project.learning.repository.StudentRepository;
import com.springboot.project.learning.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }
    @Override
    public List<StudentDtoResponse> getAllStudents() {
        List<Student> studentList= studentRepository.findAll();
        List<StudentDtoResponse> studentDtoList =
                        studentList
                        .stream()
                        .map(s-> new StudentDtoResponse(s.getId(),s.getName(),s.getEmail()))
                        .toList();
        return studentDtoList;
    }

    @Override
    public StudentDtoResponse getStudentById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id " + id));

        return new StudentDtoResponse(
                student.getId(),
                student.getName(),
                student.getEmail()
        );
    }

    @Override
    public StudentDtoResponse createStudent(StudentDtoRequest studentDtoRequest) {

        // 1. Map DTO → Entity
        Student student = new Student();
        student.setName(studentDtoRequest.getName());
        student.setEmail(studentDtoRequest.getEmail());

        // 2. Save to DB
        Student savedStudent = studentRepository.save(student);

        // 3. Map Entity → Response DTO
        return new StudentDtoResponse(
                savedStudent.getId(),
                savedStudent.getName(),
                savedStudent.getEmail()
        );
    }

    @Override
    public StudentDtoResponse updateStudent(Long id, StudentDtoRequest request) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        student.setName(request.getName());
        student.setEmail(request.getEmail());

        Student updated = studentRepository.save(student);

        return new StudentDtoResponse(
                updated.getId(),
                updated.getName(),
                updated.getEmail()
        );
    }


    @Override
    public void deleteStudent(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new RuntimeException("Student not found");
        }
        studentRepository.deleteById(id);
    }
}
