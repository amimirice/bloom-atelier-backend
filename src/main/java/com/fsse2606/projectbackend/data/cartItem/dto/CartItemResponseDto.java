package com.fsse2606.projectbackend.data.cartItem.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.math.BigDecimal;

@JsonPropertyOrder({"pid","name","imageUrl","price","cartQuantity","stock"})
public class CartItemResponseDto {

        private Integer pid;
        private String name;
        private String imageUrl;
        private BigDecimal price;
        private Integer cartQuantity;
        private Integer stock;

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getCartQuantity() {
        return cartQuantity;
    }

    public void setCartQuantity(Integer cartQuantity) {
        this.cartQuantity = cartQuantity;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
}
