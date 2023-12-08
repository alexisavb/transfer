package com.baufest.transfer.services.impl;

import com.baufest.transfer.infrastructure.exception.customs.NotFoundException;
import com.baufest.transfer.infrastructure.util.Constants;
import com.baufest.transfer.persistence.entity.AccountJpaEntity;
import com.baufest.transfer.persistence.repository.transfers.AccountRepository;
import com.baufest.transfer.services.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    @Override
    public void checkIfAccountExistsByOperationType(Long sourceAccountId, String sourceUserId, Long targetAccountId, String targetUserId) {
        verifyAccount(sourceAccountId,sourceUserId, Constants.SOURCE);
        verifyAccount(targetAccountId,targetUserId, Constants.TARGET);
    }

    private void verifyAccount(Long accountId, String userId, String scope){
        AccountJpaEntity account = accountRepository.findByAccountIdAndUserId(accountId,userId);
        if (Objects.isNull(account)) {
            throw new NotFoundException(scope + " account does not exist. Please verify if you are sending the right user_id.");
        }
    }
}
