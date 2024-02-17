package com.shop.app.brand.output.adapter.persistence.jpa;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.shop.app.brand.domain.BrandDomain;

@Mapper
public interface BrandMapper {
	BrandMapper INSTANCE = Mappers.getMapper(BrandMapper.class);
	
	BrandEntity convertFromBrandToBrandEntity(BrandDomain brand);
	
	BrandDomain convertFromBrandEntityToBrand(BrandEntity brand);
	
	List<BrandEntity> convertFromBrandsToBrandEntities(List<BrandDomain> brands);
	
	List<BrandDomain> convertFromBrandEntitiesToBrands(List<BrandEntity> brands);

}
