package com.shop.app.product.output.port;

import com.shop.app.product.domain.Product;

public interface PersistenceProduct {
	boolean saveProduct(Product product);
	boolean updateProduct(Product product);
	boolean deleteProduct(Product product);
}
