
package com.fsse2606.projectbackend.service;

import com.fsse2606.projectbackend.data.cartItem.entity.CartItemEntity;
import com.fsse2606.projectbackend.data.product.data.response.ProductResponseData;
import com.fsse2606.projectbackend.data.product.entity.ProductEntity;
import jakarta.transaction.Transactional;

import java.util.List;

public interface ProductService {

    List<ProductResponseData> getAllProducts();

    ProductResponseData getProductByPid(Integer productId);

    ProductEntity getEntityByPid(Integer pid);

    // Original method — keep for compatibility
    @Transactional
    void deductStock(CartItemEntity cartItem);

    // New method — deduct stock using saved order product details
    @Transactional
    void deductStock(Integer pid, Integer quantity);
}
