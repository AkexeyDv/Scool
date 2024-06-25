package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.service.FacultyService;

import java.util.ArrayList;

@RequestMapping("faculty")
@RestController
public class FacultyController {
    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }


    @PostMapping
    public Faculty createFaculty(@RequestBody Faculty faculty) {
        facultyService.createFaculty(faculty);
        return faculty;
    }


    @GetMapping("{idFacultet}")
    public ResponseEntity<Faculty> getFaculty(@PathVariable Long idFacultet) {
        if (facultyService.deleteFaculty(idFacultet) == null) {
            ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(facultyService.readFaculty(idFacultet));
    }

    @PutMapping()
    public ResponseEntity<Faculty> putFaculty(Faculty faculty) {
        if (facultyService.readFaculty(faculty.getId()) == null) {
            ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(facultyService.updateFaculty(faculty));
    }

    @DeleteMapping("{idFacultet}")
    public Faculty delFaculty(@PathVariable Long idFacultet) {
        if (facultyService.deleteFaculty(idFacultet) == null) {
            ResponseEntity.notFound().build();
        }
        return facultyService.deleteFaculty(idFacultet);
    }

    @GetMapping("/filtered/{color}")
    public ArrayList<Faculty> getFacultyByColor(@PathVariable String color) {
        return facultyService.filteredByColor(color);
    }


}
