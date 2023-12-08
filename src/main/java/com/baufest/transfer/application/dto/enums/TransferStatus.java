package com.baufest.transfer.application.dto.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum TransferStatus {
    PENDING(0),
    COMPLETED(1),
    FAILED(2);

    private final int code;

    public static TransferStatus from(int code) {
        return Arrays.stream(TransferStatus.values())
                .filter(s -> s.code == code)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(String.format("Unsupported type %s.", code)));
    }
}
