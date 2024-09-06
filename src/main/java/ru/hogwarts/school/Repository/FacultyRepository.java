package ru.hogwarts.school.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.school.model.Faculty;

import java.util.ArrayList;
import java.util.Optional;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {
    public ArrayList<Faculty> findByColor(String color);

    public Optional<Faculty> findById(Long idFaculty);

}
