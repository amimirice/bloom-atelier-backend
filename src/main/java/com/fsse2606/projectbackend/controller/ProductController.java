package com.fsse2606.projectbackend.controller;

import com.fsse2606.projectbackend.data.product.data.response.ProductResponseData;
import com.fsse2606.projectbackend.data.product.dto.response.GetAllProductResponseDto;
import com.fsse2606.projectbackend.data.product.dto.response.ProductResponseDto;
import com.fsse2606.projectbackend.mapper.product.ProductDtoMapper;
import com.fsse2606.projectbackend.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/public/products")
public class ProductController {
    private final ProductService productService;
    private final ProductDtoMapper productDtoMapper;

    public ProductController(ProductService productService, ProductDtoMapper productDtoMapper) {
        this.productService = productService;
        this.productDtoMapper = productDtoMapper;
    }

    @GetMapping
    public List<GetAllProductResponseDto> getAllProducts() {
        List<ProductResponseData> productResponseDataList = productService.getAllProducts();
        List<GetAllProductResponseDto> getAllProductResponseDtoList = productDtoMapper.toGetAllProductResponseDtoList(productResponseDataList);
        return getAllProductResponseDtoList;
    }

    @GetMapping("/{pid}")
    public ProductResponseDto getProductByPid(@PathVariable("pid") Integer pid) {
        ProductResponseData productResponseData = productService.getProductByPid(pid);
        ProductResponseDto productResponseDto = productDtoMapper.toProductResponseDto(productResponseData);
        return productResponseDto;
    }

}
