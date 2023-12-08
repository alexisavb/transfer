package com.baufest.transfer.services.impl;

import com.baufest.transfer.provider.event.EventProvider;
import com.baufest.transfer.provider.event.dto.NewTransferEvent;
import com.baufest.transfer.services.EventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventProvider eventProvider;
    @Override
    public void publishNewTransfer(NewTransferEvent newTransferEvent) {
        log.debug("[EventServiceImpl:publish] NewTransferEvent {} ", newTransferEvent.toString());
        eventProvider.publishNewTransfer(newTransferEvent);
    }
}
