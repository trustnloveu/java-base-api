package kr.co.ejyang.main_api.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class ApiResponse<T> implements Serializable {

    private static final ObjectMapper mapper = new ObjectMapper();

    @NotNull
    private String message;
    @NotNull
    private String code;
    @NotNull
    private Integer httpstatus;

    private T data;

    public ApiResponse(String message, String code, Integer httpstatus) {
        this.message = message;
        this.code = code;
        this.httpstatus = httpstatus;
    }

//    public ApiResponse(String message, String code, Integer httpstatus, T data) {
//        this.message = message;
//        this.code = code;
//        this.httpstatus = httpstatus;
//        this.data = data;
//    }

//    public boolean isSuccessReturn(){
//        if(200 <= this.getHttpstatus() && this.getHttpstatus() < 230) return true;
//        else return false;
//    }

//    public static ResponseEntity<?> returnByApiResponse(ApiResponse apiResponse){
//        return ResponseEntity.status(apiResponse.getHttpstatus()).body(apiResponse);
//    }

}
