package it.worldpay.fede.offersmanager.utils;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.ObjectMapper;

import it.worldpay.fede.offersmanager.model.Product;

public class TestUtils {
	
	
	@Autowired
    ObjectMapper objectMapper;
	 
	public static String asJsonString(final Object obj) {
	        try {
	            return new ObjectMapper().writeValueAsString(obj);
	        } catch (Exception e) {
	            throw new RuntimeException(e);
	        }
	    }
	
	public static void changeProductForBadRequest(Product product){
		product.setDaysValidityPeriod(0);
	}
}
