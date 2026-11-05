package com.fsse2606.projectbackend.mapper.user;

import com.fsse2606.projectbackend.data.user.data.FirebaseUserData;
import com.fsse2606.projectbackend.data.user.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserEntityMapper {
    public UserEntity toUserEntity(FirebaseUserData firebaseUserData){
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(firebaseUserData.getEmail());
        userEntity.setFirebaseUid(firebaseUserData.getFirebaseUid());
        return userEntity;
    }
}
