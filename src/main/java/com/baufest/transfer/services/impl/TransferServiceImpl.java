package com.baufest.transfer.services.impl;

import com.baufest.transfer.application.dto.enums.TransferStatus;
import com.baufest.transfer.infrastructure.exception.customs.BadRequestException;
import com.baufest.transfer.persistence.entity.TransferJpaEntity;
import com.baufest.transfer.persistence.repository.transfers.TransferRepository;
import com.baufest.transfer.services.TransferService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransferServiceImpl implements TransferService {

    private final TransferRepository transferRepository;

    @Override
    public List<TransferJpaEntity> findRepeatedTransferLastMinutes(Long sourceAccountId, String sourceUserId, Long targetAccountId, String targetUserId, double amount, LocalDateTime date, int transferType) {
        return transferRepository.
                findBySourceAccountIdAndSourceUserIdAndTargetAccountIdAndTargetUserIdAndAmountAndCreatedAfterAndTransferType(
                        sourceAccountId, sourceUserId, targetAccountId, targetUserId, amount, date, transferType);
    }

    @Override
    public TransferJpaEntity create(TransferJpaEntity transfer) {
        transfer.setStatus(TransferStatus.PENDING.getCode());
        try {
            return transferRepository.save(transfer);
        } catch (DataIntegrityViolationException ex) {
            throw new BadRequestException("Duplicate transfer detected");
        }
    }
}
