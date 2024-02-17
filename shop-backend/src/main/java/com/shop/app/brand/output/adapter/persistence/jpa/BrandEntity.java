package com.shop.app.brand.output.adapter.persistence.jpa;

import com.shop.app.brand.domain.Brand;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="brand")
public class BrandEntity implements Brand {
	
	@Id
	private String code;
	
	private String description;

}
