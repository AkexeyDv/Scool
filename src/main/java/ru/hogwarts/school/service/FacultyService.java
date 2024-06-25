package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.ErrorApp.ExceptionApp;
import ru.hogwarts.school.model.Faculty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

@Service
public class FacultyService {
    private final HashMap<Long, Faculty> facultys = new HashMap<>();
    private Long idxFacultetSpinner = 0L;


    public Faculty createFaculty(Faculty faculty) {
        idxFacultetSpinner++;
        faculty.setId(idxFacultetSpinner);
        facultys.put(idxFacultetSpinner, faculty);
        return faculty;

    }

    public Faculty updateFaculty(Faculty faculty) {
        facultys.put(faculty.getId(), faculty);
        return faculty;
    }

    public Faculty readFaculty(Long idFaculty) {
        if (!facultys.containsKey(idFaculty)) {
            throw new ExceptionApp("Такого факультета нет. Чтение невозможно");
        }
        return facultys.get(idFaculty);
    }


    public Faculty deleteFaculty(Long idFaculty) {
        if (!facultys.containsKey(idFaculty)) {
            throw new ExceptionApp("Такого факультета нет. Удаление невозможно");
        }
        Faculty removeFaculty = facultys.get(idFaculty);
        facultys.remove(idFaculty);
        return removeFaculty;
    }

    public ArrayList<Faculty> filteredByColor(String color) {
        ArrayList<Faculty> factColor = new ArrayList<>();
        Set<Long> setKey = facultys.keySet();
        for (Long s : setKey) {
            if (facultys.get(s).getColor().equals(color)) {
                factColor.add(facultys.get(s));
            }

        }
        return factColor;
    }

    @Override
    public String toString() {
        return "FacultyService{" +
                "facultys=" + facultys + '}';
    }
}
