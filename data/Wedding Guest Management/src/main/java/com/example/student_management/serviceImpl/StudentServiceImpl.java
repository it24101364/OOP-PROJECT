package com.example.student_management.serviceImpl;

import com.example.student_management.entity.Student;
import com.example.student_management.repositary.StudentRepository;
import com.example.student_management.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Student createStudent(Student student) {
        try {
            return studentRepository.save(student);
        } catch (IOException e) {
            throw new RuntimeException("Error saving student", e);
        }
    }

    @Override
    public Student getStudentById(Long id) {
        try {
            Optional<Student> student = studentRepository.findById(id);
            return student.orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
        } catch (IOException e) {
            throw new RuntimeException("Error retrieving student", e);
        }
    }

    @Override
    public List<Student> getAllStudents() {
        try {
            return studentRepository.findAll();
        } catch (IOException e) {
            throw new RuntimeException("Error retrieving students", e);
        }
    }

    @Override
    public Student updateStudent(Long id, Student student) {
        try {
            student.setId(id);
            return studentRepository.save(student);
        } catch (IOException e) {
            throw new RuntimeException("Error updating student", e);
        }
    }

    @Override
    public void deleteStudent(Long id) {
        try {
            studentRepository.delete(id);
        } catch (IOException e) {
            throw new RuntimeException("Error deleting student", e);
        }
    }
}