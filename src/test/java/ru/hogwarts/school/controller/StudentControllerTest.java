package ru.hogwarts.school.controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import ru.hogwarts.school.model.Student;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
public class StudentControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private StudentController studentController;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void testCreateStudent() throws Exception{
        Student testStudent=new Student();
        testStudent.setName("Test Student");
        testStudent.setAge(12);

        Assertions.assertThat(this.testRestTemplate.
                postForObject("http://localhost:" + port +"/student",testStudent,Student.class)).
                isNotNull();
    }

    @Test
    void ContextLoad() throws Exception {
        Assertions.assertThat(studentController).isNotNull();
    }

    @Test
    void testGetStudent() throws Exception {
        Assertions.assertThat(this.testRestTemplate.getForObject("http://localhost:" + port +
                "/student/1", String.class)).isNotNull();
    }

    @Test
    void testGetAllStudent() throws Exception {
        Assertions.assertThat(this.testRestTemplate.getForObject("http://localhost:" + port +
                "/student/all", ArrayList.class).size()).isNotZero();
    }

    @Test
    void testGetAgeStudent() throws Exception {
        Assertions.assertThat(this.testRestTemplate.getForObject("http://localhost:" + port +
                "/student/range_age?minAge=0&maxAge=20", ArrayList.class).size()).isNotZero();
        Assertions.assertThat(this.testRestTemplate.getForObject("http://localhost:" + port +
                "/student/range_age?minAge=200&maxAge=1000", ArrayList.class).size()).isZero();

    }

    @Test
    void testAgeStudent() throws Exception {
        //Прверяем на то, чего не должно быть
        Assertions.assertThat(this.testRestTemplate.getForObject("http://localhost:" + port +
                "/student/filtered/100", ArrayList.class).size()).isZero();
        //И на то, что должно что-то быть
        Assertions.assertThat(this.testRestTemplate.getForObject("http://localhost:" + port +
                "/student/filtered/12", ArrayList.class).size()).isNotZero();
    }


    @Test
    void testPostStudent() throws Exception {
        Student student = new Student();
        student.setName("Iiiiii");
        student.setAge(12);
        Assertions.assertThat(this.testRestTemplate.postForObject("http://localhost:" + port +
                "/student/1", student, String.class)).isNotNull();
    }

    @Test
    void testDownloadAvatarPreview() throws Exception {
        String STATUS_YES = "200 OK";
        String STATUS_NON = "400 BAD_REQUEST";
        assertEquals(testRestTemplate.getForEntity("http://localhost:" + port +
                "/student/avatar/preview/1", String.class).getStatusCode().toString(), STATUS_YES);

        assertEquals(testRestTemplate.getForEntity("http://localhost:" + port +
                "/student/avatar/preview/100", String.class).getStatusCode().toString(), STATUS_NON);

    }

    @Test
    void testLoadAvatar() throws Exception {
        Assertions.assertThat(this.testRestTemplate.getForObject("http://localhost:" + port +
                "/student/1/avatar", String.class)).isNotNull();
    }


}
