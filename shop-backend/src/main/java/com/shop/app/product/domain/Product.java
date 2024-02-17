package com.shop.app.product.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface Product {
	
	String getCode();
	void setCode(String id);
	
	String getDescription();
	void setDescription(String description);
	
	String getBrandCode();
	void setBrandCode(String brand);
	
	BigDecimal getPrice();
	void setPrice(BigDecimal price);
	
	LocalDateTime getCreationDate();
	void setCreationDate(LocalDateTime creationDate);
	
}
