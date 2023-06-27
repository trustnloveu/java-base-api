package kr.co.ejyang.main_api.model;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class ApiResponse implements Serializable {

    @NotNull
    private final Integer httpStatus;

    @NotNull
    private final String code;

    @Nullable
    private final String message;

    @Nullable
    private Object data;

    /*******************************************************************************************
     * 생성자 (1)
     * @param message       : 메세지
     * @param code          : 응답 코드 ( 로직 )
     * @param httpStatus    : 응답 코드 ( HTTP )
     *******************************************************************************************/
    public ApiResponse(String message, String code, Integer httpStatus) {
        this.message = message;
        this.code = code;
        this.httpStatus = httpStatus;
    }

    /*******************************************************************************************
     * 생성자 (2)
     * @param message       : 메세지
     * @param code          : 응답 코드 ( 로직 )
     * @param httpStatus    : 응답 코드 ( HTTP )
     * @param data          : 반환 데이터
     *******************************************************************************************/
    public ApiResponse(String message, String code, Integer httpStatus, Object data) {
        this.message = message;
        this.code = code;
        this.httpStatus = httpStatus;
        this.data = data;
    }

    /*******************************************************************************************
     * 생성자 (3)
     * @param response      : 응답 코드 ( 로직, HTTP ), 반환 데이터
     *******************************************************************************************/
    public ApiResponse(ResponseCode response) {
        this.message = response.getMsg();
        this.code = response.getCode();
        this.httpStatus = response.getHttpStatus();
    }

    /*******************************************************************************************
     * 생성자 (4)
     * @param response      : 응답 코드 ( 로직, HTTP ), 반환 데이터
     * @param data          : 반환 데이터
     *******************************************************************************************/
    public ApiResponse(ResponseCode response, Object data) {
        this.message = response.getMsg();
        this.code = response.getCode();
        this.httpStatus = response.getHttpStatus();
        this.data = data;
    }

    /*******************************************************************************************
     * 생성자 (5)
     * @param message       : 사용자 지정 메세지
     * @param response      : 응답 코드 ( 로직, HTTP ), 반환 데이터
     *******************************************************************************************/
    public ApiResponse(String message, ResponseCode response) {
        this.message = message;
        this.code = response.getCode();
        this.httpStatus = response.getHttpStatus();
    }

    /*******************************************************************************************
     * 생성자 (6)
     * @param message       : 사용자 지정 메세지
     * @param response      : 응답 코드 ( 로직, HTTP ), 반환 데이터
     * @param data          : 반환 데이터
     *******************************************************************************************/
    public ApiResponse(String message, ResponseCode response, Object data) {
        this.message = message;
        this.code = response.getCode();
        this.httpStatus = response.getHttpStatus();
        this.data = data;
    }


//    public boolean isSuccessReturn(){
//        if(200 <= this.getHttpstatus() && this.getHttpstatus() < 230) return true;
//        else return false;
//    }

//    public static ResponseEntity<?> returnByApiResponse(ApiResponse apiResponse){
//        return ResponseEntity.status(apiResponse.getHttpstatus()).body(apiResponse);
//    }

}
