package com.example.castingCloud.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.castingCloud.common.ResponseMessage;
import com.example.castingCloud.dto.request.upload.PostUploadDto;
import com.example.castingCloud.dto.response.ResponseDto;
import com.example.castingCloud.dto.response.upload.PostUploadResponseDto;
import com.example.castingCloud.entity.ActorEntity;
import com.example.castingCloud.entity.VideoEntity;
import com.example.castingCloud.repository.ActorRepository;
import com.example.castingCloud.repository.VideoRepository;
import com.example.castingCloud.service.VideoService;

@Service
public class VideoServiceImplements implements VideoService {
    @Autowired
    private ActorRepository actorRepository;

    @Autowired
    private VideoRepository videoRepository;

    public ResponseDto<PostUploadResponseDto> postUpload(String actorEmail, PostUploadDto dto) {
        PostUploadResponseDto data = null;

        try {
            ActorEntity actorEntity = actorRepository.findByActorEmail(actorEmail);
            if(actorEntity == null)
            return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_USER);

            VideoEntity videoEntity = new VideoEntity(actorEntity, dto);
            videoRepository.save(videoEntity);

            data = new PostUploadResponseDto(videoEntity);
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

}
