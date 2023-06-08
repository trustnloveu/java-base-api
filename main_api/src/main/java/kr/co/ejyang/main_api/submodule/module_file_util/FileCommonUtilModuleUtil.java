package kr.co.ejyang.main_api.submodule.module_file_util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import kr.co.ejyang.module_file_util.util.FileCommonUtil;

@Component
public class FileCommonUtilModuleUtil {

    private final FileCommonUtil fileCommonUtil;

    // 생성자
    public FileCommonUtilModuleUtil(@Autowired FileCommonUtil fileCommonUtil) {
        this.fileCommonUtil = fileCommonUtil;
    }

    /*******************************************************************************************
     * 파일 검증 ( Null, 파일명, 용량, 확장자 )
     *******************************************************************************************/
    public boolean isValidFile(MultipartFile file) {
        return fileCommonUtil.isValidFile(file);
    }

    /*******************************************************************************************
     * 디렉토리 타입 검증 ( public, private, static )
     *******************************************************************************************/
    public boolean isValidDirType(String dirType) {
        return fileCommonUtil.isValidDirType(dirType);
    }

    /*******************************************************************************************
     * 파일명 변환 - 타임스탬프 + 랜덤 정수 ( convertFileName )
     *******************************************************************************************/
    public String convertFileName(MultipartFile file) {
        return fileCommonUtil.convertFileName(file);
    }

    /*******************************************************************************************
     * 임시 파일명 URL 발급 ( UUID - 타임스템프 )
     *  - Redis Key 사용
     *******************************************************************************************/
    public String generateFileTempUrl() {
        return fileCommonUtil.generateFileTempUrl();
    }
}
