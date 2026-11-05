package com.fsse2606.projectbackend.repository;

import com.fsse2606.projectbackend.data.cartItem.entity.CartItemEntity;
import com.fsse2606.projectbackend.data.user.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends CrudRepository<CartItemEntity, Integer> {

    Boolean existsByUserAndProduct_Pid(UserEntity user, Integer pid);

    Optional<CartItemEntity> findByUserAndProduct_Pid(UserEntity user, Integer pid);

    List<CartItemEntity> findByUser (UserEntity user);

    void deleteByCid(Integer cid);

    void deleteByUser(UserEntity user);


}
