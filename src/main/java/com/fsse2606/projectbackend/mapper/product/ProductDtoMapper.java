package com.fsse2606.projectbackend.mapper.product;

import com.fsse2606.projectbackend.data.product.data.response.ProductResponseData;
import com.fsse2606.projectbackend.data.product.dto.response.GetAllProductResponseDto;
import com.fsse2606.projectbackend.data.product.dto.response.ProductResponseDto;
import com.fsse2606.projectbackend.data.transcationProduct.data.TransactionProductResponseData;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductDtoMapper {

    public GetAllProductResponseDto toGetAllProductResponseDto(ProductResponseData productResponseData) {
        GetAllProductResponseDto getAllProductResponseDto = new GetAllProductResponseDto();
        getAllProductResponseDto.setPid(productResponseData.getPid());
        getAllProductResponseDto.setName(productResponseData.getName());
        getAllProductResponseDto.setImageUrl(productResponseData.getImageUrl());
        getAllProductResponseDto.setPrice(productResponseData.getPrice());
        getAllProductResponseDto.setHasStock(productResponseData.getStock() > 0);
        return getAllProductResponseDto;
    }

    public List<GetAllProductResponseDto> toGetAllProductResponseDtoList(
            List<ProductResponseData> productResponseDataList) {

        List<GetAllProductResponseDto> getAllProductResponseDtoList = new ArrayList<>();

        for (ProductResponseData productResponseData : productResponseDataList) {
            GetAllProductResponseDto getAllProductResponseDto =
                    toGetAllProductResponseDto(productResponseData);

            getAllProductResponseDtoList.add(getAllProductResponseDto);
        }

        return getAllProductResponseDtoList;
    }

    public ProductResponseDto toProductResponseDto(ProductResponseData productResponseData) {
        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setPid(productResponseData.getPid());
        productResponseDto.setName(productResponseData.getName());
        productResponseDto.setDescription(productResponseData.getDescription());
        productResponseDto.setImageUrl(productResponseData.getImageUrl());
        productResponseDto.setPrice(productResponseData.getPrice());
        productResponseDto.setStock(productResponseData.getStock());
        return productResponseDto;
    }

    public ProductResponseDto toProductResponseDto(
            TransactionProductResponseData productResponseData) {

        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setPid(productResponseData.getPid());
        productResponseDto.setName(productResponseData.getName());
        productResponseDto.setDescription(productResponseData.getDescription());
        productResponseDto.setImageUrl(productResponseData.getImageUrl());
        productResponseDto.setPrice(productResponseData.getPrice());
        productResponseDto.setStock(productResponseData.getStock());
        return productResponseDto;
    }
}

