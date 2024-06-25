package ru.hogwarts.school;

import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

public class Sample {
    public static void main(String[] args) {
        StudentService std=new StudentService();
        std.createStudent(new Student(0l,"Ivan",12));
        System.out.println(std.readStudent(0l));


    }


}
