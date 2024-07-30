package ru.hogwarts.school.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.ArrayList;

public interface StudentRepository extends JpaRepository<Student,Long> {
    ArrayList<Student> findByAge(int age);
    @Query(value = "SELECT * FROM student",nativeQuery = true)
    ArrayList<Student> findAllStudent();

    ArrayList<Student> findByAgeBetween(int minAge, int maxAge);

}


