
package com.fsse2606.projectbackend.service;

import com.fsse2606.projectbackend.data.transaction.data.TransactionResponseData;
import com.fsse2606.projectbackend.data.transaction.dto.TransactionCheckoutRequestDto;
import com.fsse2606.projectbackend.data.user.data.FirebaseUserData;
import jakarta.transaction.Transactional;

public interface TransactionService {

    // Original transaction preparation
    TransactionResponseData prepareTransaction(
            FirebaseUserData firebaseUserData
    );

    // Transaction preparation with checkout delivery details
    TransactionResponseData prepareTransaction(
            FirebaseUserData firebaseUserData,
            TransactionCheckoutRequestDto checkout
    );

    TransactionResponseData getByTid(
            FirebaseUserData firebaseUserData,
            Integer tid
    );

    @Transactional
    void processTransaction(
            FirebaseUserData firebaseUserData,
            Integer tid
    );

    @Transactional
    TransactionResponseData finishTransaction(
            FirebaseUserData firebaseUserData,
            Integer tid
    );
}
