package com.shop.app.product.input.adapter.rest;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.shop.app.adapterinput.rest.PageableRequest;
import com.shop.app.adapterinput.rest.RestUtil;
import com.shop.app.product.domain.Product;
import com.shop.app.product.usecase.ReadProductUseCase;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/product")
@Slf4j
public class RestAdapterReadProduct {
	
	private ReadProductUseCase readProductService;
	
	public RestAdapterReadProduct(ReadProductUseCase readProductService) {
		this.readProductService= readProductService;
	}
	
	@GetMapping("/search/{id}")
	public ResponseEntity<Product> findById(@PathVariable("id")  String id){
		Product product= readProductService.findByCode(id);
		
		if (product==null) {
			return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Product>(product,HttpStatus.OK);
	}
	
	@GetMapping("/search-name/{description}")
	public ResponseEntity<List<Product>> findByDescription(@PathVariable("description") String description){
		List<Product> products= readProductService.findByDescription(description,1,10);
		
		if (products.isEmpty()) {
			return new ResponseEntity<List<Product>>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<List<Product>>(products,HttpStatus.OK);
	}
	
	@GetMapping("/search")
	public ResponseEntity<ProductResponse> findAll(PageableRequest pageableRequest,WebRequest request){
		log.info("Searching Products");
		
		if (!RestUtil.validateParameters(request, "page","quantity")) {
			return new ResponseEntity<ProductResponse>(new ProductResponse("Only is acceptable page and quantity parameters",Collections.emptyList()),HttpStatus.BAD_REQUEST);
		}
		
		List<Product> products= readProductService.findAll(pageableRequest.page(),pageableRequest.quantity());
		
		if (products.isEmpty()) {
			return new ResponseEntity<ProductResponse>(new ProductResponse("Do not exist any product",Collections.emptyList()),HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<ProductResponse>(new ProductResponse(String.format("It has found %d products in page %d", products.size(),pageableRequest.page()),products),HttpStatus.OK);
	}
	
	@GetMapping("/search-price")
	public ResponseEntity<List<Product>> findByPriceInterval(@RequestParam("min") BigDecimal min,@RequestParam("max") BigDecimal max,
			                                                 @RequestParam("page") int page,@RequestParam("quantity") int quantity){
		log.info("Searching By Price between {}-{} ",min,max);
		List<Product> products= readProductService.findByPriceInterval(min,max,page,quantity);
		if (products.isEmpty()) {
			return new ResponseEntity<List<Product>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Product>>(products,HttpStatus.OK);
	}


}
