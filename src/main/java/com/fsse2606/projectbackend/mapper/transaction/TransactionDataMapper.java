package com.fsse2606.projectbackend.mapper.transaction;

import com.fsse2606.projectbackend.data.transaction.data.TransactionResponseData;
import com.fsse2606.projectbackend.data.transaction.entity.TransactionEntity;
import com.fsse2606.projectbackend.mapper.transactionProduct.TransactionProductDataMapper;
import com.fsse2606.projectbackend.mapper.user.UserDataMapper;
import org.springframework.stereotype.Component;

@Component
public class TransactionDataMapper {

    private final UserDataMapper userDataMapper;
    private final TransactionProductDataMapper transactionProductDataMapper;

    public TransactionDataMapper(UserDataMapper userDataMapper, TransactionProductDataMapper transactionProductDataMapper) {
        this.userDataMapper = userDataMapper;
        this.transactionProductDataMapper = transactionProductDataMapper;
    }

    public TransactionResponseData toTransactionResponseData(TransactionEntity entity) {

        TransactionResponseData data = new TransactionResponseData();
        data.setTid(entity.getTid());
        data.setUser(userDataMapper.toUserResponseData(entity.getUser()));
        data.setStatus(entity.getStatus());
        data.setDateTime(entity.getDateTime());
        data.setTotal(entity.getTotal());

        data.setTransactionProducts(
                transactionProductDataMapper.toTransactionProductResponseDataList(
                        entity.getTransactionProduct()
                )
        );

        return data;
    }
}
