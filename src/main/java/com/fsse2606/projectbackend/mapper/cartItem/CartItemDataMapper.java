package com.fsse2606.projectbackend.mapper.cartItem;

import com.fsse2606.projectbackend.data.cartItem.data.CartItemResponseData;
import com.fsse2606.projectbackend.data.cartItem.entity.CartItemEntity;
import com.fsse2606.projectbackend.mapper.product.ProductDataMapper;
import com.fsse2606.projectbackend.mapper.user.UserDataMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CartItemDataMapper {
    private final ProductDataMapper productDataMapper;
    private final UserDataMapper userDataMapper;

    public CartItemDataMapper(ProductDataMapper productDataMapper, UserDataMapper userDataMapper) {
        this.productDataMapper = productDataMapper;
        this.userDataMapper = userDataMapper;
    }

    public CartItemResponseData toCartItemResponseData (CartItemEntity entity){
        CartItemResponseData data = new CartItemResponseData();
        data.setCid(entity.getCid());
        data.setQuantity(entity.getQuantity());
        data.setProduct(productDataMapper.toProductResponseData((entity.getProduct())));
        data.setUser(userDataMapper.toUserResponseData(entity.getUser()));

        return data;
    }

    public List<CartItemResponseData> toCartItemResponseDataList (List<CartItemEntity> entityList){
        List<CartItemResponseData> dataList = new ArrayList<>();

        for(CartItemEntity entity : entityList){
            CartItemResponseData data = toCartItemResponseData(entity);
            dataList.add(data);
        }

        return dataList;
    }
}
