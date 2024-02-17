package com.shop.app.product.input.adapter.rest;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shop.app.product.domain.Product;
import com.shop.app.product.usecase.ReadProductUseCase;

@RestController
@RequestMapping("/product")
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
		return new ResponseEntity<List<Product>>(products,HttpStatus.OK);
	}
	
	@GetMapping("/search")
	public ResponseEntity<List<Product>> findByPage(@RequestParam("page") int page,@RequestParam("quant") int quantity){
		List<Product> products= readProductService.findAll(page,quantity);
		return new ResponseEntity<List<Product>>(products,HttpStatus.OK);
	}
	
	@GetMapping("/search-price")
	public ResponseEntity<List<Product>> findByPriceInterval(@RequestParam("min") BigDecimal min,@RequestParam("max") BigDecimal max){
		List<Product> products= readProductService.findByPriceInterval(min,max);
		return new ResponseEntity<List<Product>>(products,HttpStatus.OK);
	}
	

}
