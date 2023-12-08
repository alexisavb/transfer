package com.baufest.transfer.provider.event.dto.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum KafkaProducedEvents {
    EVENT_TYPE_NEW_TRANSFER("com.event.p2p.transfer.new","0.0.1");
    private final String eventType;
    private final String payloadVersion;
}
