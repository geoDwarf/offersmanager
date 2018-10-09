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

import it.worldpay.fede.offersmanager.model.books.FantasyBook;
import it.worldpay.fede.offersmanager.services.FantasyBookService;


@RestController
@EnableAutoConfiguration
@RequestMapping(value="/fantasyBook")
public class FantasyBookController {
	
	@Autowired
	FantasyBookService fantasyBookService;
	
	@RequestMapping(value="/saveFantasyBook", method = RequestMethod.POST)
	public void saveFantasyBook(@Valid @RequestBody FantasyBook fantasyBook, HttpServletResponse response){
		fantasyBookService.saveFantasyBook(fantasyBook);
		response.setStatus(HttpStatus.CREATED.value());
	}
	
//	@RequestMapping(value="/getFantasyBook/{productId}", method = RequestMethod.GET)
//	public FantasyBook getFantasyBook(@PathVariable("productId") Long productId) {
//        return fantasyBookService.getFantasyBook(productId);
//    }
	
//	@RequestMapping(value="/deleteFantasyBook", method = RequestMethod.DELETE)
//	public void deleteGelato(@Valid @RequestBody FantasyBook fantasyBook, HttpServletResponse response)
//	{
//		 fantasyBookService.deleteFantasyBook(fantasyBook);
//		 response.setStatus(HttpStatus.OK.value());
//		 
//	}

	

}
