package com.fsse2606.projectbackend.data.transaction.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fsse2606.projectbackend.data.transcationProduct.dto.TransactionProductResponseDto;
import com.fsse2606.projectbackend.enumeration.TransactionStatus;
import com.fsse2606.projectbackend.repository.TransactionProductRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@JsonPropertyOrder({"tid","buyerUid","dataTime","status","total","items"})
public class TransactionResponseDto {

    private Integer tid;
    private Integer buyerUid;
    @JsonFormat(pattern = "yyyyMMdd'T'HH:mm:ss")
    private LocalDateTime dateTime;
    private TransactionStatus status;
    private BigDecimal total;
    private List<TransactionProductResponseDto> items = new ArrayList<>();

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public Integer getBuyerUid() {
        return buyerUid;
    }

    public void setBuyerUid(Integer buyerUid) {
        this.buyerUid = buyerUid;
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

    public List<TransactionProductResponseDto> getItems() {
        return items;
    }

    public void setItems(List<TransactionProductResponseDto> items) {
        this.items = items;
    }
}
