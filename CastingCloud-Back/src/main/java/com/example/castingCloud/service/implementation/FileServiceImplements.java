package com.example.castingCloud.service.implementation;

import java.io.File;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.castingCloud.service.FileService;

@Service
public class FileServiceImplements implements FileService{
    @Value("${file.path}")
    private String FILE_PATH;
    @Value("${file.url}")
    private String FILE_URL;

    public String upload(MultipartFile file) {
        if (file.isEmpty()) return null;

        String originalFileName = file.getOriginalFilename();
        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));

        String uuid = UUID.randomUUID().toString();

        String saveName = uuid + extension;
        String savePath = FILE_PATH + saveName;

        try {
            file.transferTo(new File(savePath));
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
        String url = FILE_URL + saveName;
        return url;
    }
}
