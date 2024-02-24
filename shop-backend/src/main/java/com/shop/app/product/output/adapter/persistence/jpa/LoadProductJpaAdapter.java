package com.shop.app.product.output.adapter.persistence.jpa;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.shop.app.annotation.PersistenceOrm;
import com.shop.app.product.domain.Product;
import com.shop.app.product.output.port.LoadProduct;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@PersistenceOrm
@AllArgsConstructor
@Slf4j
public class LoadProductJpaAdapter implements LoadProduct {
	
	private ProductRepository productRepository;
	
	
	@Override
	public Product findByCode(String code) {
		log.info("LoadProductJpaAdapter-find By {}",code);
		ProductEntity productEntity = productRepository.findByCode(code);
		Product productDomain = null;
		if (productEntity!=null) {
		  productDomain= ProductMapper.INSTANCE.convertFromProductEntityToProduct(productEntity);
		}
		return productDomain;
	}

	@Override
	public List<Product> findByDescription(String description, int page, int quantity) {
		Pageable pageableProduct = getPageableSortByDescription(page,quantity);

		Page<ProductEntity> productEntityPage= productRepository.findByDescriptionLike(description,pageableProduct);
		
		List<ProductEntity> productEntities= productEntityPage.getContent();
		
		List<Product> products = new ArrayList<>();
		
		products.addAll(ProductMapper.INSTANCE.convertFromProductEntitiesToProducts(productEntities));
	
		return products;
	}

	@Override
	public List<Product> findAll(int page, int quantity) {
		
		Pageable pageableProduct = getPageableSortByDescription(page,quantity);

		Page<ProductEntity> productEntityPage= productRepository.findAll(pageableProduct);
		
		List<ProductEntity> productEntities= productEntityPage.getContent();
		
		List<Product> products = new ArrayList<>();
		
		products.addAll(ProductMapper.INSTANCE.convertFromProductEntitiesToProducts(productEntities));
	
		return products;
	}

	@Override
	public List<Product> findByPriceInterval(BigDecimal min, BigDecimal max,int page,int quantity) {
		Pageable pageableProduct = PageRequest.of(page,quantity,Sort.by("description"));
		Page<ProductEntity> productEntityPage = productRepository.findByPriceBetween(min, max, pageableProduct);
		
		List<ProductEntity> productEntities = productEntityPage.getContent();
		
		List<Product> products = new ArrayList<>();
		
		products.addAll(ProductMapper.INSTANCE.convertFromProductEntitiesToProducts(productEntities));
		
		return products;
	}
	
	private Pageable getPageableSortByDescription(int page,int quantity) {
		Pageable pageableProduct = PageRequest.of(page, quantity,Sort.by("description"));
		
		return pageableProduct;
		
	}
}
