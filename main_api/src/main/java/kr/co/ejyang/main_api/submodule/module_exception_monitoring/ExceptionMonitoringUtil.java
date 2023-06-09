package kr.co.ejyang.main_api.submodule.module_exception_monitoring;

import kr.co.ejyang.main_api.dto.MonitoringResponseDto;
import kr.co.ejyang.module_exception_monitoring.dto.MonitoringDto;
import kr.co.ejyang.module_exception_monitoring.service.MonitoringServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
public class ExceptionMonitoringUtil {

    private final MonitoringServiceImpl monitoringService;

    // #########################################################################################
    //                                      [ PUBLIC ]
    // #########################################################################################

    // -----------------------------------------------------------------------------------------
    //                                      [ SELECT ]
    // -----------------------------------------------------------------------------------------

    /*******************************************************************************************
     * 에러 히스토리 조회 - 전체
     *******************************************************************************************/
    public List<MonitoringResponseDto> fetchAllExceptionHistory() {
        return convertMonitoringDto(monitoringService.fetchAllExceptionHistory());
    }

    /*******************************************************************************************
     * 에러 히스토리 조회 - 검색 ( App 이름 )
     *******************************************************************************************/
    public List<MonitoringResponseDto> fetchExceptionHistoryBySearchWithAppName(String appName) {
        return convertMonitoringDto(monitoringService.fetchExceptionHistoryBySearch(MonitoringDto.Select.builder()
                .appName(appName)
                .build()));
    }

    /*******************************************************************************************
     * 에러 히스토리 조회 - 검색 ( Level )
     *******************************************************************************************/
    public List<MonitoringResponseDto> fetchExceptionHistoryBySearchWithLevel(String level) {
        return convertMonitoringDto(monitoringService.fetchExceptionHistoryBySearch(MonitoringDto.Select.builder()
                .level(level)
                .build()));
    }

    /*******************************************************************************************
     * 에러 히스토리 조회 - 검색 ( Status )
     *******************************************************************************************/
    public List<MonitoringResponseDto> fetchExceptionHistoryBySearchWithStatus(String status) {
        return convertMonitoringDto(monitoringService.fetchExceptionHistoryBySearch(MonitoringDto.Select.builder()
                .status(status)
                .build()));
    }

    /*******************************************************************************************
     * 에러 히스토리 조회 - 검색 ( Type )
     *******************************************************************************************/
    public List<MonitoringResponseDto> fetchExceptionHistoryBySearchWithType(String type) {
        return convertMonitoringDto(monitoringService.fetchExceptionHistoryBySearch(MonitoringDto.Select.builder()
                .type(type)
                .build()));
    }

    /*******************************************************************************************
     * 에러 히스토리 조회 - 검색 ( Detail )
     *******************************************************************************************/
    public List<MonitoringResponseDto> fetchExceptionHistoryBySearchWithDetail(String detail) {
        return convertMonitoringDto(monitoringService.fetchExceptionHistoryBySearch(MonitoringDto.Select.builder()
                .detail(detail)
                .build()));
    }

    /*******************************************************************************************
     * 에러 히스토리 조회 - 검색 ( Message )
     *******************************************************************************************/
    public List<MonitoringResponseDto> fetchExceptionHistoryBySearchWithMessage(String message) {
        return convertMonitoringDto(monitoringService.fetchExceptionHistoryBySearch(MonitoringDto.Select.builder()
                .message(message)
                .build()));
    }

    /*******************************************************************************************
     * 에러 히스토리 조회 - 검색 ( Alarm Y/N )
     *******************************************************************************************/
    public List<MonitoringResponseDto> fetchExceptionHistoryBySearchWithAlarmYn(String alarmYn) {
        return convertMonitoringDto(monitoringService.fetchExceptionHistoryBySearch(MonitoringDto.Select.builder()
                .alarmYn(alarmYn)
                .build()));
    }

    /*******************************************************************************************
     * 에러 히스토리 조회 - 알람 미발송
     *******************************************************************************************/
    public List<MonitoringResponseDto> fetchAllExceptionHistoryAlarmNotSent() {
        return convertMonitoringDto(monitoringService.fetchAllExceptionHistoryAlarmNotSent());
    }

    // -----------------------------------------------------------------------------------------
    //                                      [ INSERT ]
    // -----------------------------------------------------------------------------------------

    /*******************************************************************************************
     * 에러 등록 ( App 이름 + Level )
     *******************************************************************************************/
    public void insertExceptionHistory(String appName, String level) {
        monitoringService.insertExceptionHistory(MonitoringDto.Insert.builder()
                .appName(appName)
                .level(level)
                .build()
        );
    }

    /*******************************************************************************************
     * 에러 등록 ( App 이름 + Level + Status )
     *******************************************************************************************/
    public void insertExceptionHistory(String appName, String level, String status) {
        monitoringService.insertExceptionHistory(MonitoringDto.Insert.builder()
                .appName(appName)
                .level(level)
                .level(status)
                .build()
        );
    }

    /*******************************************************************************************
     * 에러 등록 ( App 이름 + Level + Status + Type )
     *******************************************************************************************/
    public void insertExceptionHistory(String appName, String level, String status, String type) {
        monitoringService.insertExceptionHistory(MonitoringDto.Insert.builder()
                .appName(appName)
                .level(level)
                .status(status)
                .type(type)
                .build()
        );
    }

    /*******************************************************************************************
     * 에러 등록 ( App 이름 + Level + Status + Type + detail )
     *******************************************************************************************/
    public void insertExceptionHistory(String appName, String level, String status, String type, String detail) {
        monitoringService.insertExceptionHistory(MonitoringDto.Insert.builder()
                .appName(appName)
                .level(level)
                .status(status)
                .type(type)
                .detail(detail)
                .build()
        );
    }

    /*******************************************************************************************
     * 에러 등록 ( App 이름 + Level + Status + Type + detail + Message )
     *******************************************************************************************/
    public void insertExceptionHistory(String appName, String level, String status, String type, String detail, String message) {
        monitoringService.insertExceptionHistory(MonitoringDto.Insert.builder()
                .appName(appName)
                .level(level)
                .status(status)
                .type(type)
                .detail(detail)
                .message(message)
                .build()
        );
    }

    /*******************************************************************************************
     * 에러 등록 ( App 이름 + Level + Message )
     *******************************************************************************************/
    public void insertExceptionHistoryWithMessage(String appName, String level, String message) {
        monitoringService.insertExceptionHistory(MonitoringDto.Insert.builder()
                .appName(appName)
                .level(level)
                .message(message)
                .build()
        );
    }

    /*******************************************************************************************
     * 에러 등록 ( App 이름 + Level + Type + Message )
     *******************************************************************************************/
    public void insertExceptionHistoryWithTypeAndMessage(String appName, String level, String type, String message) {
        monitoringService.insertExceptionHistory(MonitoringDto.Insert.builder()
                .appName(appName)
                .level(level)
                .type(type)
                .message(message)
                .build()
        );
    }

    // -----------------------------------------------------------------------------------------
    //                                      [ UPDATE ]
    // -----------------------------------------------------------------------------------------

    /*******************************************************************************************
     * 에러 갱신 - 알람 발송 유무
     *******************************************************************************************/
    public void updateExceptionHistoryByAlarmSent(long idx) {
        monitoringService.updateExceptionHistoryByAlarmSent(idx);
    }

    // #########################################################################################
    //                                      [ PRIVATE ]
    // #########################################################################################

    /*******************************************************************************************
     * DTO 타입 변경 ( MonitoringDto.Select -> MonitoringResponseDto )
     *******************************************************************************************/
    private List<MonitoringResponseDto> convertMonitoringDto(List<MonitoringDto.Select> exceptionHistoryList) {

        List<MonitoringResponseDto> rtnList = new ArrayList<>();

        for (MonitoringDto.Select dto : exceptionHistoryList) {
            rtnList.add(MonitoringResponseDto.builder()
                    .idx(dto.getIdx())
                    .appName(dto.getAppName())
                    .level(dto.getLevel())
                    .status(dto.getStatus())
                    .type(dto.getType())
                    .detail(dto.getDetail())
                    .message(dto.getMessage())
                    .alarmYn(dto.getAlarmYn())
                    .regDate(dto.getRegDate())
                    .alarmDate(dto.getAlarmDate())
                    .build());
        }

        return rtnList;
    }

}