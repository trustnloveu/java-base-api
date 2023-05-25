package kr.co.ejyang.main_api.service;

import kr.co.ejyang.main_api.dto.FileParamDto;
import kr.co.ejyang.module_file.domain.FileDto;
import kr.co.ejyang.module_file.service.FileServiceImplForLocal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

//import static kr.co.ejyang.module_file.config.CommonConsts.*;

@Service
@Slf4j
public class MainService {

    private final FileServiceImplForLocal fileService;

    // 생성자
    MainService(@Autowired FileServiceImplForLocal fileService) {
        this.fileService = fileService;
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

}
