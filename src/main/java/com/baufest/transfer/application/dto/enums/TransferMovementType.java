package com.baufest.transfer.application.dto.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum TransferMovementType {
    CREDIT(0),
    DEBIT(1);

    private final int code;

    public static TransferMovementType from(int code) {
        return Arrays.stream(TransferMovementType.values())
                .filter(s -> s.code == code)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(String.format("Unsupported type %s.", code)));
    }
}
