package ru.hogwarts.school.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.ArrayList;
import java.util.Collection;

@RequestMapping("/student")
@RestController
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        Student createStd = studentService.createStudent(student);
        return createStd;
    }

    @GetMapping("{idStudent}")
    public ResponseEntity<Student> getStudent(@PathVariable Long idStudent) {
        if (studentService.readStudent(idStudent) == null) {
            ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(studentService.readStudent(idStudent));
    }
    @GetMapping(path = "/all")
    public Collection<Student> getAll(){
        return studentService.findByAllStudent();
    }
    @GetMapping
    public ArrayList<Student> getStudentAge(@RequestParam("minAge") int minAge,
                                                 @RequestParam("maxAge") int maxAge){
        return studentService.findByAgeBetweenStudent(minAge,maxAge);
    }

    @GetMapping("{idStudent}/faculty")
    public ResponseEntity<Faculty> getFaculty(@PathVariable long idStudent){
        return ResponseEntity.ok(studentService.getFaculty(idStudent));
    }




    @PutMapping("/put")
    public ResponseEntity<Student> putStudent(@RequestBody Student student) {
        if (studentService.readStudent(student.getId()) == null) {
            ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(studentService.updateStudent(student));
    }

    @DeleteMapping("{idStudentDel}")
    public Student delStudent(@PathVariable Long idStudentDel) {
        if (studentService.readStudent(idStudentDel) == null) {
            ResponseEntity.notFound().build();
        }
        Student removeStd = studentService.deleteStudent(idStudentDel);
        return removeStd;
    }


    @GetMapping("filtered/{age}")
    public ArrayList<Student> getStudentAgeStudent(@PathVariable int age) {
        System.out.println(age);
        return studentService.findByAge(age);
    }

}
