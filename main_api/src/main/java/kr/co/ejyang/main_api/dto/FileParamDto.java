package kr.co.ejyang.main_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;

public class FileParamDto {

    /*******************************************************************************************
     * 파일 업로드
     *  - 호출 Key
     *  - 경로
     *******************************************************************************************/
    @RequiredArgsConstructor
    public static class TempUrl {
        @NotBlank(message = "파일 경로가 입력되지 않았습니다.")
        @Pattern(regexp = "^\\/.*", message = "파일 경로는 '/'로 시작되어야 합니다.")
        @Pattern(regexp = "^[A-Za-z0-9가-힣./_-]+[^ ]*$", message = "입력한 경로에 '.', '/', '-', '_'를 제외한 특수문자 및 공백문자 사용은 불가능합니다.")
        public String fullPath;

        @NotBlank(message = "파일명이 입력되지 않았습니다.")
        @Pattern(regexp = "^[A-Za-z0-9가-힣./_-]+[^ ]*$", message = "입력한 경로에 '.', '/', '-', '_'를 제외한 특수문자 및 공백문자 사용은 불가능합니다.")
        public String fileName;
    }

    /*******************************************************************************************
     * 파일 업로드
     *  - 호출 Key
     *  - 경로
     *******************************************************************************************/
    @RequiredArgsConstructor
    public static class Upload {
        @NotBlank(message = "스토리지 키값이 입력되지 않았습니다. (호출 API에서 지정한 고유 Key)")
        @Length(max = 20, message = "키값은 20자 미만으로 입력되어야 합니다.")
        public String storageKey;

        @NotBlank(message = "저장 타입이 입력되지 않았습니다.")
        @Pattern(regexp = "^(public|private)$", message = "파일타입을 지정해주세요. (public 혹은 private)")
        public String saveType;

        @NotBlank(message = "파일 경로가 입력되지 않았습니다.")
        @Pattern(regexp = "^\\/.*", message = "파일 경로는 '/'로 시작되어야 합니다.")
        @Pattern(regexp = "^[A-Za-z0-9가-힣./_-]+[^ ]*$", message = "입력한 경로에 '.', '/', '-', '_'를 제외한 특수문자 및 공백문자 사용은 불가능합니다.")
        public String saveDirPath;
    }

    /*******************************************************************************************
     * 파일 업로드 ( 사용자 입력 파일명 )
     *  - 호출 Key
     *  - 경로
     *  - 지정 파일명
     *******************************************************************************************/
    @RequiredArgsConstructor
    public static class UploadWithName {
        @NotBlank(message = "스토리지 키값이 입력되지 않았습니다. (호출 API에서 지정한 고유 Key)")
        @Length(max = 20, message = "키값은 20자 미만으로 입력되어야 합니다.")
        public String storageKey;

        @NotBlank(message = "저장 타입이 입력되지 않았습니다.")
        @Pattern(regexp = "^(public|private)$", message = "파일타입을 지정해주세요. (public 혹은 private)")
        public String saveType;

        @NotBlank(message = "파일 경로가 입력되지 않았습니다.")
        @Pattern(regexp = "^\\/.*", message = "파일 경로는 '/'로 시작되어야 합니다.")
        @Pattern(regexp = "^[A-Za-z0-9가-힣./_-]+[^ ]*$", message = "입력한 경로에 '.', '/', '-', '_'를 제외한 특수문자 및 공백문자 사용은 불가능합니다.")
        public String saveDirPath;

        @NotBlank(message = "저장 파일명이 입력되지 않았습니다.")
        @Pattern(regexp = "^[A-Za-z0-9가-힣./_-]+[^ ]*$", message = "입력한 경로에 '.', '/', '-', '_'를 제외한 특수문자 및 공백문자 사용은 불가능합니다.")
        public String saveName;
    }

    /*******************************************************************************************
     * 파일 다운로드
     *  - 파일 경로
     *  - 다운로드 시 지정할 파일명
     *******************************************************************************************/
    @RequiredArgsConstructor
    public static class Download {
        @NotBlank(message = "파일 경로가 입력되지 않았습니다.")
        @Pattern(regexp = "^\\/.*", message = "파일 경로는 '/'로 시작되어야 합니다.")
        @Pattern(regexp = "^[A-Za-z0-9가-힣./_-]+[^ ]*$", message = "입력한 경로에 '.', '/', '-', '_'를 제외한 특수문자 및 공백문자 사용은 불가능합니다.")
        public String fullPath;

        @NotBlank(message = "파일명이 입력되지 않았습니다.")
        @Length(max = 20, message = "파일명은 20자 이상을 초과할 수 없습니다.")
        public String fileName;
    }

    /*******************************************************************************************
     * 파일 삭제
     *  - 호출 Key
     *  - 파일 경로
     *  - 다운로드 시 지정할 파일명
     *******************************************************************************************/
    @RequiredArgsConstructor
    public static class Delete {
        @NotBlank(message = "스토리지 키값이 입력되지 않았습니다. (호출 API에서 지정한 고유 Key)")
        @Length(max = 20, message = "키값은 20자 미만으로 입력되어야 합니다.")
        public String storageKey;

        @NotBlank(message = "파일 경로가 입력되지 않았습니다.")
        @Pattern(regexp = "^\\/.*", message = "파일 경로는 '/'로 시작되어야 합니다.")
        @Pattern(regexp = "^[A-Za-z0-9가-힣./_-]+[^ ]*$", message = "입력한 경로에 '.', '/', '-', '_'를 제외한 특수문자 및 공백문자 사용은 불가능합니다.")
        public String fullPath;
    }


}
