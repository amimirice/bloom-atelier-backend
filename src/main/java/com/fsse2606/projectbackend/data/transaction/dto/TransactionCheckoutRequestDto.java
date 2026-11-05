
package com.fsse2606.projectbackend.data.transaction.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class TransactionCheckoutRequestDto {

    @NotBlank
    @Size(max = 100)
    private String fullName;

    @NotBlank
    @Email
    @Size(max = 254)
    private String email;

    @NotBlank
    @Size(max = 30)
    private String phone;

    @NotBlank
    @Size(max = 1000)
    private String address;

    @NotNull
    @Future
    private LocalDate deliveryDate;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }
}