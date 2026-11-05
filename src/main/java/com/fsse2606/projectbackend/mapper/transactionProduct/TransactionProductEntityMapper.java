package com.fsse2606.projectbackend.mapper.transactionProduct;

import com.fsse2606.projectbackend.data.cartItem.entity.CartItemEntity;
import com.fsse2606.projectbackend.data.transaction.entity.TransactionEntity;
import com.fsse2606.projectbackend.data.transcationProduct.entity.TransactionProductEntity;
import org.springframework.stereotype.Component;

@Component
public class TransactionProductEntityMapper {

    public TransactionProductEntity toTransactionProductEntity
            (TransactionEntity transaction, CartItemEntity cartItem){

        TransactionProductEntity tpEntity = new TransactionProductEntity();
        tpEntity.setTransaction(transaction);
        tpEntity.setPid(cartItem.getProduct().getPid());
        tpEntity.setName(cartItem.getProduct().getName());
        tpEntity.setDescription(cartItem.getProduct().getDescription());
        tpEntity.setImageUrl(cartItem.getProduct().getImageUrl());
        tpEntity.setPrice(cartItem.getProduct().getPrice());
        tpEntity.setStock(cartItem.getProduct().getStock());
        tpEntity.setQuantity(cartItem.getQuantity());

        return tpEntity;
    }
}
