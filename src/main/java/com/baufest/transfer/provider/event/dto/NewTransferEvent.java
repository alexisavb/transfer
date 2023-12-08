package com.baufest.transfer.provider.event.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NewTransferEvent {
    private long transferId;
    private long sourceAccountId;
    private String sourceUserId;
    private double amount;
    private String currency;
    private long targetAccountId;
    private String targetUserId;
    private String comments;
    private String datetime;
    private String status;
}
