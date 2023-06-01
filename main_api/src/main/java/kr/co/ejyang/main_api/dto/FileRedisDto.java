package kr.co.ejyang.main_api.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
@AllArgsConstructor
public class FileRedisDto {

    @NotBlank(message = "파일 경로가 필요합니다.")
    public String savePath;

    @NotBlank(message = "파일명이 필요합니다.")
    public String saveName;

}
