package ru.hogwarts.school.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.school.model.Faculty;

import java.util.ArrayList;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {
    public ArrayList<Faculty> findByColor(String color);
}
