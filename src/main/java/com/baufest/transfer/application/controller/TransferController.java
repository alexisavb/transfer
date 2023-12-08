package com.baufest.transfer.application.controller;

import com.baufest.transfer.application.dto.request.CreateTransferRequestDTO;
import com.baufest.transfer.application.dto.response.TransferResponseDTO;
import com.baufest.transfer.application.facade.TransferFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/v1/transfers")
@RequiredArgsConstructor
public class TransferController {
    private final TransferFacade transferFacade;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public TransferResponseDTO create(@Valid @RequestBody CreateTransferRequestDTO createTransferRequest,
                                      @RequestAttribute(value = "user_id", required = false) String userId){
        log.info("[TransferController:create] request: {}",createTransferRequest);
        return transferFacade.create(createTransferRequest,userId);
    }
}
