package com.fsse2606.projectbackend.controller;

import com.fsse2606.projectbackend.data.transaction.data.TransactionResponseData;
import com.fsse2606.projectbackend.data.transaction.dto.TransactionCheckoutRequestDto;
import com.fsse2606.projectbackend.data.transaction.dto.TransactionResponseDto;
import jakarta.validation.Valid;
import com.fsse2606.projectbackend.data.user.data.FirebaseUserData;
import com.fsse2606.projectbackend.mapper.transaction.TransactionDtoMapper;
import com.fsse2606.projectbackend.mapper.user.UserDataMapper;
import com.fsse2606.projectbackend.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final UserDataMapper userDataMapper;
    private final TransactionService transactionService;
    private final TransactionDtoMapper transactionDtoMapper;

    public TransactionController(UserDataMapper userDataMapper, TransactionService transactionService, TransactionDtoMapper transactionDtoMapper) {
        this.userDataMapper = userDataMapper;
        this.transactionService = transactionService;
        this.transactionDtoMapper = transactionDtoMapper;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TransactionResponseDto prepareTransaction(
            @AuthenticationPrincipal Jwt jwt,
            @Valid @RequestBody TransactionCheckoutRequestDto checkout
    ) {
        FirebaseUserData firebaseUserData =
                userDataMapper.toFirebaseUserData(jwt);

        TransactionResponseData responseData =
                transactionService.prepareTransaction(
                        firebaseUserData,
                        checkout
                );

        return transactionDtoMapper.toTransactionResponseDto(
                responseData
        );
    }


    @GetMapping("/{tid}")
    public TransactionResponseDto getByTid(@PathVariable Integer tid,
                                           @AuthenticationPrincipal Jwt jwt) {

        FirebaseUserData firebaseUserData = userDataMapper.toFirebaseUserData(jwt);
        TransactionResponseData responseData = transactionService.getByTid(firebaseUserData, tid);
        TransactionResponseDto responseDto = transactionDtoMapper.toTransactionResponseDto(responseData);
        return responseDto;

    }

    @PatchMapping("/{tid}/payment")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void processTransaction(@PathVariable Integer tid,
                                   @AuthenticationPrincipal Jwt jwt) {

        FirebaseUserData firebaseUserData = userDataMapper.toFirebaseUserData(jwt);

        transactionService.processTransaction(firebaseUserData, tid);
    }

    @PatchMapping("/{tid}/success")
    public TransactionResponseDto finishTransaction(@PathVariable Integer tid,
                                                    @AuthenticationPrincipal Jwt jwt) {

        FirebaseUserData firebaseUserData = userDataMapper.toFirebaseUserData(jwt);
        TransactionResponseData responseData = transactionService.finishTransaction(firebaseUserData, tid);
        TransactionResponseDto responseDto = transactionDtoMapper.toTransactionResponseDto(responseData);
        return responseDto;

    }
}


    

