package com.baufest.transfer.services;

import com.baufest.transfer.persistence.entity.TransferJpaEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface TransferService {
    List<TransferJpaEntity> findRepeatedTransferLastMinutes(Long sourceAccountId, String sourceUserId, Long targetAccountId, String targetUserId, double amount, LocalDateTime date, int transferType);
    TransferJpaEntity create(TransferJpaEntity transfer);
}
