package com.prowareness.example.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.prowareness.example.domain.ProductDetail;

@Repository
public interface ProductDetailRepository extends PagingAndSortingRepository<ProductDetail, String> {
}
