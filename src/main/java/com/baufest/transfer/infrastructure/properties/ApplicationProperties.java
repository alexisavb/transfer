package com.baufest.transfer.infrastructure.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
public class ApplicationProperties {
    @Value("${custom.transfer.validations.duplication-time-interval}")
    private int duplicationTimeInterval;
    @Value(value = "${custom.transfer.producer.transfer.topic-name}")
    private String transferTopic;
}
