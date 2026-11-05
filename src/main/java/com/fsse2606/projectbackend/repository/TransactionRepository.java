package com.fsse2606.projectbackend.repository;

import com.fsse2606.projectbackend.data.transaction.entity.TransactionEntity;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository extends CrudRepository<TransactionEntity, Integer> {
}
