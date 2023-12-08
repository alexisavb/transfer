package com.baufest.transfer.application.facade;

import com.baufest.transfer.application.dto.enums.TransferType;
import com.baufest.transfer.application.dto.request.CreateTransferRequestDTO;
import com.baufest.transfer.application.dto.response.TransferResponseDTO;
import com.baufest.transfer.application.mapper.dto.TransferMapper;
import com.baufest.transfer.application.mapper.event.EventMapper;
import com.baufest.transfer.infrastructure.exception.customs.BadRequestException;
import com.baufest.transfer.infrastructure.exception.customs.ForbiddenException;
import com.baufest.transfer.infrastructure.properties.ApplicationProperties;
import com.baufest.transfer.persistence.entity.TransferJpaEntity;
import com.baufest.transfer.services.AccountService;
import com.baufest.transfer.services.EventService;
import com.baufest.transfer.services.TransferService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class TransferFacade {

    private final AccountService accountService;
    private final TransferService transferService;
    private final ApplicationProperties properties;
    private final TransferMapper transferMapper;
    private final EventService eventService;
    private final EventMapper eventMapper;

    public TransferResponseDTO create(CreateTransferRequestDTO request, String token){
        this.executeValidationsBeforeSaveTransfer(request,token);
        TransferJpaEntity transferJpa = this.createTransfer(request);
        this.publishEvent(transferJpa);
        return transferMapper.toDTO(transferJpa);
    }

    private void executeValidationsBeforeSaveTransfer(CreateTransferRequestDTO request, String token){
        this.validateAuthorization(request,token);
        this.ValidateExistenceOfTheAccounts(request);
        this.validateDuplicateTransfer(request);
        this.ValidateSelfTransfer(request);
    }

    private void validateAuthorization(CreateTransferRequestDTO request, String token){
        String errorMessageNotValidUser = "Not a valid user id.";
        if(request.getTransferType().getCode() == TransferType.P2P.getCode()){
            if(!token.equals(request.getSourceUserId()))
                throw new ForbiddenException(errorMessageNotValidUser);
        }
    }

    private void ValidateExistenceOfTheAccounts(CreateTransferRequestDTO request){
        accountService.checkIfAccountExistsByOperationType(request.getSourceAccountId(),request.getSourceUserId(),
                request.getTargetAccountId(),request.getTargetUserId());
    }

    private void validateDuplicateTransfer(CreateTransferRequestDTO request){
        LocalDateTime duplicationTimeInterval =
                LocalDateTime.now(ZoneId.of("UTC")).minus(Duration.ofMillis(properties.getDuplicationTimeInterval()));
        List<TransferJpaEntity> repeatedTransferList =
                transferService.findRepeatedTransferLastMinutes(request.getSourceAccountId(), request.getSourceUserId(), request.getTargetAccountId(), request.getTargetUserId(), request.getAmount(), duplicationTimeInterval,request.getTransferType().getCode());
        if (!repeatedTransferList.isEmpty())
            throw new BadRequestException("We detected a duplicated transfer in the last " + (properties.getDuplicationTimeInterval()/1000) + " second(s). Please try again later");
    }

    private void ValidateSelfTransfer(CreateTransferRequestDTO request){
        if (request.getSourceAccountId().equals(request.getTargetAccountId()) && request.getSourceUserId().equals(request.getTargetUserId()))
            throw new BadRequestException("You cannot make a transaction to your own account");
    }

    private TransferJpaEntity createTransfer(CreateTransferRequestDTO requestDTO){
        return transferService.create(transferMapper.toEntity(requestDTO));
    }

    private void publishEvent(TransferJpaEntity transfer) {
        switch(TransferType.from(transfer.getTransferType())) {
            case P2P:
                eventService.publishNewTransfer(eventMapper.toNewTransferEvent(transfer));
                break;
            default:
                log.info("[TransferFacade:publishEvent] nothing to publish");
                break;
        }
    }
}
