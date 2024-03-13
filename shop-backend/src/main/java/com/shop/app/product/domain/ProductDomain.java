package com.shop.app.product.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDomain implements Product{
	
	private String code;
	
	private String description;
	
	private String brandCode;
	
	private BigDecimal price;
	
	private LocalDateTime creationDate;
}
