package com.fsse2606.projectbackend.service;

import com.fsse2606.projectbackend.data.user.data.FirebaseUserData;
import com.fsse2606.projectbackend.data.user.entity.UserEntity;

public interface UserService {
    UserEntity getUserByEmail(FirebaseUserData firebaseUserData);
}
