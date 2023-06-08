package kr.co.ejyang.main_api.submodule.module_file;

import kr.co.ejyang.main_api.dto.FileResponseDto;
import kr.co.ejyang.module_file.domain.FileDto;
import kr.co.ejyang.module_file.service.FileServiceImplForLocal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Component
public class FileModuleUtil {

    private final FileServiceImplForLocal fileService;

    // 생성자
    public FileModuleUtil(@Autowired FileServiceImplForLocal fileService) {
        this.fileService = fileService;
    }

    /*******************************************************************************************
     * 단일 파일 업로드 - 파일명 입력 X
     *******************************************************************************************/
    public FileResponseDto uploadSingleFileWithoutName(String savePath, MultipartFile file) {

        FileDto fileDto = fileService.uploadSingleFile(savePath, file);

        return FileResponseDto.builder()
                .orgName(fileDto.getOrgName())
                .saveName(fileDto.getSaveName())
                .savePath(fileDto.getSavePath())
                .size(fileDto.getSize())
                .extType(fileDto.getExtType())
                .build();
    }

    /*******************************************************************************************
     * 단일 파일 업로드 - 파일명 입력 O
     *******************************************************************************************/
    public FileResponseDto uploadSingleFileWithName(String savePath, String saveName, MultipartFile file) {

        FileDto fileDto = fileService.uploadSingleFile(savePath, saveName, file);

        return FileResponseDto.builder()
                .orgName(fileDto.getOrgName())
                .saveName(fileDto.getSaveName())
                .savePath(fileDto.getSavePath())
                .size(fileDto.getSize())
                .extType(fileDto.getExtType())
                .build();
    }

    /*******************************************************************************************
     * 복수 파일 업로드
     *******************************************************************************************/
    public List<FileResponseDto> uploadMultiFiles(String savePath, MultipartFile[] files) {
        List<FileResponseDto> rtnList = new ArrayList<>();

        List<FileDto> fileDtoList = fileService.uploadMultiFiles(savePath, files);

        for (FileDto dto : fileDtoList) {
            rtnList.add(FileResponseDto.builder()
                    .orgName(dto.getOrgName())
                    .saveName(dto.getSaveName())
                    .savePath(dto.getSavePath())
                    .size(dto.getSize())
                    .extType(dto.getExtType())
                    .build());
        }

        return rtnList;
    }

    /*******************************************************************************************
     * 파일 삭제
     *******************************************************************************************/
    public void deleteFile(String path) {
        fileService.removeFile(path);
    }

    /*******************************************************************************************
     * 파일 다운로드 (1) - 경로 Only
     *******************************************************************************************/
    public InputStreamResource downloadFile(String savePath) {
        return fileService.downloadFile(savePath);
    }

    /*******************************************************************************************
     * 파일 다운로드 (2) - 경로 + 상세
     *******************************************************************************************/
    public InputStreamResource downloadFile(String savePath, String description) {
        return fileService.downloadFile(savePath, description);
    }

}
