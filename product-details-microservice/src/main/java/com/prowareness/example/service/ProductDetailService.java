package com.prowareness.example.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.prowareness.example.domain.ProductDetail;
import com.prowareness.example.exception.ProductNotFoundException;

public interface ProductDetailService {

	ProductDetail save(ProductDetail productDetail);

    List<ProductDetail> getAllProductDetail();
    
    Page<ProductDetail> getAllProductDetail(PageRequest pageRequest);
    
    ProductDetail getProductDetail(String id) throws ProductNotFoundException;
    
    void deleteProductDetail(String id);

}
