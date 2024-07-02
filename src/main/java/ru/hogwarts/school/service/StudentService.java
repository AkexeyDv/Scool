package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.ErrorApp.ExceptionApp;
import ru.hogwarts.school.Repository.StudentRepository;
import ru.hogwarts.school.model.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    public Student createStudent(Student student) {

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

    @Override
    public String toString() {
        return studentRepository.findAll().toString();
    }
}
