package com.baufest.transfer.application.dto.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum TransferType {
    P2P(0,TransferMovementType.DEBIT);
    private final int code;
    private final TransferMovementType movementTypeOnSourceAccount;

    public static TransferType from(int code) {
        return Arrays.stream(TransferType.values())
                .filter(s -> s.code == code)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(String.format("Unsupported type %s.", code)));
    }
}
