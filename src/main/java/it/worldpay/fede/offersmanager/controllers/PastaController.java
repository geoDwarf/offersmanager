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

import it.worldpay.fede.offersmanager.model.food.Pasta;
import it.worldpay.fede.offersmanager.services.PastaService;

@RestController
@EnableAutoConfiguration
@RequestMapping(value="/pasta")
public class PastaController {
	
//	@Autowired
//	PastaService pastaService;
//	
//	@RequestMapping(value="/savePasta", method = RequestMethod.POST)
//	public void savePasta(@Valid @RequestBody Pasta pasta, HttpServletResponse response){
//		pastaService.savePasta(pasta);
//		response.setStatus(HttpStatus.CREATED.value());
//	}
	
//	@RequestMapping(value="/getPasta/{productId}", method = RequestMethod.GET)
//	public Pasta getPasta(@PathVariable("productId") Long productId) {
//        return pastaService.getPasta(productId);
//    }
	
//	@RequestMapping(value="/deletePasta", method = RequestMethod.DELETE)
//	public void deleteGelato(@Valid @RequestBody Pasta pasta, HttpServletResponse response)
//	{
//		 pastaService.deletePasta(pasta);
//		 response.setStatus(HttpStatus.OK.value());
//		 
//	}

}
