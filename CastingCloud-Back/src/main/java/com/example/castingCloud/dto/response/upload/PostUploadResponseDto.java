package com.example.castingCloud.dto.response.upload;

import com.example.castingCloud.entity.VideoEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PostUploadResponseDto {
    private VideoEntity video;
}
