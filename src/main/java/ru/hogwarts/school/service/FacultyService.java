package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.Repository.FacultyRepository;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public Faculty seekById(Long idFaculty) {
        return Optional.of(facultyRepository.findById(idFaculty)).get().orElse(null);


    }

    public List<Student> getStudent(long idFaculty) {
        Faculty tmpFaculty = facultyRepository.findById(idFaculty).get();
        return tmpFaculty.getStudent();
    }


    public Faculty deleteFaculty(Long idFaculty) {
        Faculty delFaculty = seekById(idFaculty);

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
