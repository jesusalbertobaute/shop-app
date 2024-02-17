package com.shop.app.product.input.port;

import java.math.BigDecimal;
import java.util.List;

import com.shop.app.product.domain.Product;

public interface ReadProduct {
	Product findByCode(String code);
	
	List<Product> findByDescription(String description,int page,int quantity);
	
	List<Product> findAll(int page,int quantity);
	
	List<Product> findByPriceInterval(BigDecimal min,BigDecimal max);
}
