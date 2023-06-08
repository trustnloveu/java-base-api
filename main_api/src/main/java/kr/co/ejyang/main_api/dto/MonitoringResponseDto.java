package kr.co.ejyang.main_api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MonitoringResponseDto {
    public long idx;
    public String appName;
    public String level;
    public String status;
    public String type;
    public String detail;
    public String message;
    public String alarmYn;
    public Date regDate;
    public Date alarmDate;
}
