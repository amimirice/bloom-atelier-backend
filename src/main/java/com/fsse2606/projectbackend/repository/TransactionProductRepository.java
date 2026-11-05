package com.fsse2606.projectbackend.repository;

import com.fsse2606.projectbackend.data.transcationProduct.entity.TransactionProductEntity;
import org.springframework.data.repository.CrudRepository;

public interface TransactionProductRepository extends CrudRepository<TransactionProductEntity, Integer> {
}
