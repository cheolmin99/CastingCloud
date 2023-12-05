package com.example.castingCloud.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.castingCloud.common.ApiPattern;
import com.example.castingCloud.service.FileService;

@RestController
@RequestMapping(ApiPattern.FILE)
public class FileController {
    @Autowired private FileService fileService;
    private String filePath;

    @PostMapping("/upload")
    public String upload(
        @RequestParam("file") MultipartFile file
    ) {
        String response = fileService.upload(file);
        return response;
    }

    @GetMapping(value = "{fileName}")
public ResponseEntity<Resource> getFile(@PathVariable("fileName") String fileName) {
    Resource resource = fileService.getFile(fileName);

    try {
        MediaType mediaType = MediaType.parseMediaType(Files.probeContentType(Path.of(filePath + fileName)));

        return ResponseEntity.ok()
                .contentType(mediaType)
                .body(resource);
    } catch (IOException e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}
    
}
