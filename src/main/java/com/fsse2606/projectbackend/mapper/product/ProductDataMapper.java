package com.fsse2606.projectbackend.mapper.product;

import com.fsse2606.projectbackend.data.product.data.response.ProductResponseData;
import com.fsse2606.projectbackend.data.product.entity.ProductEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductDataMapper {
    public ProductResponseData toProductResponseData(ProductEntity entity){
        ProductResponseData productResponseData = new ProductResponseData();
        productResponseData.setPid(entity.getPid());
        productResponseData.setName(entity.getName());
        productResponseData.setDescription(entity.getDescription());
        productResponseData.setImageUrl(entity.getImageUrl());
        productResponseData.setPrice(entity.getPrice());
        productResponseData.setStock(entity.getStock());
        return productResponseData;
    }

    public List<ProductResponseData> toProductResponseDataList(Iterable<ProductEntity> entityList){
        List<ProductResponseData> productResponseDataList = new ArrayList<>();
        for (ProductEntity entity : entityList) {
            ProductResponseData productResponseData = toProductResponseData(entity);
            productResponseDataList.add(productResponseData);
        }
        return productResponseDataList;
    }

}
