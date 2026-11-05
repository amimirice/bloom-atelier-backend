package com.fsse2606.projectbackend.repository;

import com.fsse2606.projectbackend.data.product.entity.ProductEntity;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<ProductEntity,Integer> {
}
