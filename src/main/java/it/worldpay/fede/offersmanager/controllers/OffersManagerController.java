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
import it.worldpay.fede.offersmanager.model.food.Gelato;
import it.worldpay.fede.offersmanager.services.GelatoService;
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
	
	@Autowired
	GelatoService gelatoService;
	
	
	///########################## PRODUCT ############################################
	
	@RequestMapping(value="/getProduct/{productId}", method = RequestMethod.GET)
	public Product getProduct(@PathVariable("productId") Long productId) {
        return productService.getProduct(productId);
    }
	
	@RequestMapping(value="/deleteProduct/{productId}", method = RequestMethod.DELETE)
	public void deleteProduct(@Valid @RequestBody Product product, HttpServletResponse response)
	{
		 productService.deleteProduct(product);
		 response.setStatus(HttpStatus.OK.value());
	}
	
	///########################## GELATO ##############################################
	
	@RequestMapping(value="/saveGelato", method = RequestMethod.POST)
	public void saveGelato(@Valid @RequestBody Gelato gelato, HttpServletResponse response){
		gelatoService.saveGelato(gelato);
		response.setStatus(HttpStatus.CREATED.value());
	}
	
	///########################## PIZZA ##############################################
		
	///########################## PASTA ##############################################
		
	///########################## MOUNTAIN BIKE#######################################
	
	///########################## ROAD BIKE ##########################################
	
	///########################## HAND BOOK ##########################################
	
	///########################## FANTASY BOOK########################################
		

}
