package com.fsse2606.projectbackend.data.cartItem.data;

import com.fsse2606.projectbackend.data.product.data.response.ProductResponseData;
import com.fsse2606.projectbackend.data.product.entity.ProductEntity;
import com.fsse2606.projectbackend.data.user.entity.UserEntity;

public class CartItemResponseData {

    private Integer cid;
    private ProductResponseData product;
    private UserResponseData user;
    private Integer quantity;

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public ProductResponseData getProduct() {
        return product;
    }

    public void setProduct(ProductResponseData product) {
        this.product = product;
    }

    public UserResponseData getUser() {
        return user;
    }

    public void setUser(UserResponseData user) {
        this.user = user;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
