package kr.co.ejyang.main_api.model;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

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

    // #########################################################################################
    //                                      [ CONSTRUCTOR ]
    // #########################################################################################

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
     * @param responseCode  : 응답 코드 ( 로직, HTTP ), 반환 데이터
     *******************************************************************************************/
    public ApiResponse(String message, ResponseCode responseCode) {
        this.message = message;
        this.code = responseCode.getCode();
        this.httpStatus = responseCode.getHttpStatus();
    }

    /*******************************************************************************************
     * 생성자 (6)
     * @param message       : 사용자 지정 메세지
     * @param responseCode  : 응답 코드 ( 로직, HTTP ), 반환 데이터
     * @param data          : 반환 데이터
     *******************************************************************************************/
    public ApiResponse(String message, ResponseCode responseCode, Object data) {
        this.message = message;
        this.code = responseCode.getCode();
        this.httpStatus = responseCode.getHttpStatus();
        this.data = data;
    }

    // #########################################################################################
    //                                      [ METHOD ]
    // #########################################################################################

    /*******************************************************************************************
     * 메서드 (1)  - HTTP Response 반환
     * @param apiResponse   : HttpStatus, Code, Message
     *******************************************************************************************/
    public static ResponseEntity<ApiResponse> returnByApiResponse(ApiResponse apiResponse) {
        return ResponseEntity.status(apiResponse.getHttpStatus()).body(apiResponse);
    }

    /*******************************************************************************************
     * 메서드 (2)  - HTTP Response 반환
     * @param headers       : HttpHeaders
     * @param apiResponse   : HttpStatus, Code, Message
     *******************************************************************************************/
    public static ResponseEntity<ApiResponse> returnByApiResponse(HttpHeaders headers, ApiResponse apiResponse) {
        return ResponseEntity.status(apiResponse.getHttpStatus()).headers(headers).body(apiResponse);
    }

    /*******************************************************************************************
     * 메서드 (3)  - 성공 유무 확인
     *******************************************************************************************/
    public boolean isSuccessReturn() {
        return 200 <= this.getHttpStatus() && this.getHttpStatus() < 230;
    }

}
