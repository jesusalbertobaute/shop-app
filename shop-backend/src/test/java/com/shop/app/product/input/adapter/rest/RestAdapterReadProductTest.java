
package com.shop.app.product.input.adapter.rest;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.shop.app.product.domain.Product;
import com.shop.app.product.domain.ProductDomain;
import com.shop.app.product.usecase.ReadProductUseCase;

@WebMvcTest(RestAdapterReadProduct.class)
public class RestAdapterReadProductTest {
	
	@MockBean
	private ReadProductUseCase readProductService;
	
	@Autowired
	private MockMvc mvc;
	
	List<Product> products=new ArrayList<>();
	
	@BeforeEach
	void setUp() {
		products.clear();
		products.add(new ProductDomain("1","Milk 1L","D-1",BigDecimal.TEN,LocalDateTime.now()));
	}
	
	
	@Test
	@DisplayName("Unit Test for do request product by code")
	public void findById() throws Exception 
	{
		Mockito.when(readProductService.findByCode("1")).thenReturn(products.get(0));

		mvc.perform(MockMvcRequestBuilders
				.get("/product/search/{id}","1")
				.accept(MediaType.APPLICATION_JSON))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$.code").isNotEmpty())
		.andExpect(MockMvcResultMatchers.jsonPath("$.description").isNotEmpty())
		.andExpect(MockMvcResultMatchers.jsonPath("$.price").isNotEmpty())
		.andExpect(MockMvcResultMatchers.jsonPath("$.creationDate").isNotEmpty());
		
		mvc.perform(MockMvcRequestBuilders
				.get("/product/search/{id}","1")
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.jsonPath("$.creationDate").exists());
		
		
		mvc.perform(MockMvcRequestBuilders
				.get("/product/search/{id}","dddd")
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isNotFound());
	}
}
