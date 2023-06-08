package kr.co.ejyang.main_api.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
public class FileResponseDto {

    @NotNull
    private String orgName; // 원본 파일명

    @Null
    private String saveName; // 저장 파일명

    @Null
    private String savePath; // 저장 경로 ( 디렉토리 + 파일명 )

    @NotNull
    private long size; // 파일욜량 ( = byte )

    @NotNull
    private String extType; // 파일 확장자

}
