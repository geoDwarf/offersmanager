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

import it.worldpay.fede.offersmanager.model.Offer;
import it.worldpay.fede.offersmanager.model.Product;
import it.worldpay.fede.offersmanager.model.bikes.MountainBike;
import it.worldpay.fede.offersmanager.model.bikes.RoadBike;
import it.worldpay.fede.offersmanager.model.books.FantasyBook;
import it.worldpay.fede.offersmanager.model.books.HandBook;
import it.worldpay.fede.offersmanager.model.food.Gelato;
import it.worldpay.fede.offersmanager.model.food.Pasta;
import it.worldpay.fede.offersmanager.model.food.Pizza;
import it.worldpay.fede.offersmanager.services.BaseServiceDefault;
import it.worldpay.fede.offersmanager.services.FantasyBookService;
import it.worldpay.fede.offersmanager.services.GelatoService;
import it.worldpay.fede.offersmanager.services.HandBookService;
import it.worldpay.fede.offersmanager.services.MountainBikeService;
import it.worldpay.fede.offersmanager.services.OfferServiceDefault;
import it.worldpay.fede.offersmanager.services.PastaService;
import it.worldpay.fede.offersmanager.services.PizzaService;
import it.worldpay.fede.offersmanager.services.RoadBikeService;

@RestController
@EnableAutoConfiguration
@RequestMapping(value="/offers")
public class OffersManagerController {
	
	@Autowired
	BaseServiceDefault baseServiceDefault;
	
	@Autowired
	PizzaService pizzaService;
	
	@Autowired
	GelatoService gelatoService;
	
	@Autowired 
	PastaService pastaService;
	
	@Autowired 
	MountainBikeService mountainBikeService;
	
	@Autowired 
	RoadBikeService roadBikeService;
	
	@Autowired
	HandBookService handBookService;
	
	@Autowired 
	FantasyBookService fantasyBookService;
	
	@Autowired
	OfferServiceDefault offerServiceDefault;	

	
	@RequestMapping(value="/getProduct/{productId}", method = RequestMethod.GET)
	public Product getProduct(@PathVariable("productId") Long productId) {
        return baseServiceDefault.getProduct(productId);
    }
	
	@RequestMapping(value="/deleteProduct/{productId}", method = RequestMethod.DELETE)
	public void deleteProduct(@PathVariable("productId") Long  productId, HttpServletResponse response)
	{
		baseServiceDefault.deleteProduct(productId);
		 response.setStatus(HttpStatus.OK.value());
	}
	
	@RequestMapping(value="/getOffer/{offerId}", method = RequestMethod.GET)
	public Offer getOffer(@PathVariable("offerId") Long offerId) {
        return offerServiceDefault.getOffer(offerId);
    }


	@RequestMapping(value="/saveGelato", method = RequestMethod.POST)
	public void saveGelato(@Valid @RequestBody Gelato gelato, HttpServletResponse response){
		gelatoService.saveProduct(gelato);
		response.setStatus(HttpStatus.CREATED.value());	
		}
	
	
	@RequestMapping(value="/savePizza", method = RequestMethod.POST)
	public void savePizza(@Valid @RequestBody Pizza pizza, HttpServletResponse response){
		pizzaService.saveProduct(pizza);
		response.setStatus(HttpStatus.CREATED.value());	
		}
	

	@RequestMapping(value="/savePasta", method = RequestMethod.POST)
	public void savePizza(@Valid @RequestBody Pasta pasta, HttpServletResponse response){
		pastaService.saveProduct(pasta);
		response.setStatus(HttpStatus.CREATED.value());	
		}
	
	
	@RequestMapping(value="/saveMountainBike", method = RequestMethod.POST)
	public void saveMountainBike(@Valid @RequestBody MountainBike mountainBike, HttpServletResponse response){
		mountainBikeService.saveProduct(mountainBike);
		response.setStatus(HttpStatus.CREATED.value());	
		}
	

	@RequestMapping(value="/saveRoadBike", method = RequestMethod.POST)
	public void saveRoadBike(@Valid @RequestBody RoadBike roadBike, HttpServletResponse response){
		roadBikeService.saveProduct(roadBike);
		response.setStatus(HttpStatus.CREATED.value());	
		}
	

	@RequestMapping(value="/saveHandBook", method = RequestMethod.POST)
	public void saveHandBook(@Valid @RequestBody HandBook handBook, HttpServletResponse response){
		handBookService.saveProduct(handBook);
		response.setStatus(HttpStatus.CREATED.value());	
		}
	

	@RequestMapping(value="/saveFantasyBook", method = RequestMethod.POST)
	public void saveFantasyBook(@Valid @RequestBody FantasyBook fantasyBook, HttpServletResponse response){
		fantasyBookService.saveProduct(fantasyBook);
		response.setStatus(HttpStatus.CREATED.value());	
	}
	
	
	@RequestMapping(value="/saveOffer", method = RequestMethod.POST)
	public void saveOffer(@Valid @RequestBody Offer offer, HttpServletResponse response){
		offerServiceDefault.saveOffer(offer);
		response.setStatus(HttpStatus.CREATED.value());	
	}		
	
	@RequestMapping(value="/deleteOffer/{offerId}", method = RequestMethod.DELETE)
	public void deleteOffer(@PathVariable("offerId") Long  offerId, HttpServletResponse response)
	{
		offerServiceDefault.deleteOffer(offerId);
		 response.setStatus(HttpStatus.OK.value());
	}
}	
	
