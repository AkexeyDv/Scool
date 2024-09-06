package ru.hogwarts.school.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.AvatarService;
import ru.hogwarts.school.service.StudentService;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;

import static org.springframework.http.MediaType.parseMediaType;

@RequestMapping("/student")
@RestController
public class StudentController {
    private final StudentService studentService;
    private final AvatarService avatarService;

    public StudentController(StudentService studentService, AvatarService avatarService) {
        this.studentService = studentService;
        this.avatarService = avatarService;
    }

    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        Student createStd = studentService.createStudent(student);
        return createStd;
    }

    @PostMapping(value = "/{id}/avatar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> loadAvatar(@PathVariable Long id, @RequestParam MultipartFile avatar) throws IOException {
        if (avatar.getSize() > 1024 * 300) {
            return ResponseEntity.badRequest().body("Слишком большой размер файла");
        }
        avatarService.uploadAvatar(id, avatar);
        return ResponseEntity.ok().build();

    }

    @GetMapping("{idStudent}")
    public ResponseEntity<Student> getStudent(@PathVariable Long idStudent) {
        if (studentService.readStudent(idStudent) == null) {
            ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(studentService.readStudent(idStudent));
    }

    @GetMapping(path = "/all")
    public Collection<Student> getAll() {
        return studentService.findByAllStudent();
    }

    @GetMapping(path = "/range_age")
    public ArrayList<Student> getStudentAge(@RequestParam("minAge") int minAge,
                                            @RequestParam("maxAge") int maxAge) {
        return studentService.findByAgeBetweenStudent(minAge, maxAge);
    }

    @GetMapping("{idStudent}/faculty")
    public ResponseEntity<Faculty> getFaculty(@PathVariable long idStudent) {
        return ResponseEntity.ok(studentService.getFaculty(idStudent));
    }

    @GetMapping(value = "avatar/preview/{id}")
    public ResponseEntity<byte[]> downloadAvatarPreview(@PathVariable Long id) {
        Avatar avatar = avatarService.findAvatar(id);
        if (avatar!=null) {

            HttpHeaders headers = new HttpHeaders();

            headers.setContentType(MediaType.parseMediaType(avatar.getMediaType()));
            headers.setContentLength(avatar.getPreview().length);

            return ResponseEntity.status(HttpStatus.OK).headers(headers).body(avatar.getPreview());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @GetMapping(value = "avatar/{id}")
    public void downloadAvatar(@PathVariable Long id, HttpServletResponse response) throws IOException {
        Avatar avatar = avatarService.findAvatar(id);
        Path path = Path.of(avatar.getFilePath());
        try (
                InputStream is = Files.newInputStream(path);
                OutputStream os = response.getOutputStream()) {
            response.setStatus(200);
            response.setContentType(avatar.getMediaType());
            response.setContentLength((int) avatar.getFileSize());

            is.transferTo(os);


        }

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
        return studentService.findByAge(age);
    }

}
