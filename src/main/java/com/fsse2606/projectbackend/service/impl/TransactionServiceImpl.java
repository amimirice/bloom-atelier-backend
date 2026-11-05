package com.fsse2606.projectbackend.service.impl;

import com.fsse2606.projectbackend.data.cartItem.entity.CartItemEntity;
import com.fsse2606.projectbackend.data.transaction.data.TransactionResponseData;
import com.fsse2606.projectbackend.data.transaction.entity.TransactionEntity;
import com.fsse2606.projectbackend.data.transcationProduct.entity.TransactionProductEntity;
import com.fsse2606.projectbackend.data.user.data.FirebaseUserData;
import com.fsse2606.projectbackend.data.user.entity.UserEntity;
import com.fsse2606.projectbackend.enumeration.TransactionStatus;
import com.fsse2606.projectbackend.exception.transaction.TransactionException;
import com.fsse2606.projectbackend.mapper.transaction.TransactionDataMapper;
import com.fsse2606.projectbackend.mapper.transaction.TransactionEntityMapper;
import com.fsse2606.projectbackend.repository.TransactionRepository;
import com.fsse2606.projectbackend.service.*;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

import com.fsse2606.projectbackend.data.transaction.dto.TransactionCheckoutRequestDto;
import java.time.LocalDate;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final Logger log = LoggerFactory.getLogger(TransactionServiceImpl.class);
    private final UserService userService;
    private final CartItemService cartItemService;
    private final TransactionEntityMapper transactionEntityMapper;
    private final TransactionRepository transactionRepository;
    private final TransactionProductService transactionProductService;
    private final TransactionDataMapper transactionDataMapper;
    private final ProductService productService;

    public TransactionServiceImpl(UserService userService, CartItemService cartItemService, TransactionEntityMapper transactionEntityMapper, TransactionRepository transactionRepository, TransactionProductService transactionProductService, TransactionDataMapper transactionDataMapper, ProductService productService) {
        this.userService = userService;
        this.cartItemService = cartItemService;
        this.transactionEntityMapper = transactionEntityMapper;
        this.transactionRepository = transactionRepository;
        this.transactionProductService = transactionProductService;
        this.transactionDataMapper = transactionDataMapper;
        this.productService = productService;
    }

    @Override
    @Transactional
    public TransactionResponseData prepareTransaction(FirebaseUserData firebaseUserData) {
        try {

            UserEntity user = userService.getUserByEmail(firebaseUserData);
            List<CartItemEntity> cartItemEntityList = cartItemService.getUserCart(user);

            if (cartItemEntityList.isEmpty()) {
                throw new TransactionException();
            }

            TransactionEntity transaction = transactionEntityMapper.toTransactionEntity(user);
            transaction = transactionRepository.save(transaction);

            for (CartItemEntity cartItem : cartItemEntityList) {

                TransactionProductEntity transactionProduct =
                        transactionProductService.createTransactionProduct(cartItem, transaction);

                transaction.getTransactionProduct().add(transactionProduct);

                transaction.setTotal(
                        transaction.getTotal().add(
                                transactionProduct.getPrice().multiply(
                                        new BigDecimal(transactionProduct.getQuantity())
                                )
                        )
                );

            }

            return transactionDataMapper.toTransactionResponseData(transaction);


        } catch (Exception ex) {
            log.warn("Create transaction failed:{}", ex.getMessage());
            throw ex;
        }
    }


    // Prepare transaction with checkout delivery details
    @Override
    @Transactional
    public TransactionResponseData prepareTransaction(
            FirebaseUserData firebaseUserData,
            TransactionCheckoutRequestDto checkout
    ) {
        try {
            // Delivery must be scheduled from tomorrow onwards
            if (
                    checkout == null ||
                            checkout.getDeliveryDate() == null ||
                            !checkout.getDeliveryDate().isAfter(LocalDate.now())
            ) {
                throw new TransactionException();
            }

            UserEntity user =
                    userService.getUserByEmail(firebaseUserData);

            List<CartItemEntity> cartItemEntityList =
                    cartItemService.getUserCart(user);

            // Prevent creating a transaction with an empty cart
            if (cartItemEntityList.isEmpty()) {
                throw new TransactionException();
            }

            // Create transaction with customer and delivery details
            TransactionEntity transaction =
                    transactionEntityMapper.toTransactionEntity(
                            user,
                            checkout
                    );

            transaction = transactionRepository.save(transaction);

            // Copy cart products into transaction
            for (CartItemEntity cartItem : cartItemEntityList) {

                TransactionProductEntity transactionProduct =
                        transactionProductService.createTransactionProduct(
                                cartItem,
                                transaction
                        );

                transaction.getTransactionProduct().add(
                        transactionProduct
                );

                transaction.setTotal(
                        transaction.getTotal().add(
                                transactionProduct.getPrice().multiply(
                                        BigDecimal.valueOf(
                                                transactionProduct.getQuantity()
                                        )
                                )
                        )
                );
            }

            return transactionDataMapper.toTransactionResponseData(
                    transaction
            );

        } catch (Exception ex) {
            log.warn(
                    "Create checkout transaction failed: {}",
                    ex.getMessage()
            );
            throw ex;
        }
    }



    @Override
    public TransactionResponseData getByTid(FirebaseUserData firebaseUserData, Integer tid){

        try {
            TransactionEntity transaction = getEntityByTid(tid);
            validateUser(firebaseUserData.getEmail(),transaction);

            return transactionDataMapper.toTransactionResponseData(transaction);


        } catch (Exception ex){
            log.warn("Get transaction failed:{}", ex.getMessage());
            throw ex;
        }
    }

    @Transactional
    @Override
    public void processTransaction(FirebaseUserData firebaseUserData, Integer tid){

        try{
            TransactionEntity transaction = getEntityByTid(tid);
            validateUser(firebaseUserData.getEmail(),transaction);

            if(transaction.getStatus() != TransactionStatus.PREPARE){
                throw new TransactionException();
            }

            transaction.setStatus(TransactionStatus.PROCESSING);


        } catch (Exception ex) {
            log.warn("Process transaction failed: {}", ex.getMessage());
            throw ex;
        }
    }


    @Transactional
    @Override
    public TransactionResponseData finishTransaction(
            FirebaseUserData firebaseUserData,
            Integer tid
    ) {
        try {
            TransactionEntity transaction = getEntityByTid(tid);

            validateUser(firebaseUserData.getEmail(), transaction);

            // Only PROCESSING transactions can be completed
            if (transaction.getStatus() != TransactionStatus.PROCESSING) {
                throw new TransactionException();
            }

            // Read products saved when the transaction was created
            List<TransactionProductEntity> transactionProducts =
                    transaction.getTransactionProduct();

            if (
                    transactionProducts == null ||
                            transactionProducts.isEmpty()
            ) {
                throw new TransactionException();
            }

            // Check that the backend cart has not changed
            List<CartItemEntity> cartItems =
                    cartItemService.getUserCart(transaction.getUser());

            if (cartItems.size() != transactionProducts.size()) {
                throw new TransactionException();
            }

            for (TransactionProductEntity transactionProduct : transactionProducts) {

                boolean itemMatches = cartItems.stream().anyMatch(
                        cartItem ->
                                cartItem.getProduct().getPid()
                                        .equals(transactionProduct.getPid())
                                        &&
                                        cartItem.getQuantity()
                                                .equals(transactionProduct.getQuantity())
                );

                if (!itemMatches) {
                    throw new TransactionException();
                }
            }

            // Deduct stock using the saved transaction quantities
            for (TransactionProductEntity transactionProduct : transactionProducts) {
                productService.deductStock(
                        transactionProduct.getPid(),
                        transactionProduct.getQuantity()
                );
            }

            // Clear the verified backend cart
            cartItemService.emptyUserCart(transaction.getUser());

            // Mark the transaction as completed
            transaction.setStatus(TransactionStatus.SUCCESS);

            return transactionDataMapper.toTransactionResponseData(transaction);

        } catch (Exception ex) {
            log.warn("Finish transaction failed: {}", ex.getMessage());
            throw ex;
        }
    }


    public TransactionEntity getEntityByTid (Integer tid){

        return transactionRepository.findById(tid).orElseThrow(
                () -> new TransactionException()
        );
    }

    public void validateUser(String email, TransactionEntity transaction){
        if(!transaction.getUser().getEmail().equals(email)){
            throw new TransactionException();

        }
    }
}