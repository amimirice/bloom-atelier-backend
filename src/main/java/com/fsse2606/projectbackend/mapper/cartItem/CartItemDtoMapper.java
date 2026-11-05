package com.fsse2606.projectbackend.mapper.cartItem;

import com.fsse2606.projectbackend.data.cartItem.data.CartItemResponseData;
import com.fsse2606.projectbackend.data.cartItem.dto.CartItemResponseDto;
import com.fsse2606.projectbackend.data.cartItem.entity.CartItemEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CartItemDtoMapper {

    public CartItemResponseDto toCartItemResponseDto (CartItemResponseData data){
        CartItemResponseDto dto = new CartItemResponseDto();
        dto.setPid(data.getProduct().getPid());
        dto.setName(data.getProduct().getName());
        dto.setImageUrl(data.getProduct().getImageUrl());
        dto.setPrice(data.getProduct().getPrice());
        dto.setCartQuantity(data.getQuantity());
        dto.setStock(data.getProduct().getStock());

        return dto;
    }

    public List<CartItemResponseDto> toCartItemResponseDtoList (List<CartItemResponseData> dataList){
        List<CartItemResponseDto> dtoList = new ArrayList<>();

        for(CartItemResponseData data : dataList){
            CartItemResponseDto dto = toCartItemResponseDto(data);
            dtoList.add(dto);
        }

        return dtoList;
    }

}
