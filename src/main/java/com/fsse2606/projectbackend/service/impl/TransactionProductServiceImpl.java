package com.fsse2606.projectbackend.service.impl;

import com.fsse2606.projectbackend.data.cartItem.entity.CartItemEntity;
import com.fsse2606.projectbackend.data.transaction.entity.TransactionEntity;
import com.fsse2606.projectbackend.data.transcationProduct.entity.TransactionProductEntity;
import com.fsse2606.projectbackend.mapper.transactionProduct.TransactionProductEntityMapper;
import com.fsse2606.projectbackend.repository.TransactionProductRepository;
import com.fsse2606.projectbackend.service.TransactionProductService;
import org.springframework.stereotype.Service;

@Service
public class TransactionProductServiceImpl implements TransactionProductService {

    private final TransactionProductRepository transactionProductRepository;
    private final TransactionProductEntityMapper transactionProductEntityMapper;

    public TransactionProductServiceImpl(TransactionProductRepository transactionProductRepository, TransactionProductEntityMapper transactionProductEntityMapper) {
        this.transactionProductRepository = transactionProductRepository;
        this.transactionProductEntityMapper = transactionProductEntityMapper;
    }

    @Override
    public TransactionProductEntity createTransactionProduct
            (CartItemEntity cartItem, TransactionEntity transaction){

        TransactionProductEntity tpEntity =
                transactionProductEntityMapper.toTransactionProductEntity
                        (transaction,cartItem);

        return transactionProductRepository.save(tpEntity);


    }
}
