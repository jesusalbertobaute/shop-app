package com.shop.app.product.usecase;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.jdbc.SqlConfig.TransactionMode;

import com.shop.app.product.domain.ProductDomain;
import com.shop.app.product.usecase.exception.InvalidProductCodeException;
import com.shop.app.product.usecase.exception.ProductInvalidException;

@SpringBootTest
//@EnabledIfSystemProperty(named = "integrationTestEnabled", matches = "true|TRUE|T|t|y|Y|Yes")
@ActiveProfiles("test")
@Sql(scripts="classpath:test-integration-data.sql",
     config = @SqlConfig(transactionMode = TransactionMode.ISOLATED),
     executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD
     )
@Sql(scripts="classpath:test-integration-delete-data.sql",
    config = @SqlConfig(transactionMode = TransactionMode.ISOLATED),
    executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD
)
public class ManageProductUseCaseIntegrationTest {
	
	@Autowired
	private ManageProductUseCase manageProductUseCase;
	

	@Test
	@DisplayName("Integration Test - Save Product")
	public void save() {
		ProductDomain productDomain= ProductDomain.builder()
				                                  .code("P-10")
				                                  .brandCode("B-1")
				                                  .creationDate(LocalDateTime.now())
				                                  .description("Product Test10")
				                                  .price(new BigDecimal("30.4"))
				                                  .build();
		
		assertTrue(manageProductUseCase.save(productDomain));
	
		
		assertThrows(ProductInvalidException.class,()-> manageProductUseCase.save(null));
		productDomain.setCode("");
		assertThrows(InvalidProductCodeException.class,()-> manageProductUseCase.save(productDomain));
		productDomain.setCode(null);
		assertThrows(InvalidProductCodeException.class,()-> manageProductUseCase.save(productDomain));
	}
	
	@Test
	@DisplayName("Integration Test - Delete Product by Code")
	public void deleteByCode() {
		assertTrue(manageProductUseCase.deleteByCode("P-12"));
		
		assertThrows(InvalidProductCodeException.class,()-> manageProductUseCase.deleteByCode(""));
	}
	
	@Test
	@DisplayName("Integration Test - Delete Product")
	public void deleteByProduct() {
		ProductDomain productDomain= ProductDomain.builder()
                .code("P-10")
                .brandCode("B-1")
                .creationDate(LocalDateTime.now())
                .description("Product Test10")
                .price(new BigDecimal("30.4"))
                .build();

		assertTrue(manageProductUseCase.save(productDomain),"Product can not be saved, unit test abort");
		
		assertTrue(manageProductUseCase.delete(productDomain));
		
		assertThrows(ProductInvalidException.class,()-> manageProductUseCase.delete(null));
		
		productDomain.setCode(null);
		assertThrows(InvalidProductCodeException.class,()-> manageProductUseCase.delete(productDomain));
		
		productDomain.setCode("");
		assertThrows(InvalidProductCodeException.class,()-> manageProductUseCase.delete(productDomain));
	}
	
}
