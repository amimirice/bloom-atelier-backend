package com.fsse2606.projectbackend.mapper.transactionProduct;

import com.fsse2606.projectbackend.data.transaction.data.TransactionResponseData;
import com.fsse2606.projectbackend.data.transcationProduct.data.TransactionProductResponseData;
import com.fsse2606.projectbackend.data.transcationProduct.entity.TransactionProductEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TransactionProductDataMapper {

    public TransactionProductResponseData toTransactionProductResponseData (TransactionProductEntity entity){

        TransactionProductResponseData data = new TransactionProductResponseData();
        data.setTpid(entity.getTpid());
        data.setPid(entity.getPid());
        data.setName(entity.getName());
        data.setDescription(entity.getDescription());
        data.setImageUrl(entity.getImageUrl());
        data.setPrice(entity.getPrice());
        data.setStock(entity.getStock());
        data.setQuantity(entity.getQuantity());

        return data;
    }
    public List<TransactionProductResponseData> toTransactionProductResponseDataList
            (List<TransactionProductEntity> entityList){

        List<TransactionProductResponseData> dataList = new ArrayList<>();

        for(TransactionProductEntity entity : entityList){
            TransactionProductResponseData data = toTransactionProductResponseData(entity);
            dataList.add(data);
        }

        return dataList;
    }
}
