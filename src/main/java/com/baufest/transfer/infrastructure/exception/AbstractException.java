package com.baufest.transfer.infrastructure.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public abstract class AbstractException extends RuntimeException{
    private String errorCode;
    private String errorDescription;
}
