package com.example.castingCloud.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.castingCloud.common.ApiPattern;
import com.example.castingCloud.dto.request.upload.PostUploadDto;
import com.example.castingCloud.dto.response.ResponseDto;
import com.example.castingCloud.dto.response.upload.PostUploadResponseDto;
import com.example.castingCloud.service.VideoService;

@RestController
@RequestMapping(ApiPattern.VIDEO)
public class VideoController {

    @Autowired private VideoService videoService;

    private final String POST_VIDEO = "";

    @PostMapping(POST_VIDEO)
    public ResponseDto<PostUploadResponseDto> postUpload(
        @AuthenticationPrincipal String actorEmail,
        @Valid @RequestBody PostUploadDto requestBody
    ) {
        ResponseDto<PostUploadResponseDto> response = videoService.postUpload(actorEmail, requestBody);
        return response;
    }
}
