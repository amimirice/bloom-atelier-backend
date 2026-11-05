package com.fsse2606.projectbackend.mapper.cartItem;

import com.fsse2606.projectbackend.data.cartItem.entity.CartItemEntity;
import com.fsse2606.projectbackend.data.product.entity.ProductEntity;
import com.fsse2606.projectbackend.data.user.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class CartItemEntityMapper {

    public CartItemEntity toCartItemEntity (ProductEntity product, UserEntity user,Integer quantity){
        CartItemEntity cartItemEntity = new CartItemEntity();
        cartItemEntity.setProduct(product);
        cartItemEntity.setUser(user);
        cartItemEntity.setQuantity(quantity);

        return cartItemEntity;
    }
    }

