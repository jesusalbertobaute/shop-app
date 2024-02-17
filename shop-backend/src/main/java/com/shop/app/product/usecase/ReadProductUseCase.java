package com.shop.app.product.usecase;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.shop.app.anotation.UseCase;
import com.shop.app.product.domain.Product;
import com.shop.app.product.domain.ProductDomain;
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
	    List<Product> products= new ArrayList<>();
	    
	    products.add(new ProductDomain("1","Milk 1L","D-1",BigDecimal.TEN,LocalDateTime.now()));
		return products;
	}

	@Override
	public List<Product> findAll(int page, int quantity) {
	    List<Product> products= loadDataProduct.findAll(page-1, quantity);
	    
	   /* products.add(new ProductDomain("1","Milk 1L",new BigDecimal("3.5"),500));
	    products.add(new ProductDomain("2","Flour 1K",new BigDecimal("1.43"),300));
	    products.add(new ProductDomain("3","Rice 1K",new BigDecimal("2.43"),460));*/
		return products;
	}

	@Override
	public List<Product> findByPriceInterval(BigDecimal min, BigDecimal max) {
	    List<Product> products= new ArrayList<>();
	    
	    products.add(new ProductDomain("39484828","Milk 1L","D-1",BigDecimal.TEN,LocalDateTime.now()));
		return products;
	}

}
