package it.worldpay.fede.offersmanager.controllers;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import it.worldpay.fede.offersmanager.model.Product;
import it.worldpay.fede.offersmanager.services.BaseService;

@RestController
@EnableAutoConfiguration
@RequestMapping(value="/offers")
public class OffersManagerController {
	
	@Autowired
	BaseService baseService;
	
	
	///########################## PRODUCT ############################################
	
	@RequestMapping(value="/getProduct/{productId}", method = RequestMethod.GET)
	public Product getProduct(@PathVariable("productId") Long productId) {
        return baseService.getProduct(productId);
    }
	
	@RequestMapping(value="/deleteProduct", method = RequestMethod.DELETE)
	public void deleteProduct(@Valid @RequestBody Product product, HttpServletResponse response)
	{
		 baseService.deleteProduct(product);
		 response.setStatus(HttpStatus.OK.value());
	}
	
	@RequestMapping(value="/saveProduct", method = RequestMethod.POST)
	public void saveProduct(@Valid @RequestBody Product product, HttpServletResponse response){
		baseService.saveProduct(product);
		response.setStatus(HttpStatus.CREATED.value());
	}
	
	
	
	///########################## GELATO #############################################
	
	///########################## PIZZA ##############################################
		
	///########################## PASTA ##############################################
		
	///########################## MOUNTAIN BIKE#######################################
	
	///########################## ROAD BIKE ##########################################
	
	///########################## HAND BOOK ##########################################
	
	///########################## FANTASY BOOK########################################
		

}
