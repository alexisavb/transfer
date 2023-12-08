package com.baufest.transfer.application.dto.response;

import com.baufest.transfer.application.dto.enums.Currency;
import com.baufest.transfer.application.dto.enums.TransferStatus;
import com.baufest.transfer.application.dto.enums.TransferType;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransferResponseDTO {
    @JsonProperty("transfer_id")
    private Long transferId;
    @JsonProperty("source_account_id")
    private Long sourceAccountId;
    @JsonProperty("target_account_id")
    private Long targetAccountId;
    @JsonProperty("source_user_id")
    private String sourceUserId;
    @JsonProperty("target_user_id")
    private String targetUserId;
    private Double amount;
    private Currency currency;
    @JsonProperty("transfer_type")
    private TransferType transferType;
    private TransferStatus status;
    private String datetime;
}
