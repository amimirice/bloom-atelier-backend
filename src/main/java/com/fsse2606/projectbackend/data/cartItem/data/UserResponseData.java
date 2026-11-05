package com.fsse2606.projectbackend.data.cartItem.data;

public class UserResponseData {

    private Integer uid;
    private String email;
    private String firebaseUid;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirebaseUid() {
        return firebaseUid;
    }

    public void setFirebaseUid(String firebaseUid) {
        this.firebaseUid = firebaseUid;
    }
}
