package ru.hogwarts.school.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.hogwarts.school.model.Student;

import java.util.ArrayList;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student,Long> {
    ArrayList<Student> findByAge(int age);
    @Query(value = "SELECT * FROM student",nativeQuery = true)
    ArrayList<Student> findAllStudent();

    ArrayList<Student> findByAgeBetween(int minAge, int maxAge);
    Optional<Student> findById(Long id);

}


