
package com.fsse2606.projectbackend.service.impl;

import com.fsse2606.projectbackend.data.cartItem.entity.CartItemEntity;
import com.fsse2606.projectbackend.data.product.data.response.ProductResponseData;
import com.fsse2606.projectbackend.data.product.entity.ProductEntity;
import com.fsse2606.projectbackend.exception.cartItem.CartItemException;
import com.fsse2606.projectbackend.exception.product.ProductNotFoundException;
import com.fsse2606.projectbackend.mapper.product.ProductDataMapper;
import com.fsse2606.projectbackend.repository.ProductRepository;
import com.fsse2606.projectbackend.service.ProductService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductDataMapper productDataMapper;

    @PersistenceContext
    private EntityManager entityManager;

    public ProductServiceImpl(
            ProductRepository productRepository,
            ProductDataMapper productDataMapper
    ) {
        this.productRepository = productRepository;
        this.productDataMapper = productDataMapper;
    }

    @Override
    public List<ProductResponseData> getAllProducts() {
        Iterable<ProductEntity> entityList = productRepository.findAll();

        return productDataMapper.toProductResponseDataList(entityList);
    }

    @Override
    public ProductResponseData getProductByPid(Integer productId) {
        ProductEntity entity = getEntityByPid(productId);

        return productDataMapper.toProductResponseData(entity);
    }

    @Override
    public ProductEntity getEntityByPid(Integer pid) {
        Optional<ProductEntity> optionalProductEntity =
                productRepository.findById(pid);

        if (optionalProductEntity.isEmpty()) {
            throw new ProductNotFoundException();
        }

        return optionalProductEntity.get();
    }

    // Original stock deduction method
    @Transactional
    @Override
    public void deductStock(CartItemEntity cartItem) {

        if (cartItem.getQuantity() > cartItem.getProduct().getStock()) {
            throw new CartItemException();
        }

        cartItem.getProduct().setStock(
                cartItem.getProduct().getStock() - cartItem.getQuantity()
        );
    }

    // New stock deduction method for saved transaction products
    @Transactional
    @Override
    public void deductStock(Integer pid, Integer quantity) {

        if (pid == null || quantity == null || quantity <= 0) {
            throw new CartItemException();
        }

        // Lock the product row during stock deduction
        ProductEntity product = entityManager.find(
                ProductEntity.class,
                pid,
                LockModeType.PESSIMISTIC_WRITE
        );

        if (product == null) {
            throw new ProductNotFoundException();
        }

        // Check stock before deduction
        if (
                product.getStock() == null ||
                        product.getStock() < quantity
        ) {
            throw new CartItemException();
        }

        product.setStock(product.getStock() - quantity);
    }
}
