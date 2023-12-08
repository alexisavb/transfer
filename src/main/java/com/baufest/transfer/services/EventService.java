package com.baufest.transfer.services;

import com.baufest.transfer.provider.event.dto.NewTransferEvent;

public interface EventService {
    void publishNewTransfer(NewTransferEvent newTransferEvent);
}
