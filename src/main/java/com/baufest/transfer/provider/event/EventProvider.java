package com.baufest.transfer.provider.event;

import com.baufest.transfer.provider.event.dto.NewTransferEvent;

public interface EventProvider {
    void publishNewTransfer(NewTransferEvent newTransferEvent);
}
