package kr.co.ejyang.main_api.submodule.module_file;

import kr.co.ejyang.main_api.dto.FileResponseDto;
import kr.co.ejyang.module_file.domain.FileDto;
import kr.co.ejyang.module_file.service.FileServiceImplForLocal;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
public class FileModuleUtil {

    private final FileServiceImplForLocal fileService;

    /*******************************************************************************************
     * 단일 파일 업로드 - 파일명 입력 X
     *******************************************************************************************/
    public FileResponseDto uploadSingleFileWithoutName(String storageKey, String saveType, String savePath, MultipartFile file) {

        FileDto fileDto = fileService.uploadSingleFile(storageKey, saveType, savePath, file);

        return FileResponseDto.builder()
                .storageKey(fileDto.getStorageKey())
                .orgName(fileDto.getOrgName())
                .saveName(fileDto.getSaveName())
                .saveDirPath(fileDto.getSaveDirPath())
                .fullPath(fileDto.getFullPath())
                .url(fileDto.getUrl())
                .size(fileDto.getSize())
                .fileType(fileDto.getFileType())
                .extType(fileDto.getExtType())
                .build();
    }

    /*******************************************************************************************
     * 단일 파일 업로드 - 파일명 입력 O
     *******************************************************************************************/
    public FileResponseDto uploadSingleFileWithName(String storageKey, String saveType, String savePath, String saveName, MultipartFile file) {

        FileDto fileDto = fileService.uploadSingleFile(storageKey, saveType, savePath, saveName, file);

        return FileResponseDto.builder()
                .storageKey(fileDto.getStorageKey())
                .orgName(fileDto.getOrgName())
                .saveName(fileDto.getSaveName())
                .saveDirPath(fileDto.getSaveDirPath())
                .fullPath(fileDto.getFullPath())
                .url(fileDto.getUrl())
                .size(fileDto.getSize())
                .fileType(fileDto.getFileType())
                .extType(fileDto.getExtType())
                .build();
    }

    /*******************************************************************************************
     * 복수 파일 업로드
     *******************************************************************************************/
    public List<FileResponseDto> uploadMultiFiles(String storageKey, String saveType, String savePath, MultipartFile[] files) {
        List<FileResponseDto> rtnList = new ArrayList<>();

        List<FileDto> fileDtoList = fileService.uploadMultiFiles(storageKey, saveType, savePath, files);

        for (FileDto fileDto : fileDtoList) {
            rtnList.add(FileResponseDto.builder()
                    .storageKey(fileDto.getStorageKey())
                    .orgName(fileDto.getOrgName())
                    .saveName(fileDto.getSaveName())
                    .saveDirPath(fileDto.getSaveDirPath())
                    .fullPath(fileDto.getFullPath())
                    .url(fileDto.getUrl())
                    .size(fileDto.getSize())
                    .fileType(fileDto.getFileType())
                    .extType(fileDto.getExtType())
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
     * 파일 다운로드
     *******************************************************************************************/
    public InputStreamResource downloadFile(String savePath) {
        return fileService.downloadFile(savePath);
    }

    /*******************************************************************************************
     * 파일 조회
     *******************************************************************************************/
    public byte[] getPrivateFile(String savePath) {
        return fileService.getPrivateFile(savePath);
    }

    /*******************************************************************************************
     * 파일 조회
     *******************************************************************************************/
    public byte[] getPublicFile(String savePath) {
        return fileService.getPublicFile(savePath);
    }

}
