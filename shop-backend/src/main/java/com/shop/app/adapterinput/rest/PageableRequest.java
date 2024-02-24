package com.shop.app.adapterinput.rest;

import jakarta.validation.constraints.Min;

public record PageableRequest(@Min(value=1,message="Min Value of Page is 1") int page,
		                      @Min(value=1,message="Min Value of Quantity is 1") int quantity) {}
