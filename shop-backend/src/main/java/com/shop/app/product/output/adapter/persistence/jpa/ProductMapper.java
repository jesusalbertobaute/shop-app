package com.shop.app.product.output.adapter.persistence.jpa;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.shop.app.brand.output.adapter.persistence.jpa.BrandMapper;
import com.shop.app.product.domain.ProductDomain;

@Mapper(uses= {BrandMapper.class})
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);
    
    @Mapping(source="brandCode",target="brand.code")
    ProductEntity convertFromProductToProductEntity(ProductDomain product);
    
    @Mapping(source="brand.code",target="brandCode")
    ProductDomain convertFromProductEntityToProduct(ProductEntity product);
    
    List<ProductEntity> convertFromProductsToProductEntities(List<ProductDomain> products);
    
    List<ProductDomain> convertFromProductEntitiesToProducts(List<ProductEntity> productEntities);
}
