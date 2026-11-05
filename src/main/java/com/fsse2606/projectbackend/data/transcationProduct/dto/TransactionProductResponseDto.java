package com.fsse2606.projectbackend.data.transcationProduct.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fsse2606.projectbackend.data.product.dto.response.ProductResponseDto;

import java.math.BigDecimal;

public class TransactionProductResponseDto {

    @JsonPropertyOrder({"tpid","product","quantity","total"})
    private Integer tpid;
    private ProductResponseDto product;
    private Integer quantity;
    private BigDecimal subtotal;

    public Integer getTpid() {
        return tpid;
    }

    public void setTpid(Integer tpid) {
        this.tpid = tpid;
    }

    public ProductResponseDto getProduct() {
        return product;
    }

    public void setProduct(ProductResponseDto product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }
}
