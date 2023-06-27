package kr.co.ejyang.main_api.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

/*******************************************************************************************
 *  [ ResponseCode ]
 *
 *      HttpStatus  = 응답 코드 ( HTTP )
 *      Code        = 응답 코드 ( 로직 )
 *      Message     = 메세지
 *
 *  [ HTTP STATUS CODE ]
 *
 *      200 = 정상
 *      400 = 잘못된 요청 - 공통
 *      401 = 유효성 요청 - JWT, Request Header
 *      403 = 권한
 *      404 = Not Found
 *      409 = 내부 검증 로직 ( if/else )
 *      500 = 통신
 *******************************************************************************************/
@Getter
@RequiredArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ResponseCode {

    /*******************************************************************************************
     * 응답 코드 상수
     *******************************************************************************************/
    CODE_0000(200, "0000", "success"),
    CODE_0400(400, "0400", "fail"),
    CODE_0403(403, "0403", "fail"),
    CODE_0404(404, "0404", "fail"),
    CODE_0500(500, "0500", "fail");

    /*******************************************************************************************
     * 인자값
     *******************************************************************************************/
    private final Integer httpStatus;
    private final String code;
    private final String msg;

    /*******************************************************************************************
     * 메서드 (1)  : 코드 조회
     *******************************************************************************************/
    public static ResponseCode getResponseCodeByCode(String code) {
        return Arrays.stream(ResponseCode.values()).filter(x -> x.getCode().equals(code)).findFirst().orElseThrow(RuntimeException::new);
    }
}
