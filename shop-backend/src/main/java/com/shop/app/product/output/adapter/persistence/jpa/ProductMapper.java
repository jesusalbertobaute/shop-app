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
  //  @Mapping(target = "creationDate", expression = "java(LocalDateTime.of(source.getCreationDate().toLocalDate(), source.getCreationDate().toLocalTime()))")
    @Mapping(source="creationDate", target = "creationDate",dateFormat="yyyy-MM-dd'T'HH:mm:ss")
    ProductEntity convertFromProductToProductEntity(ProductDomain product);
    
    @Mapping(source="brand.code",target="brandCode")
    //@Mapping(target = "creationDate", expression = "java(LocalDateTime.of(source.getCreationDate().toLocalDate(), source.getCreationDate().toLocalTime()))")
    @Mapping(source="creationDate", target = "creationDate",dateFormat="yyyy-MM-dd'T'HH:mm:ss") //Esto Funciona
    ProductDomain convertFromProductEntityToProduct(ProductEntity product);
    
    List<ProductEntity> convertFromProductsToProductEntities(List<ProductDomain> products);
    
    List<ProductDomain> convertFromProductEntitiesToProducts(List<ProductEntity> productEntities);
}
