package com.fsse2606.projectbackend.mapper.user;

import com.fsse2606.projectbackend.data.cartItem.data.UserResponseData;
import com.fsse2606.projectbackend.data.user.data.FirebaseUserData;
import com.fsse2606.projectbackend.data.user.entity.UserEntity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

@Component
public class UserDataMapper {
    public FirebaseUserData toFirebaseUserData(Jwt jwt){
        FirebaseUserData firebaseUserData = new FirebaseUserData();
        firebaseUserData.setEmail(jwt.getClaimAsString("email"));
        firebaseUserData.setFirebaseUid(jwt.getClaimAsString("user_id"));
        return firebaseUserData;
    }

    public UserResponseData toUserResponseData(UserEntity entity){
        UserResponseData data = new UserResponseData();
        data.setUid(entity.getUid());
        data.setEmail(entity.getEmail());
        data.setFirebaseUid(entity.getFirebaseUid());

        return data;
    }
}
