package com.shop.app.product.input.adapter.rest;

import java.util.List;

import com.shop.app.product.domain.Product;

public record ProductResponse(String message,List<Product> products) {

}
