package com.expressbank.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
public class ResponseDTO {
    private Integer code;
    private String desc;
    private HttpStatus httpStatus;
    private Object obj;

    public static ResponseDTO of(Integer code, HttpStatus httpStatus, String desc){
      return ResponseDTO.builder()
               .code(code)
               .httpStatus(httpStatus)
               .desc(desc).build();
    }

    public static ResponseDTO of(Integer code, HttpStatus httpStatus, String desc, Object obj){
        return ResponseDTO.builder()
                .code(code)
                .httpStatus(httpStatus)
                .desc(desc)
                .obj(obj).build();
    }
}
