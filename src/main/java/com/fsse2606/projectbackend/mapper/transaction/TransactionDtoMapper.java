package com.fsse2606.projectbackend.mapper.transaction;

import com.fsse2606.projectbackend.data.transaction.data.TransactionResponseData;
import com.fsse2606.projectbackend.data.transaction.dto.TransactionResponseDto;
import com.fsse2606.projectbackend.mapper.transactionProduct.TransactionProductDtoMapper;
import org.springframework.stereotype.Component;

@Component
public class TransactionDtoMapper {

    private final TransactionProductDtoMapper transactionProductDtoMapper;

    public TransactionDtoMapper(TransactionProductDtoMapper transactionProductDtoMapper) {
        this.transactionProductDtoMapper = transactionProductDtoMapper;
    }

    public TransactionResponseDto toTransactionResponseDto(TransactionResponseData data){
        TransactionResponseDto dto = new TransactionResponseDto();
        dto.setTid(data.getTid());
        dto.setBuyerUid(data.getUser().getUid());
        dto.setDateTime(data.getDateTime());
        dto.setStatus(data.getStatus());
        dto.setTotal(data.getTotal());
        dto.setItems(
                transactionProductDtoMapper.toTransactionProductResponseDtoList(
                        data.getTransactionProducts()
                )

        );

        return dto;
    }
}
