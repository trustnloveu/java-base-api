package kr.co.ejyang.main_api.service;

import kr.co.ejyang.main_api.dto.FileParamDto;
import kr.co.ejyang.main_api.dto.FileRedisDto;
import kr.co.ejyang.module_file.domain.FileDto;
import kr.co.ejyang.module_file.service.FileServiceImplForLocal;
import kr.co.ejyang.module_file_util.util.FileCommonUtil;
import kr.co.ejyang.module_redis.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static kr.co.ejyang.main_api.config.CommonConsts.RD_KEY_TEMP_URL_PREFIX;

@Slf4j
@Service
public class FileService {

    private final FileServiceImplForLocal fileService;
    private final FileCommonUtil fileUtil;
    private final RedisUtil redisUtil;

    // 생성자
    FileService(
        @Autowired FileServiceImplForLocal fileService,
        @Autowired FileCommonUtil fileUtil,
        @Autowired RedisUtil redisUtil
    ) {
        this.fileService = fileService;
        this.fileUtil = fileUtil;
        this.redisUtil = redisUtil;
    }

    /*******************************************************************************************
     * 단일 파일 업로드 - 파일명 입력 X
     *******************************************************************************************/
    public FileDto uploadSingleFileWithoutName(FileParamDto.Upload param, MultipartFile file) {
        return fileService.uploadSingleFile(param.savePath, file);
    }

    /*******************************************************************************************
     * 단일 파일 업로드 - 파일명 입력 O
     *******************************************************************************************/
    public FileDto uploadSingleFileWithName(FileParamDto.UploadWithName param, MultipartFile file) {
        return fileService.uploadSingleFile(param.savePath, param.saveName, file);
    }

    /*******************************************************************************************
     * 복수 파일 업로드
     *******************************************************************************************/
    public List<FileDto> uploadMultiFiles(FileParamDto.Upload param, MultipartFile[] files) {
        return fileService.uploadMultiFiles(param.savePath, files);
    }

    /*******************************************************************************************
     * 파일 삭제
     *******************************************************************************************/
    public void deleteFile(String path) {
        fileService.removeFile(path);
    }

    /*******************************************************************************************
     * 파일 다운로드
     *******************************************************************************************/
    public InputStreamResource downloadFile(String path) {
        return fileService.downloadFile(path);
    }

    /*******************************************************************************************
     * 임시 URL 발급 ( Redis Key )
     *******************************************************************************************/
    public String updateFileTempUrlOnRedis(String savePath, String saveName) {
        // 임시 URL 생성
        String tempUrl = RD_KEY_TEMP_URL_PREFIX + fileUtil.generateFileTempUrl();

        // Redis Value 설정
        FileRedisDto redisDto = FileRedisDto.builder()
                .savePath(savePath)
                .saveName(saveName)
                .build();

        // Redis 업데이트
        redisUtil.setRedisData(tempUrl, redisDto.toString());

        // 등록된 임시 URL 반환
        return tempUrl;
    }

}
