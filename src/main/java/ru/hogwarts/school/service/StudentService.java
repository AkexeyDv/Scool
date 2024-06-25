package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.ErrorApp.ExceptionApp;
import ru.hogwarts.school.model.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.stream.Stream;

@Service
public class StudentService {
    private final HashMap<Long, Student> students = new HashMap<>();
    private Long idxStudentSpinner = 0L;

    public Long getSpinner() {
        return idxStudentSpinner;
    }

    public Student createStudent(Student student) {
        idxStudentSpinner++;
        student.setId(idxStudentSpinner);
        students.put(idxStudentSpinner, student);
        return student;

    }


    public Student updateStudent(Student student) {
        students.put(student.getId(), student);
        return student;
    }



    public Student readStudent(Long idStudent) {
        if (!students.containsKey(idStudent)) {
            throw new ExceptionApp("Такого студента нет. Чтение невозможно");
        }
        return students.get(idStudent);
    }

    public Student deleteStudent(Long idStudent) {
        if (!students.containsKey(idStudent)) {
            throw new ExceptionApp("Такого студента нет. Удаление невозможно");
        }
        Student removeStudent = students.get(idStudent);
        students.remove(idStudent);
        return removeStudent;
    }

    public ArrayList<Student> filteredByAgeStudent(int age) {
        ArrayList<Student> stdAge = new ArrayList<>();
        Set<Long> setKey = students.keySet();
        for (Long s : setKey) {
            if (students.get(s).getAge() == age) {
                stdAge.add(students.get(s));
            }

        }
        return stdAge;
    }

    @Override
    public String toString() {
        return "StudentService{" +
                "students=" + students +
                '}';
    }
}
