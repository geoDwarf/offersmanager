package it.worldpay.fede.offersmanager;


import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.stereotype.Component;

import it.worldpay.fede.offersmanager.model.bikes.MountainBike;
import it.worldpay.fede.offersmanager.model.bikes.RoadBike;
import it.worldpay.fede.offersmanager.model.books.FantasyBook;
import it.worldpay.fede.offersmanager.model.books.HandBook;
import it.worldpay.fede.offersmanager.model.books.HandBook.Level;
import it.worldpay.fede.offersmanager.model.food.Gelato;
import it.worldpay.fede.offersmanager.model.food.Pasta;
import it.worldpay.fede.offersmanager.model.food.Pizza;

@Component
public class DataBaseInitializerImpl implements DataBaseInitializer{
	
	@Override
	public MountainBike getMountainBike() throws ParseException{
		MountainBike mountainBike = new MountainBike();
		mountainBike.setProductId(new Long(2));
		mountainBike.setBrand("Wilier");
		mountainBike.setDaysValidityPeriod(6);
		mountainBike.setOfferDescription("An unique chance of getting a first class bike for rediculous price ");
		mountainBike.setOfferStartingDate(new SimpleDateFormat("yyyy-MM-dd").parse("2019-08-22 11:00"));
		mountainBike.setOfferExpiringDate(new SimpleDateFormat("yyyy-MM-dd").parse("2019-08-28 11:00"));
		mountainBike.setOfferPrice(150.12);
		mountainBike.setProductName("Cento10pro");
		mountainBike.setSpeeds(12);
		return mountainBike;
	}
	
	@Override
	public RoadBike getRoadBike() throws ParseException{
		RoadBike roadBike = new RoadBike();
		roadBike.setProductId(new Long(3));
		roadBike.setBrand("Colnago");
		roadBike.setDaysValidityPeriod(2);
		roadBike.setOfferDescription("A flying bike!");
		roadBike.setOfferStartingDate(new SimpleDateFormat("yyyy-MM-dd").parse("2019-08-18 11:00"));
		roadBike.setOfferExpiringDate(new SimpleDateFormat("yyyy-MM-dd").parse("2019-08-20 11:00"));
		roadBike.setOfferPrice(new Long(1200));
		roadBike.setProductName("Colnago C64");
		roadBike.setSpeeds(18);
		return roadBike;
	}

	@Override
	public HandBook getHandBook() throws ParseException{
		HandBook handBook = new HandBook();
		handBook.setProductId(new Long(4));
		handBook.setCoverType("Soft");
		handBook.setField("IT");
		handBook.setLevel(Level.INTERMEDIATE);
		handBook.setNumberOfPages(512);
		handBook.setOfferDescription("Your five minutes away from you first Java job");
		handBook.setOfferStartingDate(new SimpleDateFormat("yyyy-MM-dd").parse("2019-08-18 11:00"));
		handBook.setOfferExpiringDate(new SimpleDateFormat("yyyy-MM-dd").parse("2019-08-22 11:00"));
		handBook.setOfferPrice(12.50);
		handBook.setProductName("Java from zero to hero! In five minutes..");
		return handBook;
	}
	
	@Override
	public FantasyBook getfantasyBook() throws ParseException{
		FantasyBook fantasyBook = new FantasyBook();
		fantasyBook.setProductId(new Long(5));
		fantasyBook.setCoverType("Hard");
		fantasyBook.setDaysValidityPeriod(8);
		fantasyBook.setFantasyWorldName("Hyrule");
		fantasyBook.setNumberOfPages(852);
		fantasyBook.setOfferDescription("You will be the hero.... at a fair price");
		fantasyBook.setOfferStartingDate(new SimpleDateFormat("yyyy-MM-dd").parse("2019-08-18 11:00"));
		fantasyBook.setOfferExpiringDate(new SimpleDateFormat("yyyy-MM-dd").parse("2019-08-26 11:00"));
		fantasyBook.setOfferPrice(12.50);
		fantasyBook.setProductName("Heroes of Narnia");
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
