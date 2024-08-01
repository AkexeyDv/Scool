package ru.hogwarts.school.model;

import jakarta.persistence.*;

import static jakarta.persistence.GenerationType.IDENTITY;
@Entity
public class Avatar {
    @Id
    @GeneratedValue(strategy =IDENTITY)
    Long id;
    String filePath;
    long fileSize;
    String mediaType;
    @Lob
    byte[] data;
    @OneToOne
    Student student;

    public Avatar(){};

    public void setId(Long id) {
        this.id = id;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
