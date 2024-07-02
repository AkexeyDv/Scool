package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.ErrorApp.ExceptionApp;
import ru.hogwarts.school.Repository.FacultyRepository;
import ru.hogwarts.school.model.Faculty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

@Service
public class FacultyService {


    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }


    public Faculty createFaculty(Faculty faculty) {

        return facultyRepository.save(faculty);

    }

    public Faculty updateFaculty(Faculty faculty) {

        return facultyRepository.save(faculty);
    }

    public Faculty readFaculty(Long idFaculty) {

        return facultyRepository.findById(idFaculty).get();
    }


    public Faculty deleteFaculty(Long idFaculty) {
        Faculty delFaculty = readFaculty(idFaculty);
        facultyRepository.deleteById(idFaculty);
        return delFaculty;
    }

    public ArrayList<Faculty> findByColor(String color) {

        return facultyRepository.findByColor(color);
    }

    @Override
    public String toString() {
        return facultyRepository.findAll().toString();
    }
}
