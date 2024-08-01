package ru.hogwarts.school.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.Repository.AvatarRepository;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.model.Student;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Service
@Transactional

public class AvatarService {
    @Value("avatars")
    private String avatarsDir;
    private final StudentService studentService;
    private final AvatarRepository avatarRepository;

    public AvatarService(StudentService studentService, AvatarRepository avatarRepository) {
        this.studentService = studentService;
        this.avatarRepository = avatarRepository;
    }

    public void uploadAvatar(Long idStudent, MultipartFile file) throws IOException {
        Student student = studentService.findById(idStudent);
        String exestensionFile = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
        Path pathFile = Path.of(avatarsDir, idStudent + "." + exestensionFile);
        Files.createDirectories(pathFile.getParent());
        Files.deleteIfExists(pathFile);

        try (InputStream is = file.getInputStream();
             OutputStream os = Files.newOutputStream(pathFile, CREATE_NEW);
             BufferedInputStream bis = new BufferedInputStream(is, 1024);
             BufferedOutputStream bos = new BufferedOutputStream(os, 1024)
        ) {
            bis.transferTo(bos);
        }

        Avatar avatar=findAvatar(idStudent);
        avatar.setStudent(student);
        avatar.setFilePath(pathFile.toString());
        avatar.setFileSize(file.getSize());
        avatar.setMediaType(file.getContentType());


    }

    public Avatar findAvatar(Long id){
        return avatarRepository.findByStudentId(id).orElseThrow(null);
    }
}
