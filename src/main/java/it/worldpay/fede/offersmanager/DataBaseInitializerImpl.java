package it.worldpay.fede.offersmanager;


import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.stereotype.Component;

import it.worldpay.fede.offersmanager.model.bikes.MountainBike;
import it.worldpay.fede.offersmanager.model.bikes.RoadBike;
import it.worldpay.fede.offersmanager.model.books.FantasyBook;
import it.worldpay.fede.offersmanager.model.books.HandBook;
import it.worldpay.fede.offersmanager.model.food.Gelato;
import it.worldpay.fede.offersmanager.model.food.Pasta;
import it.worldpay.fede.offersmanager.model.food.Pizza;

@Component
public class DataBaseInitializerImpl implements DataBaseInitializer{
	
	@Override
	public MountainBike getMountainBike() throws ParseException{
		MountainBike mountainBike = new MountainBike();
		mountainBike.setProductId(new Long(2));
		mountainBike.setBrand("Cannondale");
		mountainBike.setDaysValidityPeriod(6);
		mountainBike.setOfferDescription("An unique chance of getting a first class bike for rediculous price ");
		mountainBike.setOfferStartingDate(new SimpleDateFormat("yyyy-MM-dd").parse("2019-08-22 11:00"));
		mountainBike.setOfferExpiringDate(new SimpleDateFormat("yyyy-MM-dd").parse("2019-14-22 11:00"));
		mountainBike.setOfferPrice(new Long(150));
		mountainBike.setProductName("Cannondale t13X");
		return mountainBike;
	}
	
	@Override
	public RoadBike getRoadBike(){
		RoadBike roadBike = new RoadBike();
		roadBike.setProductId(new Long(3));
		return roadBike;
	}

	@Override
	public HandBook getHandBook(){
		HandBook handBook = new HandBook();
		handBook.setProductId(new Long(4));
		return handBook;
	}
	
	@Override
	public FantasyBook getfantasyBook(){
		FantasyBook fantasyBook = new FantasyBook();
		fantasyBook.setProductId(new Long(5));
		return fantasyBook;
	}
	
	@Override
	public Pizza getPizza(){
		Pizza pizza = new Pizza();
		pizza.setProductId(new Long(6));
		return pizza;
	}
	
	@Override
	public Pasta getPasta(){
		Pasta pasta = new Pasta();
		pasta.setProductId(new Long(7));
		return pasta;
	}
	
	public Gelato getGelato(){
		Gelato gelato = new Gelato();
		gelato.setProductId(new Long(8));
		return gelato;
	} 

}
