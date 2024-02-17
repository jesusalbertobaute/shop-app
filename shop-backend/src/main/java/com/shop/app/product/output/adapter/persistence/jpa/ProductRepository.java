package com.shop.app.product.output.adapter.persistence.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity,String>{

	Page<ProductEntity> findByDescriptionLike(String description,Pageable pageableProduct);

}
