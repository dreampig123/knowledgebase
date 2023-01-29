package org.pegcode.common.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.pegcode.common.enums.ExceptionEnum;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BusinessException extends RuntimeException {

    /**
     * 错误码
     */
    private String code;

    /**
     * 错误信息
     */
    private String message;

    public BusinessException(String message){
        this.code = ExceptionEnum.BUSINESS_ERROR.code;
        this.message = message;
    }
}
