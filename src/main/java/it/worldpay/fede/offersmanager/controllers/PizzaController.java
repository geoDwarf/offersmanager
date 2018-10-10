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

import it.worldpay.fede.offersmanager.model.food.Gelato;
import it.worldpay.fede.offersmanager.model.food.Pizza;
import it.worldpay.fede.offersmanager.services.PizzaService;



@RestController
@EnableAutoConfiguration
@RequestMapping(value="/pizza")
public class PizzaController {
	
	
//	@Autowired
//	PizzaService pizzaService;
//	
//	@RequestMapping(value="/savePizza", method = RequestMethod.POST)
//	public void savePizza(@Valid @RequestBody Pizza pizza, HttpServletResponse response){
//		pizzaService.savePizza(pizza);
//		response.setStatus(HttpStatus.CREATED.value());
//	}
//	
//	@RequestMapping(value="/getPizza/{productId}", method = RequestMethod.GET)
//	public Pizza getPizza(@PathVariable("productId") Long productId) {
//        return pizzaService.getPizza(productId);
//    }
	
//	@RequestMapping(value="/deletePizza", method = RequestMethod.DELETE)
//	public void deletePizza(@Valid @RequestBody Pizza pizza, HttpServletResponse response)
//	{
//		 pizzaService.deletePizza(pizza);
//		 response.setStatus(HttpStatus.OK.value());
//		 
//	}
}
