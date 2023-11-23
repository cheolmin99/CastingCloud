package com.example.castingCloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
    
    private final String UPLOAD = "/upload";

    @PostMapping(UPLOAD)
    public String upload(
        @RequestParam("file") MultipartFile file
    ) {
        String response = fileService.upload(file);
        return response;
    }
}
