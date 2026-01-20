package com.springboot.project.learning.service.impl;

import com.springboot.project.learning.controller.StudentController;
import com.springboot.project.learning.dto.StudentDtoRequest;
import com.springboot.project.learning.dto.StudentDtoResponse;
import com.springboot.project.learning.entity.Student;
import com.springboot.project.learning.repository.StudentRepository;
import com.springboot.project.learning.service.StudentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final ModelMapper  modelMapper;

    public StudentServiceImpl(
            StudentRepository studentRepository,
            ModelMapper       modelMapper
    ){
        this.studentRepository = studentRepository;
        this.modelMapper       = modelMapper;
    }
    @Override
    public List<StudentDtoResponse> getAllStudents() {
        List<Student> studentList= studentRepository.findAll();
        List<StudentDtoResponse> studentDtoList =
                        studentList
                        .stream()
                        .map(s->modelMapper.map(s,StudentDtoResponse.class))
                        .toList();
        return studentDtoList;
    }

    @Override
    public StudentDtoResponse getStudentById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id " + id));

        return modelMapper.map(student, StudentDtoResponse.class);
    }

    @Override
    public StudentDtoResponse createStudent(StudentDtoRequest studentDtoRequest) {

        // 1. Map DTO → Entity
        Student student = modelMapper.map(studentDtoRequest, Student.class);

        // 2. Save to DB
        Student savedStudent = studentRepository.save(student);

        // 3. Map Entity → Response DTO
        return modelMapper.map(savedStudent, StudentDtoResponse.class);
    }

    @Override
    public StudentDtoResponse updateStudent(Long id, StudentDtoRequest request) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        modelMapper.map(request, student);

        Student updated = studentRepository.save(student);

        return modelMapper.map(updated, StudentDtoResponse.class);
    }


    @Override
    public void deleteStudent(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new RuntimeException("Student not found");
        }
        studentRepository.deleteById(id);
    }

    @Override
    public StudentDtoResponse updatePartialStudent(Long id, Map<String, Object> request) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        request.forEach((key, value)->{
            switch (key){
                case "name"  : student.setName((String)value);
                    break;
                case "email" : student.setEmail((String) value);
                    break;
                default: throw new RuntimeException("Field not allowed to update" + key);
            }
        });
        studentRepository.save(student);
        return modelMapper.map(student, StudentDtoResponse.class);
    }
}
