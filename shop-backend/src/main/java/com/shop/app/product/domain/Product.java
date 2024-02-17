package com.shop.app.product.domain;

import java.math.BigDecimal;

public interface Product {
	
	String getCode();
	void setCode(String id);
	
	String getDescription();
	void setDescription(String description);
	
	String getBrandCode();
	void setBrandCode(String brand);
	
	BigDecimal getPrice();
	void setPrice(BigDecimal price);
}
