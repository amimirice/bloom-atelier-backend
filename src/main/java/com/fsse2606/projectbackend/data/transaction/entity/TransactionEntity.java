
package com.fsse2606.projectbackend.data.transaction.entity;

import com.fsse2606.projectbackend.data.transcationProduct.entity.TransactionProductEntity;
import com.fsse2606.projectbackend.data.user.entity.UserEntity;
import com.fsse2606.projectbackend.enumeration.TransactionStatus;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "transaction")
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tid;

    @ManyToOne
    @JoinColumn(name = "buyer_uid", nullable = false)
    private UserEntity user;

    @Column(nullable = false)
    private LocalDateTime dateTime;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TransactionStatus status;

    @Column(nullable = false)
    private BigDecimal total;

    @OneToMany(mappedBy = "transaction")
    private List<TransactionProductEntity> transactionProduct = new ArrayList<>();

    // Checkout customer and delivery details
    @Column(length = 100)
    private String fullName;

    @Column(length = 254)
    private String contactEmail;

    @Column(length = 30)
    private String phone;

    @Column(length = 1000)
    private String deliveryAddress;

    private LocalDate deliveryDate;

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    public void setStatus(TransactionStatus status) {
        this.status = status;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public List<TransactionProductEntity> getTransactionProduct() {
        return transactionProduct;
    }

    public void setTransactionProduct(
            List<TransactionProductEntity> transactionProduct
    ) {
        this.transactionProduct = transactionProduct;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }
}
