package com.shop.app.product.output.adapter.persistence.jpa;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.shop.app.brand.output.adapter.persistence.jpa.BrandEntity;

@DataJpaTest
public class ProductRepositoryTest {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
    private TestEntityManager entityManager;
	
	private BrandEntity brandEntity;
	
	
	@BeforeEach
	public void setup() {
		brandEntity= entityManager.persist(new BrandEntity("TEST-1","Brand Test"));
		 
	}
	
	@Test
    @DisplayName("Unit test for save product operation")
    public void givenProduct_whenSave_thenReturnSaveProduct() {
		
		ProductEntity productEntity = new ProductEntity("P-1","Product Test",new BigDecimal("3.12"),LocalDateTime.now(),brandEntity);

		ProductEntity newProduct = productRepository.save(productEntity);
		assertNotNull(newProduct);
	}
	
	@Test
    @DisplayName("Unit test for save null product or product with null code")
    public void givenProduct_whenSaveProductNotValid_thenThrowException() {
		
		ProductEntity productEntity = new ProductEntity(null,"Product Test",new BigDecimal("3.12"),LocalDateTime.now(),brandEntity);

		assertThrows(RuntimeException.class,() -> productRepository.save(productEntity));
		assertThrows(RuntimeException.class,() -> productRepository.save(null));
		
	}
	
	@Test
    @DisplayName("Unit test for find products by description like")
    public void givenProductDescription_whenFindByDescriptionLike_thenReturnProductList() {
		Pageable pageableProduct= PageRequest.of(0, 10,Sort.by("description"));
		
		ProductEntity productEntity1 = new ProductEntity("P-1","Product Test1",new BigDecimal("3.12"),LocalDateTime.now(),brandEntity);
		ProductEntity productEntity2 = new ProductEntity("P-2","Product Test2",new BigDecimal("20"),LocalDateTime.now(),brandEntity);
		
		
		productRepository.save(productEntity1);
		productRepository.save(productEntity2);
		
		Page<ProductEntity> productPage=  productRepository.findByDescriptionLike("Product%", pageableProduct);
		
		assertNotNull(productPage);
		assertFalse(productPage.getContent().isEmpty());
		
        productPage=  productRepository.findByDescriptionLike("%Milk%", pageableProduct);
		
		assertNotNull(productPage);
		assertTrue(productPage.getContent().isEmpty());
	}
	
	@Test
    @DisplayName("Unit test for find a product by code")
    public void givenProductCode_whenFindByCode_thenReturnProduct() {
		ProductEntity productEntity1 = new ProductEntity("P-1","Product Test1",new BigDecimal("3.12"),LocalDateTime.now(),brandEntity);
	
		productRepository.save(productEntity1);
		
		ProductEntity productPage=  productRepository.findByCode("P-1");
		
		assertNotNull(productPage);
		
		productPage=  productRepository.findByCode("CODETEST");
		assertNull(productPage);
		
	}
	
	@Test
    @DisplayName("Unit test for find products by interval price")
    public void givenProductPriceInterval_whenFindByIntervalPrice_thenReturnProductList() {
		Pageable pageableProduct= PageRequest.of(0, 10,Sort.by("description"));
		
		ProductEntity productEntity1 = new ProductEntity("P-1","Product Test1",new BigDecimal("3.12"),LocalDateTime.now(),brandEntity);
		ProductEntity productEntity2 = new ProductEntity("P-2","Product Test2",new BigDecimal("20"),LocalDateTime.now(),brandEntity);
		ProductEntity productEntity3 = new ProductEntity("P-3","Product Test3",new BigDecimal("15"),LocalDateTime.now(),brandEntity);
		ProductEntity productEntity4 = new ProductEntity("P-4","Product Test4",new BigDecimal("11"),LocalDateTime.now(),brandEntity);
		
		productRepository.save(productEntity1);
		productRepository.save(productEntity2);
		productRepository.save(productEntity3);
		productRepository.save(productEntity4);
		
		Page<ProductEntity> productPage=  productRepository.findByPriceBetween(new BigDecimal("10"), new BigDecimal("16"), pageableProduct);
		
		assertNotNull(productPage);
		assertFalse(productPage.getContent().isEmpty());
		assertTrue(productPage.getContent().size()==2);
		assertTrue(productPage.getContent().stream().anyMatch(product -> product.getCode().equals("P-3")));
		assertTrue(productPage.getContent().stream().anyMatch(product -> product.getCode().equals("P-4")));
		
	}
	
	
	

}
