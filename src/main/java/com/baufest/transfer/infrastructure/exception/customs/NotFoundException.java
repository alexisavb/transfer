package com.baufest.transfer.infrastructure.exception.customs;

import com.baufest.transfer.infrastructure.exception.AbstractException;
import com.baufest.transfer.infrastructure.exception.ErrorCodeEnum;

public class NotFoundException extends AbstractException {
    public NotFoundException() {
        super(ErrorCodeEnum.NOT_FOUND.getErrorCode(), ErrorCodeEnum.NOT_FOUND.getDefaultErrorMessage());
    }
    public NotFoundException(String errorMessage) {
        super(ErrorCodeEnum.NOT_FOUND.getErrorCode(), errorMessage);
    }

}
