package com.baufest.transfer.application.mapper.event;

import com.baufest.transfer.persistence.entity.TransferJpaEntity;
import com.baufest.transfer.provider.event.dto.NewTransferEvent;

public interface EventMapper {
    NewTransferEvent toNewTransferEvent(TransferJpaEntity entity);
}
