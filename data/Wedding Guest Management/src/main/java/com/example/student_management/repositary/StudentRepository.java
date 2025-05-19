package com.example.student_management.repositary;

import com.example.student_management.entity.Student;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class StudentRepository {

    @Value("${app.data.file}")
    private String dataFile;

    private final ObjectMapper objectMapper;

    public StudentRepository(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public Student save(Student student) throws IOException {
        List<Student> students = findAll();
        if (student.getId() == null) {
            // Generate ID for new student
            Long newId = students.isEmpty() ? 1L : students.get(students.size() - 1).getId() + 1;
            student.setId(newId);
            students.add(student);
        } else {
            // Update existing student
            Optional<Student> existing = students.stream()
                    .filter(s -> s.getId().equals(student.getId()))
                    .findFirst();
            if (existing.isPresent()) {
                students.remove(existing.get());
                students.add(student);
            } else {
                throw new RuntimeException("Student not found with id: " + student.getId());
            }
        }
        writeToFile(students);
        return student;
    }

    public Optional<Student> findById(Long id) throws IOException {
        List<Student> students = findAll();
        return students.stream()
                .filter(student -> student.getId().equals(id))
                .findFirst();
    }

    public List<Student> findAll() throws IOException {
        File file = new File(dataFile.replace("classpath:", "src/main/resources/"));
        if (!file.exists()) {
            return new ArrayList<>();
        }
        return objectMapper.readValue(file, new TypeReference<List<Student>>() {});
    }

    public void delete(Long id) throws IOException {
        List<Student> students = findAll();
        boolean removed = students.removeIf(student -> student.getId().equals(id));
        if (!removed) {
            throw new RuntimeException("Student not found with id: " + id);
        }
        writeToFile(students);
    }

    private void writeToFile(List<Student> students) throws IOException {
        File file = new File(dataFile.replace("classpath:", "src/main/resources/"));
        objectMapper.writeValue(file, students);
    }
}