package org.pegcode.common.entity.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseEntity<T> implements Serializable {

    private static final String SUCCESS_CODE = "200";
    private static final String SUCCESS_MSG = "成功";
    private static final String FAIL_CODE = "9999";
    private static final String FAIL_MSG = "失败";

    private String code;
    private String msg;
    private T body;

    public static ResponseEntity success() {
        ResponseEntity result = new ResponseEntity<>();
        result.setCode(SUCCESS_CODE);
        result.setMsg(SUCCESS_MSG);
        return result;
    }

    public static ResponseEntity fail() {
        ResponseEntity result = new ResponseEntity<>();
        result.setCode(FAIL_CODE);
        result.setMsg(FAIL_MSG);
        return result;
    }

    public static <T> ResponseEntity<T> success(T data) {
        ResponseEntity<T> result = new ResponseEntity<>();
        result.setCode(SUCCESS_CODE);
        result.setMsg(SUCCESS_MSG);
        result.setBody(data);
        return result;
    }

    public static ResponseEntity error(String code, String msg) {
        ResponseEntity result = new ResponseEntity();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

}
