package com.fsse2606.projectbackend.exception.transaction;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class TransactionException extends RuntimeException {
    public TransactionException() {
        super("Transaction Exception Occurs.");}
}
