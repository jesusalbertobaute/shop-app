package com.shop.app.product.output.adapter.persistence.jpa;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.doThrow;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.OptimisticLockingFailureException;

import com.shop.app.brand.output.adapter.persistence.jpa.BrandEntity;
import com.shop.app.product.domain.Product;
import com.shop.app.product.domain.ProductDomain;

@ExtendWith(MockitoExtension.class)
public class PersistenceProductJpaAdapterTest {

	@Mock
	private ProductRepository productRepository;
	
	@InjectMocks
	private PersistenceProductJpaAdapter persistenceProductJpaAdapter;

	@Test
	@DisplayName("Test Unit - Save Product")
	public void saveProduct() {
		ProductDomain productDomain= ProductDomain.builder()
                .code("P-10")
                .brandCode("B-1")
                .creationDate(LocalDateTime.now())
                .description("Product Test10")
                .price(new BigDecimal("30.4"))
                .build();
		
		ProductEntity productEntity = ProductMapper.INSTANCE.convertFromProductToProductEntity(productDomain);
		
		given(productRepository.save(productEntity)).willReturn(productEntity);
		
		assertTrue(persistenceProductJpaAdapter.saveProduct(productEntity));
		
		assertFalse(persistenceProductJpaAdapter.saveProduct(null));
		
        given(productRepository.save(productEntity)).willThrow(OptimisticLockingFailureException.class);
		
		assertFalse(persistenceProductJpaAdapter.saveProduct(productEntity));
	}
	
	@Test
	@DisplayName("Test Unit - Delete Product")
	public void deleteProduct() {
		ProductDomain productDomain= ProductDomain.builder()
                .code("P-10")
                .brandCode("B-1")
                .creationDate(LocalDateTime.now())
                .description("Product Test10")
                .price(new BigDecimal("30.4"))
                .build();
		
		
		assertTrue(persistenceProductJpaAdapter.deleteProduct(productDomain));
		
		ProductEntity productEntity = ProductMapper.INSTANCE.convertFromProductToProductEntity(productDomain);
	
		doThrow(OptimisticLockingFailureException.class).when(productRepository).delete(productEntity);
		
		assertFalse(persistenceProductJpaAdapter.deleteProduct(productDomain));
	}
	
	@Test
	@DisplayName("Test Unit - Delete Product By Code")
	public void deleteProductByCode() {
		assertTrue(persistenceProductJpaAdapter.deleteProduct("P-10"));
		
		assertFalse(persistenceProductJpaAdapter.deleteProduct(""));
	}

}
