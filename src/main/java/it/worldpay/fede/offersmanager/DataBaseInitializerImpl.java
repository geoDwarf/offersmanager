package it.worldpay.fede.offersmanager;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.springframework.stereotype.Component;

import it.worldpay.fede.offersmanager.model.Offer;
import it.worldpay.fede.offersmanager.model.Product;
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
	
//	Offer genericOffer; //new Offer(new Long(3), new ArrayList<Product>());
//	Offer mountainBikeOffer; 
//	Offer roadBikeBikeoffer = new Offer(new Long(6),new ArrayList<Product>());
//	Offer pizzaOffer = new Offer(new Long(6),new ArrayList<Product>());
//	Offer pastaOffer = new Offer(new Long(7),new ArrayList<Product>());
//	Offer gelatoOffer = new Offer(new Long(8),new ArrayList<Product>());
//	Offer fantasyBookOffer = new Offer(new Long (9),new ArrayList<Product>());
//	Offer handBookOffer = new Offer(new Long(10),new ArrayList<Product>());

	
	public DataBaseInitializerImpl() throws ParseException{
//		this.mountainBikeOffer = new Offer(new Long(5), new ArrayList<Product>());
//		this.genericOffer = new Offer(new Long(3), new ArrayList<Product>());
//		mountainBikeOffer.getProducts().add(getMountainBike());
//		genericOffer.getProducts().add(getGelato());
		
	}
	
	@Override
	public MountainBike getMountainBike() throws ParseException{
		Offer mountainBikeOffer = new Offer();
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
		mountainBike.setOffer(mountainBikeOffer);
		mountainBikeOffer.getProducts().add(mountainBike);
		return mountainBike;
	}
	
	@Override
	public RoadBike getRoadBike() throws ParseException{
		//Offer genericOffer =  new Offer(new Long(6), new ArrayList<Product>());
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
		//roadBike.setOffer(genericOffer);
		//genericOffer.getProducts().add(roadBike);
		return roadBike;
	}

	@Override
	public HandBook getHandBook() throws ParseException{
		//Offer genericOffer =  new Offer(new Long(1), new ArrayList<Product>());
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
	//	handBook.setOffer(genericOffer);
	//	//genericOffer.getProducts().add(handBook);
		return handBook;
	}
	
	@Override
	public FantasyBook getfantasyBook() throws ParseException{
	//	Offer genericOffer =  new Offer(new Long(12), new ArrayList<Product>());
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
	//	fantasyBook.setOffer(genericOffer);
		//genericOffer.getProducts().add(fantasyBook);
		return fantasyBook;
	}
	
	@Override
	public Pizza getPizza() throws ParseException{
//		Offer genericOffer =  new Offer(new Long(23), new ArrayList<Product>());
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
		pizza.setCalories(558.3);
//		pizza.setOffer(genericOffer);
		//genericOffer.getProducts().add(pizza);
		return pizza;
	}
	
	@Override
	public Pasta getPasta() throws ParseException{
//		Offer genericOffer =  new Offer(new Long(33), new ArrayList<Product>());
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
//		pasta.setOffer(genericOffer);
		//genericOffer.getProducts().add(pasta);
		return pasta;
	}
	
	@Override
	public Gelato getGelato() throws ParseException{
	//	Offer genericOffer =  new Offer(new Long(44), new ArrayList<Product>());
		Gelato gelato = new Gelato();
		gelato.setProductId(new Long(8));
		gelato.setCalories(250);
		gelato.setDaysValidityPeriod(2);
		gelato.setOfferStartingDate(new SimpleDateFormat("yyyy-MM-dd").parse("2019-08-18 11:00"));
		gelato.setExpiringDate(new SimpleDateFormat("yyyy-MM-dd").parse("2019-08-29 11:00"));
		gelato.setFlavorOne(Flavor.CHOCOLATE);
		gelato.setFlavorTwo(Flavor.COCONUT);
		gelato.setFlavorThree(Flavor.PISTACHIO);
		gelato.setOfferPrice(2);
		gelato.setOrigin("Unknown");
		gelato.setProductName("Chocolate coconut and pistachi ice cream!");
		gelato.setServedWithWippedCream(false);
	//	gelato.setOffer(genericOffer);
		//genericOffer.getProducts().add(gelato);
		
		return gelato;
	}

//	public Offer getGenericOffer() {
//		return genericOffer;
//	}
//
//	public void setGenericOffer(Offer genericOffer) {
//		this.genericOffer = genericOffer;
//	}
//
//	public Offer getMountainBikeOffer() {
//		return mountainBikeOffer;
//	}
//
//	public void setMountainBikeOffer(Offer mountainBikeOffer) {
//		this.mountainBikeOffer = mountainBikeOffer;
//	}


}
