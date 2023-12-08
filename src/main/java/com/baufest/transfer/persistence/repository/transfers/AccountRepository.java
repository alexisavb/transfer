package com.baufest.transfer.persistence.repository.transfers;

import com.baufest.transfer.persistence.entity.AccountJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<AccountJpaEntity, Long> {
    AccountJpaEntity findByAccountIdAndUserId(Long accountId, String userId);
}
