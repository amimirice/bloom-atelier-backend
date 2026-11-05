package com.fsse2606.projectbackend.mapper.transaction;

import com.fsse2606.projectbackend.data.transaction.dto.TransactionCheckoutRequestDto;
import com.fsse2606.projectbackend.data.transaction.entity.TransactionEntity;
import com.fsse2606.projectbackend.data.user.entity.UserEntity;
import com.fsse2606.projectbackend.enumeration.TransactionStatus;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
public class TransactionEntityMapper {

    // Original transaction mapper
    public TransactionEntity toTransactionEntity(UserEntity user) {

        TransactionEntity transaction = new TransactionEntity();

        transaction.setUser(user);
        transaction.setStatus(TransactionStatus.PREPARE);
        transaction.setDateTime(LocalDateTime.now());
        transaction.setTotal(BigDecimal.ZERO);

        return transaction;
    }

    // Transaction mapper with checkout delivery details
    public TransactionEntity toTransactionEntity(
            UserEntity user,
            TransactionCheckoutRequestDto checkout
    ) {

        // Reuse the original transaction initialization
        TransactionEntity transaction = toTransactionEntity(user);

        // Add customer and delivery information
        transaction.setFullName(checkout.getFullName());
        transaction.setContactEmail(checkout.getEmail());
        transaction.setPhone(checkout.getPhone());
        transaction.setDeliveryAddress(checkout.getAddress());
        transaction.setDeliveryDate(checkout.getDeliveryDate());

        return transaction;
    }
}
