package it.worldpay.fede.offersmanager;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.springframework.stereotype.Component;

import it.worldpay.fede.offersmanager.model.Offer;
import it.worldpay.fede.offersmanager.model.bikes.MountainBike;
import it.worldpay.fede.offersmanager.model.bikes.RoadBike;
import it.worldpay.fede.offersmanager.model.books.FantasyBook;
import it.worldpay.fede.offersmanager.model.books.HandBook;
import it.worldpay.fede.offersmanager.model.books.HandBook.Level;
import it.worldpay.fede.offersmanager.model.food.Gelato;
import it.worldpay.fede.offersmanager.model.food.Gelato.Flavor;
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
		mountainBike.setFrontForkBrand("FOX");
		mountainBike.setFullSuspended(false);
		
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
		handBook.setOffer(getGenericOffer());
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
	public Pizza getPizza() throws ParseException{
		Pizza pizza = new Pizza();
		pizza.setProductId(new Long(6));
		pizza.setDressing("Margherita");
		pizza.setOfferDescription("The most classical italian pizza served at special price");
		pizza.setOfferPrice(15.30);
		pizza.setOfferStartingDate(new SimpleDateFormat("yyyy-MM-dd").parse("2019-08-18 11:00"));
		pizza.setOfferExpiringDate(new SimpleDateFormat("yyyy-MM-dd").parse("2019-08-26 11:00"));
		pizza.setDaysValidityPeriod(8);
		pizza.setOrigin("Piedmont");
		pizza.setProductName("Pizza Margherita");
		pizza.setOffer(getGenericOffer());
		pizza.setCalories(558.3);
		return pizza;
	}
	
	@Override
	public Pasta getPasta() throws ParseException{
		Pasta pasta = new Pasta();
		pasta.setProductId(new Long(7));
		pasta.setDressing("Carbonara");
		pasta.setOrigin("Naples");
		pasta.setCookingTime(11);
		pasta.setDaysValidityPeriod(11);
		pasta.setOfferDescription("Bacon, eggs an pepper...");
		pasta.setOfferPrice(5.3);
		pasta.setOfferStartingDate(new SimpleDateFormat("yyyy-MM-dd").parse("2019-08-18 11:00"));
		pasta.setOfferExpiringDate(new SimpleDateFormat("yyyy-MM-dd").parse("2019-08-29 11:00"));
		pasta.setPastaType("Bucatini");
		pasta.setProductName("Carboara pasta");
		return pasta;
	}
	
	@Override
	public Gelato getGelato() throws ParseException{
		Gelato gelato = new Gelato();
		gelato.setProductId(new Long(8));
		gelato.setCalories(250);
		gelato.setDaysValidityPeriod(2);
		gelato.setOfferStartingDate(new SimpleDateFormat("yyyy-MM-dd").parse("2019-08-18 11:00"));
		gelato.setOfferExpiringDate(new SimpleDateFormat("yyyy-MM-dd").parse("2019-08-29 11:00"));
		gelato.setFlavorOne(Flavor.CHOCOLATE);
		gelato.setFlavorTwo(Flavor.COCONUT);
		gelato.setFlavorThree(Flavor.PISTACHIO);
		gelato.setOfferPrice(2);
		gelato.setOrigin("Unknown");
		gelato.setProductName("Chocolate coconut and pistachi ice cream!");
		gelato.setServedWithWippedCream(false);		
		return gelato;
	}
	
	@Override
	public Offer getGenericOffer(){
		return new Offer(new Long(15), new ArrayList<>());
	}
	


}
