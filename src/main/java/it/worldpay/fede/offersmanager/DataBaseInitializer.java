package it.worldpay.fede.offersmanager;


import java.text.ParseException;

import org.springframework.stereotype.Component;

import it.worldpay.fede.offersmanager.model.bikes.MountainBike;
import it.worldpay.fede.offersmanager.model.bikes.RoadBike;
import it.worldpay.fede.offersmanager.model.books.FantasyBook;
import it.worldpay.fede.offersmanager.model.books.HandBook;
import it.worldpay.fede.offersmanager.model.food.Gelato;
import it.worldpay.fede.offersmanager.model.food.Pasta;
import it.worldpay.fede.offersmanager.model.food.Pizza;

@Component
public interface DataBaseInitializer {
	
	MountainBike getMountainBike() throws ParseException;
	
	RoadBike getRoadBike()throws ParseException;
	
	HandBook getHandBook()throws ParseException;
	
	FantasyBook getfantasyBook()throws ParseException;
	
	Pizza getPizza() throws ParseException;
	
	Pasta getPasta() throws ParseException;
	
	Gelato getGelato() throws ParseException; 

}
