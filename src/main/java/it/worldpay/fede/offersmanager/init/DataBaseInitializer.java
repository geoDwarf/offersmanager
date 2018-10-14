package it.worldpay.fede.offersmanager.init;


import java.text.ParseException;

import org.springframework.stereotype.Component;

import it.worldpay.fede.offersmanager.model.offer.Offer;
import it.worldpay.fede.offersmanager.model.product.bike.MountainBike;
import it.worldpay.fede.offersmanager.model.product.bike.RoadBike;
import it.worldpay.fede.offersmanager.model.product.book.FantasyBook;
import it.worldpay.fede.offersmanager.model.product.book.HandBook;
import it.worldpay.fede.offersmanager.model.product.food.Gelato;
import it.worldpay.fede.offersmanager.model.product.food.Pasta;
import it.worldpay.fede.offersmanager.model.product.food.Pizza;

@Component
public interface DataBaseInitializer {
	
	MountainBike getMountainBike() throws ParseException;
	
	RoadBike getRoadBike()throws ParseException;
	
	HandBook getHandBook()throws ParseException;
	
	FantasyBook getfantasyBook()throws ParseException;
	
	Pizza getPizza() throws ParseException;
	
	Pasta getPasta() throws ParseException;
	
	Gelato getGelato() throws ParseException; 
	
	Offer getGenericOffer();
	
	//Offer getMountainBikeOffer();
}
