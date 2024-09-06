package ru.hogwarts.school.controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

@SpringBootTest(webEnvironment = RANDOM_PORT)
class FacultyControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private FacultyController facultyController;

    @Autowired
    private TestRestTemplate testRestTemplate;


    @Test
    void createFaculty() throws Exception {
        Faculty testFaculty = new Faculty();
        testFaculty.setName("test");
        testFaculty.setColor("testColor");

        int size1 = testRestTemplate.getForObject("http://localhost:" + port + "/faculty/filtered/testColor", List.class).size();
        Assertions.assertThat(this.testRestTemplate.
                postForObject("http://localhost:" + port + "/faculty", testFaculty, Faculty.class)).isNotNull();
    }

    @Test
    void getFaculty() {
        Assertions.assertThat(this.testRestTemplate.
                getForObject("http://localhost:" + port + "/faculty/2", Faculty.class)).isNotNull();

    }

    @Test
    void getStudents() {
        Assertions.assertThat(this.testRestTemplate.
                getForObject("http://localhost:" + port + "/faculty/1/student", Student.class)).isNotNull();

    }

    @Test
    void putFaculty() {
        String colorTestFaculty = "colorTestBlue";
        Faculty testFaculty = new Faculty();
        testFaculty.setName("test1");
        testFaculty.setColor(colorTestFaculty);
        Faculty newFaculty=testRestTemplate.postForObject("http://localhost:" + port + "/faculty", testFaculty, Faculty.class);
        String newColor="newColorTestBlack";
        newFaculty.setColor(newColor);
        Long idNewFaculty=newFaculty.getId();
        testRestTemplate.put("http://localhost:" + port + "/faculty",newFaculty);
        ResponseEntity<Faculty> response=testRestTemplate.getForEntity(
                        "http://localhost:" + port +"/faculty/"+idNewFaculty, Faculty.class);
        assertEquals(OK,response.getStatusCode());
        Faculty body=response.getBody();
        String getColor=body.getColor();
        assertEquals(newColor,getColor);
        testRestTemplate.delete("http://localhost:" + port + "/faculty"+body.getId());


        //assertEquals(newColor,);



    }

    @Test
    void delFaculty() {
        Faculty testFaculty = new Faculty();
        testFaculty.setName("test2");
        testFaculty.setColor("colorTest2");
        ResponseEntity<Faculty> entityFaculty=testRestTemplate.postForEntity("http://localhost:" + port + "/faculty", testFaculty, Faculty.class);
        Faculty delFaculty=entityFaculty.getBody();
        long idFacultyDel = delFaculty.getId();
        System.out.println(idFacultyDel);
        testRestTemplate.delete("http://localhost:" + port + "/faculty" + idFacultyDel);
        ResponseEntity<Faculty> facultyResponseEntity = testRestTemplate.getForEntity("http://localhost:" + port + "/faculty" + idFacultyDel,
                Faculty.class);
        assertEquals(NOT_FOUND, facultyResponseEntity.getStatusCode());
    }

    @Test
    void getFacultyByColor() {
        Faculty testFaculty1 = new Faculty();
        testFaculty1.setName("test3");
        testFaculty1.setColor("color1");
        ResponseEntity<Faculty> response1=testRestTemplate.
                postForEntity("http://localhost:" + port + "/faculty", testFaculty1, Faculty.class);
        Faculty faculty1=response1.getBody();
        Long id1=faculty1.getId();
        Faculty testFaculty2 = new Faculty();
        testFaculty2.setName("test4");
        testFaculty2.setColor("color1");
        ResponseEntity<Faculty> response2=testRestTemplate.
                postForEntity("http://localhost:" + port + "/faculty", testFaculty2, Faculty.class);
        Faculty faculty2=response2.getBody();
        Long id2=faculty2.getId();

        assertEquals(2, testRestTemplate.getForEntity("http://localhost:" + port + "/faculty/filtered/color1", List.class).getBody().size());
        testRestTemplate.delete("http://localhost:" + port + "/faculty/"+id1);
        testRestTemplate.delete("http://localhost:" + port + "/faculty/"+id2);

    }
}