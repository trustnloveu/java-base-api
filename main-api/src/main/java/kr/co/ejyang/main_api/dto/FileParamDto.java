package kr.co.ejyang.main_api.dto;

import jakarta.validation.constraints.*;
import lombok.NoArgsConstructor;

public class FileParamDto {

    // 파일 업로드 ( 경로 Only )
    @NoArgsConstructor
    public static class Upload {
        @NotBlank(message = "파일 경로가 입력되지 않았습니다.")
        @Pattern(regexp = "^\\/.*", message = "파일 경로는 '/'로 시작되어야 합니다.")
        @Pattern(regexp = "^[A-Za-z0-9가-힣./_-]+$", message = "입력한 경로에 '.', '/', '-', '_'를 제외한 특수문자 사용은 불가능합니다.")
        public String savePath;
    }

    // 파일 업로드 ( 경로 + 지정 파일명 )
    @NoArgsConstructor
    public static class UploadWithName {
        @NotBlank(message = "파일 경로가 입력되지 않았습니다.")
        @Pattern(regexp = "^\\/.*", message = "파일 경로는 '/'로 시작되어야 합니다.")
        @Pattern(regexp = "^[A-Za-z0-9가-힣./_-]+$", message = "입력한 경로에 '.', '/', '-', '_'를 제외한 특수문자 사용은 불가능합니다.")
        public String savePath;

        @NotNull
        @NotBlank(message = "저장 파일명이 입력되지 않았습니다.")
        @Pattern(regexp = "^[A-Za-z0-9가-힣./_-]+$", message = "입력한 경로에 '.', '/', '-', '_'를 제외한 특수문자 사용은 불가능합니다.")
        public String saveName;
    }

    // 다운로드
    @NoArgsConstructor
    public static class Download {
        @NotBlank(message = "파일 경로가 입력되지 않았습니다.")
        @Pattern(regexp = "^\\/.*", message = "파일 경로는 '/'로 시작되어야 합니다.")
        @Pattern(regexp = "^[A-Za-z0-9가-힣./_-]+$", message = "입력한 경로에 '.', '/', '-', '_'를 제외한 특수문자 사용은 불가능합니다.")
        public String savePath;

        @NotBlank(message = "파일명이 입력되지 않았습니다.")
        public String fileName;
    }

    // 삭제
    @NoArgsConstructor
    public static class Delete {
        @NotBlank(message = "파일 경로가 입력되지 않았습니다.")
        @Pattern(regexp = "^\\/.*", message = "파일 경로는 '/'로 시작되어야 합니다.")
        @Pattern(regexp = "^[A-Za-z0-9가-힣./_-]+$", message = "입력한 경로에 '.', '/', '-', '_'를 제외한 특수문자 사용은 불가능합니다.")
        public String savePath;
    }


}
