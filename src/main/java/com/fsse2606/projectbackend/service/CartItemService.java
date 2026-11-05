package com.fsse2606.projectbackend.service;

import com.fsse2606.projectbackend.data.cartItem.data.CartItemResponseData;
import com.fsse2606.projectbackend.data.cartItem.entity.CartItemEntity;
import com.fsse2606.projectbackend.data.user.data.FirebaseUserData;
import com.fsse2606.projectbackend.data.user.entity.UserEntity;
import jakarta.transaction.Transactional;

import java.util.List;

public interface CartItemService {
    @Transactional
    void addCartItem(FirebaseUserData firebaseUserData, Integer pid, Integer quantity);

    List<CartItemResponseData> getUserCart(FirebaseUserData firebaseUserData);

    @Transactional
    void patchCartItemQuantity(FirebaseUserData firebaseUserData, Integer pid, Integer quantity);

    @Transactional
    void deleteCartItem(FirebaseUserData firebaseUserData, Integer pid);

    List<CartItemEntity> getUserCart (UserEntity user);

    @Transactional
    void emptyUserCart(UserEntity user);
}
