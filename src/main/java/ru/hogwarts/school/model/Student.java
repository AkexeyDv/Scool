package ru.hogwarts.school.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.Objects;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty(access = READ_ONLY)
    private Long id;
    private String name;
    private int age;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="faculty_id",referencedColumnName = "id")
    private Faculty faculty;

    public Student(Long id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;

    }

    public Student(){}

    public Faculty getFaculty(){
        return faculty;
    }


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Student student = (Student) object;
        return age == student.age && Objects.equals(id, student.id) && Objects.equals(name, student.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, age);
    }
}
