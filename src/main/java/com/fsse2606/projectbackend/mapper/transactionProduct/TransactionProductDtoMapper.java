package com.fsse2606.projectbackend.mapper.transactionProduct;

import com.fsse2606.projectbackend.data.transcationProduct.data.TransactionProductResponseData;
import com.fsse2606.projectbackend.data.transcationProduct.dto.TransactionProductResponseDto;
import com.fsse2606.projectbackend.mapper.product.ProductDtoMapper;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class TransactionProductDtoMapper {

    private final ProductDtoMapper productDtoMapper;

    public TransactionProductDtoMapper(ProductDtoMapper productDtoMapper) {
        this.productDtoMapper = productDtoMapper;
    }

    public TransactionProductResponseDto toTransactionProductResponseDto
            (TransactionProductResponseData data){
        TransactionProductResponseDto dto = new TransactionProductResponseDto();
        dto.setTpid(data.getTpid());
        dto.setProduct(productDtoMapper.toProductResponseDto(data));
        dto.setQuantity(data.getQuantity());
        dto.setSubtotal(data.getPrice().multiply(new BigDecimal(data.getQuantity())));

        return dto;
    }

    public List<TransactionProductResponseDto> toTransactionProductResponseDtoList
            (List<TransactionProductResponseData> dataList){

        List<TransactionProductResponseDto> dtoList = new ArrayList<>();

        for (TransactionProductResponseData data : dataList){
            TransactionProductResponseDto dto = toTransactionProductResponseDto(data);
            dtoList.add(dto);
        }

        return dtoList;
    }

}
