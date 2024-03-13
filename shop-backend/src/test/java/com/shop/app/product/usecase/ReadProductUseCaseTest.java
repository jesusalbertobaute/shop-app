package com.shop.app.product.usecase;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.EnabledIf;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;

import com.shop.app.product.domain.Product;
import com.shop.app.product.domain.ProductDomain;
import com.shop.app.product.output.port.LoadProduct;
import com.shop.app.product.usecase.exception.InvalidProductCodeException;

@ExtendWith(MockitoExtension.class)
public class ReadProductUseCaseTest {
	
	@Mock
	private LoadProduct loadDataProduct;
	
	@InjectMocks
	private ReadProductUseCase readProductUseCase;
	
	private Product product;
	
	private static final String PRODUCT_CODE =  "P-10";
	
	@BeforeEach
	void setup() {
		product = ProductDomain.builder()
                .code(PRODUCT_CODE)
                .brandCode("B-1")
                .creationDate(LocalDateTime.now())
                .description("Product Test")
                .price(new BigDecimal("20"))
                .build();
	}
	
	@Test
	@DisplayName("Test Use Case - Find By Code")
	public void findByCode() {
		
		given(loadDataProduct.findByCode(PRODUCT_CODE)).willReturn(product);
		given(loadDataProduct.findByCode("P-2")).willReturn(null);
		Product productTest= readProductUseCase.findByCode(PRODUCT_CODE);
		
		assertTrue(productTest!=null && productTest.getCode().equals(PRODUCT_CODE));
		
		productTest= readProductUseCase.findByCode("P-2");
		assertNull(productTest);
	    
		assertThrows(InvalidProductCodeException.class,()->readProductUseCase.findByCode(""));
		
		assertThrows(InvalidProductCodeException.class,()->readProductUseCase.findByCode(null));

	}

}
