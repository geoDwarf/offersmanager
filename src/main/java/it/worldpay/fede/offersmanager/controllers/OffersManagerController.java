package it.worldpay.fede.offersmanager.controllers;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import it.worldpay.fede.offersmanager.model.food.Gelato;
import it.worldpay.fede.offersmanager.services.GelatoService;

@RestController
@EnableAutoConfiguration
@RequestMapping(value="/offers")
public class OffersManagerController {
	
	@Autowired
	GelatoService gelatoService;
	
	@RequestMapping(value="/saveGelato", method = RequestMethod.POST)
	public void saveGelatoOffer(@RequestBody Gelato gelato, HttpServletResponse response){
		gelatoService.saveGelato(gelato);
		response.setStatus(HttpStatus.CREATED.value());
	}

}
