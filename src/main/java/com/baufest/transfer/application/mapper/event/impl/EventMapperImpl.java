package com.baufest.transfer.application.mapper.event.impl;

import com.baufest.transfer.application.dto.enums.Currency;
import com.baufest.transfer.application.dto.enums.TransferStatus;
import com.baufest.transfer.application.mapper.event.EventMapper;
import com.baufest.transfer.persistence.entity.TransferJpaEntity;
import com.baufest.transfer.provider.event.dto.NewTransferEvent;
import org.springframework.stereotype.Component;

@Component
public class EventMapperImpl implements EventMapper {
    @Override
    public NewTransferEvent toNewTransferEvent(TransferJpaEntity entity) {
        return NewTransferEvent.builder()
                .transferId(entity.getId())
                .sourceAccountId(entity.getSourceAccountId())
                .sourceUserId(entity.getSourceUserId())
                .amount(entity.getAmount())
                .currency(Currency.from(entity.getCurrency()).name())
                .targetAccountId(entity.getTargetAccountId())
                .targetUserId(entity.getTargetUserId())
                .comments("P2P")
                .datetime(entity.getCreated().toString())
                .status(TransferStatus.from(entity.getStatus()).name())
                .build();
    }
}
