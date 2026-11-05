package com.fsse2606.projectbackend.service.impl;

import com.fsse2606.projectbackend.data.cartItem.data.CartItemResponseData;
import com.fsse2606.projectbackend.data.cartItem.entity.CartItemEntity;
import com.fsse2606.projectbackend.data.product.entity.ProductEntity;
import com.fsse2606.projectbackend.data.user.data.FirebaseUserData;
import com.fsse2606.projectbackend.data.user.entity.UserEntity;
import com.fsse2606.projectbackend.exception.cartItem.CartItemException;
import com.fsse2606.projectbackend.mapper.cartItem.CartItemDataMapper;
import com.fsse2606.projectbackend.mapper.cartItem.CartItemEntityMapper;
import com.fsse2606.projectbackend.repository.CartItemRepository;
import com.fsse2606.projectbackend.service.CartItemService;
import com.fsse2606.projectbackend.service.ProductService;
import com.fsse2606.projectbackend.service.UserService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartItemServiceImpl implements CartItemService {

    private final Logger log = LoggerFactory.getLogger(CartItemServiceImpl.class);

    private final UserService userService;
    private final ProductService productService;
    private final CartItemRepository cartItemRepository;
    private final CartItemEntityMapper cartItemEntityMapper;
    private final CartItemDataMapper cartItemDataMapper;

    public CartItemServiceImpl(UserService userService, ProductService productService, CartItemRepository cartItemRepository, CartItemEntityMapper cartItemEntityMapper, CartItemDataMapper cartItemDataMapper) {
        this.userService = userService;
        this.productService = productService;
        this.cartItemRepository = cartItemRepository;
        this.cartItemEntityMapper = cartItemEntityMapper;
        this.cartItemDataMapper = cartItemDataMapper;
    }

    @Transactional
    @Override
    public void addCartItem(FirebaseUserData firebaseUserData, Integer pid, Integer quantity) {

        try {

            UserEntity userEntity = userService.getUserByEmail(firebaseUserData);
            ProductEntity productEntity = productService.getEntityByPid(pid);

            if (cartItemRepository.existsByUserAndProduct_Pid(userEntity, pid)) {

                CartItemEntity cartItem = getEntityByUserAndPid(userEntity, pid);
                cartItem.setQuantity(cartItem.getQuantity() + quantity);

                if (cartItem.getQuantity() > productEntity.getStock()) {
                    throw new CartItemException();
                }
            } else {

                CartItemEntity cartItem = cartItemEntityMapper.toCartItemEntity(productEntity, userEntity, quantity);

                if (cartItem.getQuantity() > productEntity.getStock()) {
                    throw new CartItemException();
                }

                cartItemRepository.save(cartItem);

            }
        } catch (Exception ex) {
            log.warn("Put item into cart failed:{}", ex.getMessage());
            throw ex;
        }

    }

    @Override
    public List<CartItemResponseData> getUserCart(FirebaseUserData firebaseUserData) {
        UserEntity userEntity = userService.getUserByEmail(firebaseUserData);

        List<CartItemEntity> cartItemEntityList = cartItemRepository.findByUser(userEntity);
        List<CartItemResponseData> cartItemResponseDataList = cartItemDataMapper.toCartItemResponseDataList(cartItemEntityList);

        return cartItemResponseDataList;
    }

    @Transactional
    @Override
    public void patchCartItemQuantity(FirebaseUserData firebaseUserData, Integer pid, Integer quantity) {

        try {

            UserEntity userEntity = userService.getUserByEmail(firebaseUserData);
            CartItemEntity cartItem = getEntityByUserAndPid(userEntity, pid);
            ProductEntity productEntity = productService.getEntityByPid(pid);

            if (productEntity.getStock() < quantity) {
                throw new CartItemException();
            }

            cartItem.setQuantity(quantity);

        } catch (Exception ex) {
            log.warn("Update item in cart failed:{}", ex.getMessage());
            throw ex;
        }
    }

    @Transactional
    @Override
    public void deleteCartItem(FirebaseUserData firebaseUserData, Integer pid) {
        try {

            UserEntity userEntity = userService.getUserByEmail(firebaseUserData);
            CartItemEntity cartItem = getEntityByUserAndPid(userEntity, pid);
            cartItemRepository.deleteByCid(cartItem.getCid());
        } catch (Exception ex) {
            log.warn("Delete item in cart failed:{}", ex.getMessage());
            throw ex;
        }
    }


    public CartItemEntity getEntityByUserAndPid(UserEntity user, Integer pid) {
        return cartItemRepository.findByUserAndProduct_Pid(user, pid).orElseThrow(
                () -> new CartItemException()
        );

    }

    @Override
    public List<CartItemEntity> getUserCart(UserEntity user) {
        return cartItemRepository.findByUser(user);
    }

    @Transactional
    @Override
    public void emptyUserCart(UserEntity user) {
        cartItemRepository.deleteByUser(user);
    }
}
