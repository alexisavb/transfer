package com.baufest.transfer.application.dto.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum Currency {
    MXN(0),
    USD(1);

    private final int code;

    public static Currency from(int code) {
        return Arrays.stream(Currency.values())
                .filter(s -> s.code == code)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(String.format("Unsupported type %s.", code)));
    }
}
