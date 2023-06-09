package kr.co.ejyang.main_api.exception;

import com.fasterxml.jackson.core.JsonParseException;
import kr.co.ejyang.main_api.model.ApiResponse;
import kr.co.ejyang.module_file.exception.FileModuleException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestControllerAdvice
class ControllerExceptionHandler {

    private static final Logger log = LogManager.getLogger(ControllerExceptionHandler.class);

    // 글로벌 에러
    @ExceptionHandler( value = Exception.class)
    public ResponseEntity<?> globalException(Exception e) {
        log.error(ExceptionUtils.getStackTrace(e));
        return ResponseEntity.status(500).body(new ApiResponse<>("서버 에러","500",500));
    }

    // 실행 에러
    @ExceptionHandler( value = RuntimeException.class)
    public ResponseEntity<?> runTimeException(RuntimeException e) {
        log.error(ExceptionUtils.getStackTrace(e));
        return ResponseEntity.status(500).body(new ApiResponse<>("런타임 에러","500",500));
    }

    // 파일 에러
    @ExceptionHandler( value = FileModuleException.class)
    public ResponseEntity<?> FileModuleException(FileModuleException e) {
        log.error(ExceptionUtils.getStackTrace(e));
        return ResponseEntity.status(500).body(new ApiResponse<>(e.getMessage(),"400",400));
    }

    // 파일 에러
    @ExceptionHandler( value = FileNotFoundException.class)
    public ResponseEntity<?> FileNotFoundException(FileNotFoundException e) {
        log.error(ExceptionUtils.getStackTrace(e));
        return ResponseEntity.status(500).body(new ApiResponse<>(e.getMessage(),"400",400));
    }

    // DTO 객체 검증 에러
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<?> methodArgumentNotValidException(MethodArgumentNotValidException e){
        log.error(ExceptionUtils.getStackTrace(e));

        BindingResult bindingResult = e.getBindingResult();
        List<String> messageList = new ArrayList<>();

        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            messageList.add("[ " + fieldError.getField() + " ::: " + fieldError.getDefaultMessage() + " ]");
        }

        return ResponseEntity.status(500).body(new ApiResponse<>(String.join(", ", messageList),"400",400));
    }

    // RequestPart == Null 에러
    @ExceptionHandler( value = MissingServletRequestPartException.class)
    public ResponseEntity<?> httpRequestMethodNotSupportedException(MissingServletRequestPartException e) {
        log.error(ExceptionUtils.getStackTrace(e));
        return ResponseEntity.status(400).body(new ApiResponse<>(e.getMessage(),"400",400));
    }

    // HTTP Method 에러
    @ExceptionHandler( value = HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<?> httpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        log.error(ExceptionUtils.getStackTrace(e));
        return ResponseEntity.status(403).body(new ApiResponse<>("forbidden","403",403));
    }

    // Request 파라미터 에러
    @ExceptionHandler( value = HttpMessageConversionException.class)
    public ResponseEntity<?> HttpMessageConversionException(HttpMessageConversionException e) {
        log.error(ExceptionUtils.getStackTrace(e));
        return ResponseEntity.status(400).body(new ApiResponse<>("잘못된 요청입니다.","400",400));
    }

    // Java 라이브러리러 Build 에러
    @ExceptionHandler( value = ExecutionException.class)
    public ResponseEntity<?> ExecutionException(ExecutionException e) {
        log.error(ExceptionUtils.getStackTrace(e));
        return ResponseEntity.status(500).body(new ApiResponse<>("빌드 에러", "500", 500));
    }

    // JSON 파싱 에러
    @ExceptionHandler( value = JsonParseException.class)
    public ResponseEntity<?> JsonParseException(JsonParseException e) {
        log.error(ExceptionUtils.getStackTrace(e));
        return ResponseEntity.status(500).body(new ApiResponse<>("JSON 파싱 에러", "500", 500));
    }

    // List Index 조회 에러
    @ExceptionHandler( value = ArrayIndexOutOfBoundsException.class)
    public ResponseEntity<?> ArrayIndexOutOfBoundsException(ArrayIndexOutOfBoundsException e) {
        log.error(ExceptionUtils.getStackTrace(e));
        return ResponseEntity.status(500).body(new ApiResponse<>(e.getMessage(), "400", 400));
    }

}