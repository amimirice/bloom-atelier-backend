package com.fsse2606.projectbackend.controller;

import com.fsse2606.projectbackend.data.cartItem.data.CartItemResponseData;
import com.fsse2606.projectbackend.data.cartItem.dto.CartItemResponseDto;
import com.fsse2606.projectbackend.data.user.data.FirebaseUserData;
import com.fsse2606.projectbackend.mapper.cartItem.CartItemDtoMapper;
import com.fsse2606.projectbackend.mapper.user.UserDataMapper;
import com.fsse2606.projectbackend.service.CartItemService;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart/items")
public class CartItemController {
    private final UserDataMapper userDataMapper;
    private final CartItemService cartItemService;
    private final CartItemDtoMapper cartItemDtoMapper;

    public CartItemController(UserDataMapper userDataMapper, CartItemService cartItemService, CartItemDtoMapper cartItemDtoMapper) {
        this.userDataMapper = userDataMapper;
        this.cartItemService = cartItemService;
        this.cartItemDtoMapper = cartItemDtoMapper;
    }

    @PutMapping("/{pid}/{quantity}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addCartItem(@AuthenticationPrincipal Jwt jwt,
                            @PathVariable Integer pid,
                            @PathVariable @Positive Integer quantity) {
        FirebaseUserData firebaseUserData = userDataMapper.toFirebaseUserData(jwt);
        cartItemService.addCartItem(firebaseUserData, pid, quantity);
    }


    @GetMapping
    public List<CartItemResponseDto> getUserCart(@AuthenticationPrincipal Jwt jwt) {
        FirebaseUserData firebaseUserData = userDataMapper.toFirebaseUserData(jwt);
        List<CartItemResponseData> responseDataList = cartItemService.getUserCart(firebaseUserData);
        List<CartItemResponseDto> responseDtoList = cartItemDtoMapper.toCartItemResponseDtoList(responseDataList);

        return responseDtoList;

    }


    @PatchMapping("/{pid}/{quantity}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCartItemQuantity(@AuthenticationPrincipal Jwt jwt,
                                       @PathVariable Integer pid,
                                       @PathVariable @Positive Integer quantity) {
        FirebaseUserData firebaseUserData = userDataMapper.toFirebaseUserData(jwt);
        cartItemService.patchCartItemQuantity(firebaseUserData, pid, quantity);
    }

    @DeleteMapping("/{pid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeCartItem(@AuthenticationPrincipal Jwt jwt,
                               @PathVariable Integer pid) {
        FirebaseUserData firebaseUserData = userDataMapper.toFirebaseUserData(jwt);
        cartItemService.deleteCartItem(firebaseUserData, pid);
    }
}