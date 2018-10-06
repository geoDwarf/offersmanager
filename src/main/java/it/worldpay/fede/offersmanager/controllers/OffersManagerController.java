package it.worldpay.fede.offersmanager.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import it.worldpay.fede.offersmanager.model.Product;
import it.worldpay.fede.offersmanager.services.PizzaService;
import it.worldpay.fede.offersmanager.services.ProductService;

@RestController
@EnableAutoConfiguration
@RequestMapping(value="/offers")
public class OffersManagerController {
	
	
	
	@Autowired
	ProductService productService;
	
	@Autowired
	PizzaService pizzaService;
	
	///########################## GELATO ##############################################
	
	///########################## PIZZA ##############################################
	
	///########################## PASTA ##############################################
	
	///########################## MOUNTAIN BIKE#######################################
	
	///########################## PRODUCT ############################################
	
	@RequestMapping(value="/getProduct/{productId}", method = RequestMethod.GET)
	public Product getProduct(@PathVariable("productId") Long productId) {
        return productService.getProduct(productId);
    }
	}


