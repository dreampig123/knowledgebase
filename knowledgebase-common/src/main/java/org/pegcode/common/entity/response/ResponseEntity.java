package org.pegcode.common.entity.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.pegcode.common.enums.ExceptionEnum;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseEntity<T> implements Serializable {

    private String code;
    private String msg;
    private T body;

    public static ResponseEntity success() {
        ResponseEntity result = new ResponseEntity<>();
        result.setCode(ExceptionEnum.SUCCESS.code);
        result.setMsg(ExceptionEnum.SUCCESS.msg);
        return result;
    }

    public static ResponseEntity fail() {
        ResponseEntity result = new ResponseEntity<>();
        result.setCode(ExceptionEnum.FAIL.code);
        result.setMsg(ExceptionEnum.FAIL.msg);
        return result;
    }

    public static <T> ResponseEntity<T> success(T data) {
        ResponseEntity<T> result = new ResponseEntity<>();
        result.setCode(ExceptionEnum.SUCCESS.code);
        result.setMsg(ExceptionEnum.SUCCESS.msg);
        result.setBody(data);
        return result;
    }

    public static ResponseEntity defFail(String code, String msg) {
        ResponseEntity result = new ResponseEntity();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }
}
