package com.fsse2606.projectbackend.service;

import com.fsse2606.projectbackend.data.cartItem.entity.CartItemEntity;
import com.fsse2606.projectbackend.data.transaction.entity.TransactionEntity;
import com.fsse2606.projectbackend.data.transcationProduct.entity.TransactionProductEntity;

public interface TransactionProductService {
    public TransactionProductEntity createTransactionProduct
            (CartItemEntity cartItem, TransactionEntity transaction);
}
