package com.baufest.transfer.infrastructure.exception.customs;

import com.baufest.transfer.infrastructure.exception.AbstractException;
import com.baufest.transfer.infrastructure.exception.ErrorCodeEnum;

public class ForbiddenException extends AbstractException {
    public ForbiddenException() {
        super(ErrorCodeEnum.JWT_FORBIDDEN.getErrorCode(), ErrorCodeEnum.JWT_FORBIDDEN.getDefaultErrorMessage());
    }
    public ForbiddenException(String errorMessage) {
        super(ErrorCodeEnum.JWT_FORBIDDEN.getErrorCode(), errorMessage);
    }

}