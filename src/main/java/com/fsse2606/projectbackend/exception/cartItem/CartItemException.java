package com.fsse2606.projectbackend.exception.cartItem;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CartItemException extends RuntimeException {
    public CartItemException() {
        super("Cart Item Exception Occurs.");
    }
}
