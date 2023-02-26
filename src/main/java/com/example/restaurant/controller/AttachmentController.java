package com.example.restaurant.controller;

import com.example.restaurant.service.AttachmentService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/img")
public class AttachmentController {

    private final AttachmentService service;


    @PostMapping
    public UUID saveFile(@RequestBody MultipartFile file) {
        return service.saveFile(file);
    }

    @GetMapping("/{id}")
    public void getFile(@PathVariable UUID id, HttpServletResponse response) {
        service.getFile(id, response);
    }


}
