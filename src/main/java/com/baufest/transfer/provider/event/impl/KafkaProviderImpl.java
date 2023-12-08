package com.baufest.transfer.provider.event.impl;

import com.baufest.transfer.infrastructure.properties.ApplicationProperties;
import com.baufest.transfer.infrastructure.util.Constants;
import com.baufest.transfer.provider.event.EventProvider;
import com.baufest.transfer.provider.event.dto.NewTransferEvent;
import com.baufest.transfer.provider.event.dto.enums.KafkaProducedEvents;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import io.cloudevents.CloudEvent;
import io.cloudevents.core.builder.CloudEventBuilder;
import io.cloudevents.core.data.PojoCloudEventData;
import java.time.Instant;
import java.time.ZoneOffset;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaProviderImpl implements EventProvider {

    private final KafkaTemplate<String, CloudEvent> kafkaTemplate;
    private final ApplicationProperties properties;
    private final ObjectMapper objectMapper;
    @Override
    public void publishNewTransfer(NewTransferEvent event) {
        log.info("[KafkaProviderImpl:publishNewTransfer] topic: {}, NewTransferEvent: {}",properties.getTransferTopic(), event);
        this.publishEventTopic(event, KafkaProducedEvents.EVENT_TYPE_NEW_TRANSFER,properties.getTransferTopic());
    }

    private void publishEventTopic(Object event, KafkaProducedEvents kafkaProducedEvents, String topic){
        CloudEvent cloudEvent = CloudEventBuilder.v1()
                .withExtension("payloadversion", kafkaProducedEvents.getPayloadVersion())
                .withId(UUID.randomUUID().toString())
                .withType(kafkaProducedEvents.getEventType())
                .withSource(Constants.URI_SOURCE_TRANSFER_MS)
                .withDataContentType(Constants.JSON_CONTENT_TYPE)
                .withTime(Instant.now().atOffset(ZoneOffset.UTC))
                .withData(PojoCloudEventData.wrap(event, objectMapper::writeValueAsBytes))
                .build();
        kafkaTemplate.send(topic, cloudEvent);
        log.info("[KafkaProviderImpl:publishEventTopic] message published!!!");
    }
}
