package com.baufest.transfer.persistence.repository.transfers;

import com.baufest.transfer.persistence.entity.TransferJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransferRepository extends JpaRepository<TransferJpaEntity, Long> {
    List<TransferJpaEntity> findBySourceAccountIdAndSourceUserIdAndTargetAccountIdAndTargetUserIdAndAmountAndCreatedAfterAndTransferType(
            Long sourceAccountId, String sourceUserId, Long targetAccountId, String targetUserId,
            double amount, LocalDateTime minutesBefore, int transferType);
}
