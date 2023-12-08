package com.baufest.transfer.services;

public interface AccountService {
    void checkIfAccountExistsByOperationType(Long sourceAccountId, String sourceUserId, Long targetAccountId, String targetUserId);
}
