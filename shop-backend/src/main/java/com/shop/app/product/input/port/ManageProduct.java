package com.shop.app.product.input.port;

import com.shop.app.product.domain.Product;

public interface ManageProduct {
	
	boolean save(Product product);
	
	boolean deleteByCode(String codeProduct);
	
	boolean delete(Product product);

}
