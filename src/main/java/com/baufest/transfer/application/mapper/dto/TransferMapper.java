package com.baufest.transfer.application.mapper.dto;

import com.baufest.transfer.application.dto.request.CreateTransferRequestDTO;
import com.baufest.transfer.application.dto.response.TransferResponseDTO;
import com.baufest.transfer.persistence.entity.TransferJpaEntity;

public interface TransferMapper {
    public TransferJpaEntity toEntity(CreateTransferRequestDTO createDTO);
    public TransferResponseDTO toDTO(TransferJpaEntity entity);
}
