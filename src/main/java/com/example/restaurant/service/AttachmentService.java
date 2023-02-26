package com.example.restaurant.service;

import com.example.restaurant.entity.Attachment;
import com.example.restaurant.repository.AttachmentRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AttachmentService {

    private final AttachmentRepository attachmentRepository;

    @SneakyThrows
    public UUID saveFile(MultipartFile file) {
        UUID uuid = UUID.randomUUID();
        Attachment attachment = new Attachment(uuid, "images/" + uuid + ".jpg");
        Attachment save = attachmentRepository.save(attachment);
        FileCopyUtils.copy(file.getInputStream(), new FileOutputStream("images/" + save.getId() + ".jpg"));
        return save.getId();
    }

    @SneakyThrows
    public void getFile(UUID id, HttpServletResponse response) {
        FileCopyUtils.copy(
                new FileInputStream("images/" + id + ".jpg"),
                response.getOutputStream()
        );
     }
}
