package com.fsse2606.projectbackend.data.transaction.data;

import com.fsse2606.projectbackend.data.cartItem.data.UserResponseData;
import com.fsse2606.projectbackend.data.transcationProduct.data.TransactionProductResponseData;
import com.fsse2606.projectbackend.enumeration.TransactionStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TransactionResponseData {

    private Integer tid;
    private UserResponseData user;
    private LocalDateTime dateTime;
    private TransactionStatus status;
    private BigDecimal total;
    private List<TransactionProductResponseData> transactionProducts = new ArrayList<>();

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public UserResponseData getUser() {
        return user;
    }

    public void setUser(UserResponseData user) {
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

    public List<TransactionProductResponseData> getTransactionProducts() {
        return transactionProducts;
    }

    public void setTransactionProducts(List<TransactionProductResponseData> transactionProducts) {
        this.transactionProducts = transactionProducts;
    }
}
