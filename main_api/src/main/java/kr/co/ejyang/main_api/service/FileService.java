package kr.co.ejyang.main_api.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.ejyang.main_api.dto.FileParamDto;
import kr.co.ejyang.main_api.dto.FileRedisDto;
import kr.co.ejyang.main_api.dto.FileResponseDto;
import kr.co.ejyang.main_api.model.ApiResponse;
import kr.co.ejyang.main_api.submodule.module_file.FileModuleUtil;
import kr.co.ejyang.main_api.submodule.module_file_util.FileCommonUtilModuleUtil;
import kr.co.ejyang.main_api.submodule.module_redis.RedisModuleUtil;
import kr.co.ejyang.module_file.exception.FileModuleException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.regex.Pattern;

import static kr.co.ejyang.main_api.config.CommonConsts.RD_KEY_TEMP_URL_PREFIX;

@Slf4j
@RequiredArgsConstructor
@Service
public class FileService {

    @Value("${file.url}")
    private String fileServerUrl;

    private final FileModuleUtil fileModuleUtil;    // 파일 모듈
    private final FileCommonUtilModuleUtil fileCommonUtilModuleUtil;      // 파일 모듈 유틸
    private final RedisModuleUtil redisModuleUtil;    // 레디스 모듈

    // #########################################################################################
    //                                      [ PUBLIC ]
    // #########################################################################################

    /*******************************************************************************************
     * 단일 파일 업로드 - 파일명 입력 X
     *******************************************************************************************/
    public ApiResponse uploadSingleFileWithoutName(FileParamDto.Upload param, MultipartFile file) throws JsonProcessingException {

        boolean isValid = validateFileName(file);

        if (!isValid)
            throw new FileModuleException("파일명에 '.', '/', '-', '_'를 제외한 특수문자 및 공백문자를 허용하지 않습니다.");

        return new ApiResponse("success", "200", 200, new ObjectMapper().writeValueAsString(fileModuleUtil.uploadSingleFileWithoutName(
                param.storageKey,
                param.saveType,
                param.saveDirPath,
                file
        )));
    }

    /*******************************************************************************************
     * 단일 파일 업로드 - 파일명 입력 O
     *******************************************************************************************/
    public FileResponseDto uploadSingleFileWithName(FileParamDto.UploadWithName param, MultipartFile file) {

        boolean isValid = validateFileName(file);

        if (!isValid)
            throw new FileModuleException("파일명에 '.', '/', '-', '_'를 제외한 특수문자 및 공백문자를 허용하지 않습니다.");

        return fileModuleUtil.uploadSingleFileWithName(
                param.storageKey,
                param.saveType,
                param.saveDirPath,
                param.saveName,
                file
        );
    }

    /*******************************************************************************************
     * 복수 파일 업로드
     *******************************************************************************************/
    public List<FileResponseDto> uploadMultiFiles(FileParamDto.Upload param, MultipartFile[] files) {

        for (MultipartFile file : files) {
            boolean isValid = validateFileName(file);

            if (!isValid)
                throw new FileModuleException("파일명에 '.', '/', '-', '_'를 제외한 특수문자 및 공백문자를 허용하지 않습니다.");
        }

        return fileModuleUtil.uploadMultiFiles(
                param.storageKey,
                param.saveType,
                param.saveDirPath,
                files
        );
    }

    /*******************************************************************************************
     * 파일 삭제
     *******************************************************************************************/
    public void deleteFile(String path) {
        fileModuleUtil.deleteFile(path);
    }

    /*******************************************************************************************
     * 파일 다운로드
     *******************************************************************************************/
    public InputStreamResource downloadFile(String path) {
        return fileModuleUtil.downloadFile(path);
    }

    /*******************************************************************************************
     * 임시 URL 발급 ( Redis Key )
     *******************************************************************************************/
    public String updateFileTempUrlOnRedis(String fullPath, String fileName) {
        // 임시 URL 생성
        String tempUrl = RD_KEY_TEMP_URL_PREFIX + fileCommonUtilModuleUtil.generateFileTempUrl();

        // Redis Value 설정
        FileRedisDto redisDto = FileRedisDto.builder()
                .savePath(fullPath)
                .saveName(fileName)
                .build();

        // Redis 업데이트
        redisModuleUtil.setRedisDataWithTTL(tempUrl, redisDto.toStringJson());

        // 등록된 임시 URL 반환
        return fileServerUrl + "/open/file/view/private/" + tempUrl;
    }

    // #########################################################################################
    //                                      [ PRIVATE ]
    // #########################################################################################

    /*******************************************************************************************
     * 파일명 검증 - '.', '/', '-', '_'를 제외한 특수문자 및 공백문자 사용 불가
     *******************************************************************************************/
    private boolean validateFileName(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        String regExp = "^[A-Za-z0-9가-힣./_-]+[^ ]*$";

        return Pattern.matches(regExp, fileName);
    }

}
