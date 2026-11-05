package com.fsse2606.projectbackend.service.impl;

import com.fsse2606.projectbackend.data.user.data.FirebaseUserData;
import com.fsse2606.projectbackend.data.user.entity.UserEntity;
import com.fsse2606.projectbackend.mapper.user.UserEntityMapper;
import com.fsse2606.projectbackend.repository.UserRepository;
import com.fsse2606.projectbackend.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserEntityMapper userEntityMapper;

    public UserServiceImpl(UserRepository userRepository, UserEntityMapper userEntityMapper) {
        this.userRepository = userRepository;
        this.userEntityMapper = userEntityMapper;
    }

    @Override
    public UserEntity getUserByEmail(FirebaseUserData firebaseUserData){
        Optional<UserEntity> optionalUserEntity = userRepository.findByEmail(firebaseUserData.getEmail());

        if(optionalUserEntity.isEmpty()){
            UserEntity newUserEntity = userEntityMapper.toUserEntity(firebaseUserData);
            newUserEntity = userRepository.save(newUserEntity);
            return newUserEntity;
        }else {
            return optionalUserEntity.get();
        }
    }
}
