package ru.hogwarts.school.service;

import org.aspectj.lang.annotation.RequiredTypes;
import org.hibernate.boot.model.internal.QueryBinder;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.ErrorApp.ExceptionApp;
import ru.hogwarts.school.Repository.StudentRepository;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.sql.Connection;
import java.sql.Statement;
import java.util.*;
import java.util.stream.Stream;

@Service

public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    public Student createStudent(Student student) {
        student.setId(null);
        return studentRepository.save(student);

    }


    public Student updateStudent(Student student) {
        return studentRepository.save(student);
    }


    public Student readStudent(Long idStudent) {
        return studentRepository.findById(idStudent).get();
    }

    public Student deleteStudent(Long idStudent) {

        Student delStudent = readStudent(idStudent);
        studentRepository.deleteById(idStudent);
        return delStudent;
    }


    public ArrayList<Student> findByAge(int age) {
        return studentRepository.findByAge(age);
    }

    public Student findById(Long idStudent) {
        return studentRepository.findById(idStudent).get();
    }

    public ArrayList<Student> findByAgeBetweenStudent(int minAge, int maxAge) {
        return studentRepository.findByAgeBetween(minAge, maxAge);
    }


    public Collection<Student> findByAllStudent() {

        return studentRepository.findAllStudent();
    }

    public Faculty getFaculty(Long idStudent) {

        return studentRepository.findById(idStudent)
                .map(Student::getFaculty).orElse(null);
    }

    @Override
    public String toString() {
        return studentRepository.findAll().toString();
    }

}
