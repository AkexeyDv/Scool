package ru.hogwarts.school.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.hogwarts.school.ErrorApp.ExceptionApp;
import ru.hogwarts.school.Repository.FacultyRepository;
import ru.hogwarts.school.model.Faculty;

import static org.junit.jupiter.api.Assertions.*;

class FacultyServiceTest {
    private ru.hogwarts.school.Repository.FacultyRepository FacultyRepository;
    FacultyService out = new FacultyService(FacultyRepository);

    @BeforeEach
    void setUp() {
        Faculty newFaculty = new Faculty(10l, "G", "Red");

        out.createFaculty(newFaculty);
    }

    @Test
    void createFaculty() {

        Assertions.assertEquals(1l, out.readFaculty(1l).getId());

    }

    @Test
    void updateFaculty() {
        Faculty updateFaculty = new Faculty(1l, "Sliseren", "Black");
        out.updateFaculty(updateFaculty);
        Assertions.assertEquals(out.readFaculty(1l).getName(), "Sliseren");
        Assertions.assertEquals(out.readFaculty(1l).getColor(), "Black");
    }

    @Test
    void readFaculty() {
        Faculty readFaculty = new Faculty(258l, "Griffendor", "Red");
        out.createFaculty(readFaculty);
        Assertions.assertThrows(ExceptionApp.class, () -> out.readFaculty(258l).getId());
        Assertions.assertEquals(out.readFaculty(2l).getName(), "Griffendor");
    }


    @Test
    void deleteFaculty() {
        out.deleteFaculty(1l);
        Assertions.assertThrows(ExceptionApp.class, () -> out.readFaculty(1l).getId());

    }

    @Test
    void filteredByColor() {
        out.createFaculty(new Faculty(0l, "A", "Red"));
        out.createFaculty(new Faculty(0l, "B", "Blue"));
        Assertions.assertEquals(out.findByColor("Red").stream().toArray().length, 2);
    }
}