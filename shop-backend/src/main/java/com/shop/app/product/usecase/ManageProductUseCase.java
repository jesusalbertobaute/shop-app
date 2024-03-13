package com.shop.app.product.usecase;

import org.springframework.util.ObjectUtils;

import com.shop.app.annotation.UseCase;
import com.shop.app.product.domain.Product;
import com.shop.app.product.domain.ProductDomain;
import com.shop.app.product.input.port.ManageProduct;
import com.shop.app.product.output.adapter.persistence.jpa.ProductMapper;
import com.shop.app.product.output.port.PersistenceProduct;
import com.shop.app.product.usecase.exception.InvalidProductCodeException;
import com.shop.app.product.usecase.exception.ProductInvalidException;

import lombok.AllArgsConstructor;

@UseCase
@AllArgsConstructor
public class ManageProductUseCase implements ManageProduct {
	
	private PersistenceProduct persistenceProduct;

	@Override
	public boolean save(Product product) throws InvalidProductCodeException,ProductInvalidException{
		if (product==null) throw new ProductInvalidException();
		
		if (ObjectUtils.isEmpty(product.getBrandCode())) throw new ProductInvalidException("A product has to have a brand");
			
		if (ObjectUtils.isEmpty(product.getCode())) throw new InvalidProductCodeException();

		ProductDomain productEntity = ProductMapper.INSTANCE.convertFromProductToProductDomain(product);
		
		return persistenceProduct.saveProduct(productEntity);
	}

	@Override
	public boolean deleteByCode(String codeProduct) throws InvalidProductCodeException {
		if (ObjectUtils.isEmpty(codeProduct)) throw new InvalidProductCodeException();
		
		return persistenceProduct.deleteProduct(codeProduct);
	}

	@Override
	public boolean delete(Product product) throws InvalidProductCodeException,ProductInvalidException {
        if (product==null) throw new ProductInvalidException();
			
		if (ObjectUtils.isEmpty(product.getCode())) throw new InvalidProductCodeException();

		ProductDomain productEntity = ProductMapper.INSTANCE.convertFromProductToProductDomain(product);

		return persistenceProduct.deleteProduct(productEntity);
	}

}
