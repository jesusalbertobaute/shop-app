package com.shop.app.product.usecase;

import java.math.BigDecimal;
import java.util.List;

import com.shop.app.annotation.UseCase;
import com.shop.app.product.domain.Product;
import com.shop.app.product.input.port.ReadProduct;
import com.shop.app.product.output.port.LoadProduct;

import lombok.AllArgsConstructor;

@UseCase
@AllArgsConstructor
public class ReadProductUseCase implements ReadProduct {
	
	private LoadProduct loadDataProduct;

	@Override
	public Product findByCode(String code) {
		Product product= loadDataProduct.findByCode(code);
		return product;
	}

	@Override
	public List<Product> findByDescription(String description,int page,int quantity) {
		 List<Product> products= loadDataProduct.findByDescription(description,page-1, quantity);
		 return products;
	}

	@Override
	public List<Product> findAll(int page, int quantity) {
	    List<Product> products= loadDataProduct.findAll(page-1, quantity);
	    
		return products;
	}

	@Override
	public List<Product> findByPriceInterval(BigDecimal min, BigDecimal max,int page,int quantity) {
		 List<Product> products= loadDataProduct.findByPriceInterval(min, max,page-1, quantity);
		 return products;
	}

}
