package com.shop.app.product.output.adapter.persistence.jpa;

import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.util.ObjectUtils;

import com.shop.app.annotation.PersistenceOrm;
import com.shop.app.product.domain.Product;
import com.shop.app.product.output.port.PersistenceProduct;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@PersistenceOrm
@AllArgsConstructor
@Slf4j
public class PersistenceProductJpaAdapter implements PersistenceProduct {
	
	private ProductRepository productRepository;

	@Override
	public boolean saveProduct(Product product) {
		if (product==null) return false;
		
		ProductEntity productEntity = ProductMapper.INSTANCE.convertFromProductToProductEntity(product);
		
		try {
			productRepository.save(productEntity);
		}catch(IllegalArgumentException | OptimisticLockingFailureException exception) {
			log.error(exception.getMessage(), exception);
			return false;
		}
		
		return true;
	}

	@Override
	public boolean deleteProduct(Product product) {
		if (product==null) return false;

		ProductEntity productEntity = ProductMapper.INSTANCE.convertFromProductToProductEntity(product);
		
		try {
			productRepository.delete(productEntity);
		}catch(IllegalArgumentException | OptimisticLockingFailureException exception) {
			log.error(exception.getMessage(), exception);
			return false;
		}
		return true;
	}

	@Override
	public boolean deleteProduct(String codeProduct) {
		if (ObjectUtils.isEmpty(codeProduct)) return false;
		
		productRepository.deleteById(codeProduct);
		
		return true;
	}
	
	

}
