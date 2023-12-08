package com.baufest.transfer.infrastructure.exception.customs;

import com.baufest.transfer.infrastructure.exception.AbstractException;
import com.baufest.transfer.infrastructure.exception.ErrorCodeEnum;

public class BadRequestException extends AbstractException {
    public BadRequestException() {
        super(ErrorCodeEnum.DUPLICATED_TRANSFER.getErrorCode(), ErrorCodeEnum.DUPLICATED_TRANSFER.getDefaultErrorMessage());
    }
    public BadRequestException(String errorMessage) {
        super(ErrorCodeEnum.DUPLICATED_TRANSFER.getErrorCode(), errorMessage);
    }
}
