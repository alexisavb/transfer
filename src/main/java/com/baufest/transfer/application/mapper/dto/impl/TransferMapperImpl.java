package com.baufest.transfer.application.mapper.dto.impl;

import com.baufest.transfer.application.dto.enums.Currency;
import com.baufest.transfer.application.dto.enums.TransferStatus;
import com.baufest.transfer.application.dto.enums.TransferType;
import com.baufest.transfer.application.dto.request.CreateTransferRequestDTO;
import com.baufest.transfer.application.dto.response.TransferResponseDTO;
import com.baufest.transfer.application.mapper.dto.TransferMapper;
import com.baufest.transfer.persistence.entity.TransferJpaEntity;
import org.springframework.stereotype.Component;

@Component
public class TransferMapperImpl implements TransferMapper {
    @Override
    public TransferJpaEntity toEntity(CreateTransferRequestDTO createDTO) {
        return TransferJpaEntity.builder()
                .amount(createDTO.getAmount())
                .currency(createDTO.getCurrency().getCode())
                .transferType(createDTO.getTransferType().getCode())
                .sourceAccountId(createDTO.getSourceAccountId())
                .sourceUserId(createDTO.getSourceUserId())
                .targetAccountId(createDTO.getTargetAccountId())
                .targetUserId(createDTO.getTargetUserId())
                .build();
    }

    @Override
    public TransferResponseDTO toDTO(TransferJpaEntity entity) {
        return TransferResponseDTO.builder()
                .transferId(entity.getId())
                .amount(entity.getAmount())
                .currency(Currency.from(entity.getCurrency()))
                .transferType(TransferType.from(entity.getTransferType()))
                .sourceAccountId(entity.getSourceAccountId())
                .sourceUserId(entity.getSourceUserId())
                .targetAccountId(entity.getTargetAccountId())
                .targetUserId(entity.getTargetUserId())
                .status(TransferStatus.from(entity.getStatus()))
                .datetime(entity.getCreated().toString())
                .build();
    }
}
