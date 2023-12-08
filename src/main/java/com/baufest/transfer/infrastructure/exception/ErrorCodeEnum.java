package com.baufest.transfer.infrastructure.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.Objects;

@AllArgsConstructor
@Getter
public enum ErrorCodeEnum {
    PARAMETER_NOT_VALID("TR-001","Parameter not valid", HttpStatus.BAD_REQUEST),
    JWT_FORBIDDEN("TR-002","JWT not authorized for requested resource",HttpStatus.FORBIDDEN),
    NOT_FOUND("TR-003","Resource not found",HttpStatus.NOT_FOUND),
    DUPLICATED_TRANSFER("TR-004","Duplicate transfer within default minutes not allowed",HttpStatus.BAD_REQUEST),
    SELF_TRANSFER("TR-005","Self transfer is not allowed",HttpStatus.BAD_REQUEST);
    private String errorCode;
    private String defaultErrorMessage;
    private HttpStatus httpStatus;

    public static HttpStatus getHttpStatusByCode(String errorCode){
        return Arrays.stream(ErrorCodeEnum.values())
                .filter(s -> Objects.equals(s.errorCode, errorCode))
                .findFirst()
                .map(x -> x.httpStatus)
                .orElse(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
