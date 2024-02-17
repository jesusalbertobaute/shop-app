package com.shop.app;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import com.shop.app.product.input.adapter.rest.RestAdapterReadProduct;

@SpringBootTest
class ShopAppApplicationTests {
	
	@Autowired
	private RestAdapterReadProduct restAdapterReadProduct;
	

	@Test
	void contextLoads() {
		Assert.notNull(restAdapterReadProduct,"RestAdapterReadProduct is Null");
	}

}
