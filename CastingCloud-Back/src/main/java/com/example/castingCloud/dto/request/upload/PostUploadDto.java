package com.example.castingCloud.dto.request.upload;

import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostUploadDto {
    @NotBlank
    private String videoUrl;
    @NotBlank
    private String videoCategoryGender;
    @NotBlank
    private String videoCategoryAge;
    @NotBlank
    private String videoCategoryGenre;
    @NotBlank
    private String videoCategoryPosition;
}
