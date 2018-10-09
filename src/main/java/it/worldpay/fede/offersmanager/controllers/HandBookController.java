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

import it.worldpay.fede.offersmanager.model.books.HandBook;
import it.worldpay.fede.offersmanager.services.HandBookService;

@RestController
@EnableAutoConfiguration
@RequestMapping(value="/handBook")
public class HandBookController {
	
	@Autowired
	HandBookService handBookService;
	
	@RequestMapping(value="/saveHandBook", method = RequestMethod.POST)
	public void saveHandBook(@Valid @RequestBody HandBook handBook, HttpServletResponse response){
		handBookService.saveHandBook(handBook);
		response.setStatus(HttpStatus.CREATED.value());
	}
	
//	@RequestMapping(value="/getHandBook/{productId}", method = RequestMethod.GET)
//	public HandBook getHandBook(@PathVariable("productId") Long productId) {
//        return handBookService.getHandBook(productId);
//    }

//	@RequestMapping(value="/deleteHandBook", method = RequestMethod.DELETE)
//	public void deleteGelato(@Valid @RequestBody HandBook handBook, HttpServletResponse response)
//	{
//		 handBookService.deleteHandBook(handBook);
//		 response.setStatus(HttpStatus.OK.value());
//		 
//	}


}
