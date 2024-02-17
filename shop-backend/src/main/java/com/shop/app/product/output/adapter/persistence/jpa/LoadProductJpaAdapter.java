package com.shop.app.product.output.adapter.persistence.jpa;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.shop.app.anotation.PersistenceOrm;
import com.shop.app.product.domain.Product;
import com.shop.app.product.output.port.LoadProduct;

import lombok.AllArgsConstructor;

@PersistenceOrm
@AllArgsConstructor
public class LoadProductJpaAdapter implements LoadProduct {
	
	private ProductRepository productRepository;
	
	
	@Override
	public Product findByCode(String code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> findByDescription(String description, int page, int quantity) {
		Pageable pageableProduct = getPageableByDescription(page,quantity);

		Page<ProductEntity> productEntityPage= productRepository.findByDescriptionLike(description,pageableProduct);
		
		List<ProductEntity> productEntities= productEntityPage.getContent();
		
		List<Product> products = new ArrayList<>();
		
		products.addAll(ProductMapper.INSTANCE.convertFromProductEntitiesToProducts(productEntities));
	
		return products;
	}

	@Override
	public List<Product> findAll(int page, int quantity) {
		
		Pageable pageableProduct = getPageableByDescription(page,quantity);

		Page<ProductEntity> productEntityPage= productRepository.findAll(pageableProduct);
		
		List<ProductEntity> productEntities= productEntityPage.getContent();
		
		List<Product> products = new ArrayList<>();
		
		products.addAll(ProductMapper.INSTANCE.convertFromProductEntitiesToProducts(productEntities));
	
		return products;
	}

	@Override
	public List<Product> findByPriceInterval(BigDecimal min, BigDecimal max) {
		// TODO Auto-generated method stub
		return null;
	}

	private Pageable getPageableByCode(int page,int quantity) {
		Pageable pageableProduct = PageRequest.of(page, quantity,Sort.by("code"));
		
		return pageableProduct;
		
	}
	
	private Pageable getPageableByDescription(int page,int quantity) {
		Pageable pageableProduct = PageRequest.of(page, quantity,Sort.by("description"));
		
		return pageableProduct;
		
	}
}
