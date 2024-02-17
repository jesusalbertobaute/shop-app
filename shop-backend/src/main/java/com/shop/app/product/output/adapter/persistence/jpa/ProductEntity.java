package com.shop.app.product.output.adapter.persistence.jpa;

import java.math.BigDecimal;

import com.shop.app.brand.output.adapter.persistence.jpa.BrandEntity;
import com.shop.app.product.domain.Product;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="product")
public class ProductEntity implements Product{
		
	@Id
	private String code;
	
	private String description;
	
	private BigDecimal price;
	
	@ManyToOne
	@JoinColumn(name="brand_code")
	private BrandEntity brand;
	
	public String getBrandCode() {
		if (brand==null) {
			return null;
		}
		
		return brand.getCode();
	}
	
	public void setBrandCode(String code) {
		if (brand!=null) {
		   brand.setCode(code);
		}
	}
}
