package com.example.castingCloud.dto.request.upload;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostUploadDto {
    private String videoUrl;
    private String videoCategoryGender;
    private String videoCategoryAge;
    private String videoCategoryGenre;
    private String videoCategoryPosition;
}
