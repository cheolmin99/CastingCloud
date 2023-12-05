package com.example.castingCloud.service.implementation;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.castingCloud.service.FileService;

@Service
public class FileServiceImplements implements FileService {
    @Value("${file.path}")
    private String filePath;
    @Value("${file.url}")
    private String fileUrl;

    public String upload(MultipartFile file) {
        if (file.isEmpty()) return null;

        String originalFileName = file.getOriginalFilename();
        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));

        String uuid = UUID.randomUUID().toString();

        String saveName = uuid + extension;
        String savePath = filePath + saveName;

        try {
            file.transferTo(new File(savePath));
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
        String url = fileUrl + saveName;
        return url;
    }

    public Resource getFile(String fileName) {
        try {
            Path file = Paths.get(filePath).resolve(fileName);
            Resource resource = new UrlResource(file.toUri());
    
            if (resource.exists() && resource.isReadable()) {
                // 파일 타입에 따라 적절한 MIME 타입을 설정
                String contentType = determineContentType(fileName);
                return new DefaultResourceLoader().getResource("file:" + file.toString());
            } else {
                // 파일을 찾을 수 없거나 읽을 수 없는 경우 처리
                return null;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }

    private String determineContentType(String fileName) {
        // 파일 확장자에 따라 MIME 타입을 결정하여 반환
        if (fileName.endsWith(".mp4")) {
            return "video/mp4";
        } else if (fileName.endsWith(".jpg") || fileName.endsWith(".jpeg")) {
            return "image/jpeg";
        } else if (fileName.endsWith(".png")) {
            return "image/png";
        } else {
            // 다른 MIME 타입 처리
            return "application/octet-stream";
        }
    }

}