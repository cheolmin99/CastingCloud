package com.example.castingCloud.service;

import com.example.castingCloud.dto.response.ResponseDto;
import com.example.castingCloud.dto.response.upload.PostUploadResponseDto;
import com.example.castingCloud.dto.request.upload.PostUploadDto;

public interface VideoService {
    public ResponseDto<PostUploadResponseDto> postUpload(String actorEmail, PostUploadDto dto);
}
