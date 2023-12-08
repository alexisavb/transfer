package com.baufest.transfer.application.dto.request;

import com.baufest.transfer.application.dto.enums.Currency;
import com.baufest.transfer.application.dto.enums.TransferType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateTransferRequestDTO {
    @JsonProperty("source_account_id")
    @NotNull
    private Long sourceAccountId;
    @JsonProperty("target_account_id")
    @NotNull
    private Long targetAccountId;
    @JsonProperty("source_user_id")
    @NotNull
    @NotBlank
    private String sourceUserId;
    @JsonProperty("target_user_id")
    @NotNull
    @NotBlank
    private String targetUserId;
    @NotNull
    private double amount;
    @NotNull
    private Currency currency;
    @NotNull
    @JsonProperty("transfer_type")
    private TransferType transferType;
}
