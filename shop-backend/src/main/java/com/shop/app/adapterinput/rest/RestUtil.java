package com.shop.app.adapterinput.rest;

import java.util.Arrays;
import java.util.Set;

import org.springframework.web.context.request.WebRequest;

public class RestUtil {

	public static final boolean validateParameters(WebRequest request,String...validInputParameters) { 
		
		Set<String> requestParameters = request.getParameterMap().keySet();
		
		boolean validParameters = validInputParameters.length == requestParameters.size() && 
				                  Arrays.stream(validInputParameters).allMatch(parameter-> requestParameters.contains(parameter));;
		
		return validParameters;
	}
}
