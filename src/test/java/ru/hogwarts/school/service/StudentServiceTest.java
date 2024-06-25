package ru.hogwarts.school.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.hogwarts.school.ErrorApp.ExceptionApp;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import static org.junit.jupiter.api.Assertions.*;

class StudentServiceTest {
    StudentService out=new StudentService();
    @BeforeEach
    void setUp(){
        Student newStudent=new Student(10l,"Ivan",12);

        out.createStudent(newStudent);
    }

    @Test
    void createStudent() {
        Assertions.assertEquals(1l,out.readStudent(1l).getId());
    }

    @Test
    void updateStudent() {
        Student updateStudent=new Student(1l,"Semen",12);
        out.updateStudent(updateStudent);
        Assertions.assertEquals(out.readStudent(1l).getName(),"Semen");
        Assertions.assertEquals(out.readStudent(1l).getAge(),12);
    }

    @Test
    void readStudent() {
        Student readStudent=new Student(258l,"Ilya",18);
        out.createStudent(readStudent);
        Assertions.assertThrows(ExceptionApp.class,()->out.readStudent(258l).getId());
        Assertions.assertEquals(out.readStudent(2l).getName(),"Ilya");
    }

    @Test
    void deleteStudent() {
        out.deleteStudent(1l);
        Assertions.assertThrows(ExceptionApp.class,()->out.readStudent(1l).getId());
    }

    @Test
    void filteredByAgeStudent() {
    }
}