package com.baufest.transfer.infrastructure.exception;

import com.baufest.transfer.infrastructure.exception.customs.ForbiddenException;
import com.baufest.transfer.infrastructure.util.Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.lang.NonNull;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(AbstractException.class)
    protected ResponseEntity<ErrorDetail> abstractException(final AbstractException ex) {
        log.error("$$$$$$$$$$$$$$$$-ERROR-$$$$$$$$$$$$$$$$");
        ErrorDetail errorDetail = ErrorDetail.builder()
                .errorCode(ex.getErrorCode())
                .errorMessage(ex.getErrorDescription())
                .timestamp(LocalDateTime.now(ZoneId.of("UTC")).toString())
                .build();
        log.error("ErrorDetail: {}", errorDetail);
        return new ResponseEntity<>(errorDetail, ErrorCodeEnum.getHttpStatusByCode(ex.getErrorCode()));
    }

    @NonNull
    @Override
    protected ResponseEntity<Object> handleBindException(BindException ex,HttpHeaders headers,
                                                         HttpStatus status, WebRequest request) {
        Map<String, Set<String>> errorsMap = manageValidationError(ex.getBindingResult().getFieldErrors());
        return new ResponseEntity<>(errorsMap.isEmpty() ? ex : errorsMap, headers, status);
    }

    @NonNull
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {
        final BindingResult bindingResult = ex.getBindingResult();
        List<ErrorDetail> errorList = new ArrayList<>(1);
        if (bindingResult.hasErrors()) {
            final List<FieldError> fieldErrorList = bindingResult.getFieldErrors();
            for (FieldError fieldError : fieldErrorList) {
                String field = Utils.parseCamelCaseToSnakeCase(fieldError.getField());
                errorList.add(ErrorDetail.builder().errorMessage(field + ": " + fieldError.getDefaultMessage())
                        .errorCode(ErrorCodeEnum.PARAMETER_NOT_VALID.getErrorCode())
                        .timestamp(LocalDateTime.now(ZoneId.of("UTC")).toString()).build());
            }
        }
        return new ResponseEntity<>(errorList, headers, status);
    }

    @NotNull
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {
        ErrorDetail error = ErrorDetail.builder().errorCode(ErrorCodeEnum.PARAMETER_NOT_VALID.getErrorCode())
                .errorMessage(ex.getMessage())
                .timestamp(LocalDateTime.now(ZoneId.of("UTC")).toString())
                .build();
        return new ResponseEntity<>(error, new HttpHeaders(), ErrorCodeEnum.PARAMETER_NOT_VALID.getHttpStatus());
    }

    private Map<String, Set<String>> manageValidationError(List<FieldError> fieldErrors) {
        return fieldErrors.stream().collect(
                Collectors.groupingBy(FieldError::getField,
                        Collectors.mapping(FieldError::getDefaultMessage, Collectors.toSet())));
    }
}
