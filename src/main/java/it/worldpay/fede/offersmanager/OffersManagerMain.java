package it.worldpay.fede.offersmanager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import it.worldpay.fede.offersmanager.dao.FantasyBookDao;
import it.worldpay.fede.offersmanager.dao.GelatoDao;
import it.worldpay.fede.offersmanager.dao.HandBookDao;
import it.worldpay.fede.offersmanager.dao.MountainBikeDao;
import it.worldpay.fede.offersmanager.dao.OfferDao;
import it.worldpay.fede.offersmanager.dao.PastaDao;
import it.worldpay.fede.offersmanager.dao.PizzaDao;
import it.worldpay.fede.offersmanager.dao.RoadBikeDao;
import it.worldpay.fede.offersmanager.model.Offer;
import it.worldpay.fede.offersmanager.model.bikes.MountainBike;


@SpringBootApplication
@ComponentScan({"it.worldpay.fede"})
public class OffersManagerMain implements CommandLineRunner
{
	@Autowired
	OfferDao offerDao;
	
	@Autowired
	DataBaseInitializer dataBaseInitializer;
	
	@Autowired
	MountainBikeDao mountainBikeDao;
	
	@Autowired
	RoadBikeDao roadBikeDao;

	@Autowired
	HandBookDao handBookDao;

	@Autowired
	FantasyBookDao fantasyBookDao;
	
	@Autowired
	PastaDao pastaDao;
	
	@Autowired  
	PizzaDao pizzaDao;
	
	@Autowired
	GelatoDao gelatoDao;
	

	 public static void main( String[] args )
    {
    	 SpringApplication.run(OffersManagerMain.class, args);
    }
	 
	 @Override
	 public void run(String... strings) throws Exception {
		 Offer offer = new Offer();
		 offer.setOfferId(new Long(11));
		 MountainBike mb = new MountainBike();
		 mb.setProductId(new Long(145) );
		 mb.setDaysValidityPeriod(5);
		 mb.setOffer(offer);
		 mb.setOfferStartingDate(new SimpleDateFormat("yyyy-MM-dd").parse("2019-08-22 11:00"));
		 mb.setOfferExpiringDate(new SimpleDateFormat("yyyy-MM-dd").parse("2019-08-27 11:00"));
		 offerDao.save(offer);
		 mountainBikeDao.save(mb);
		 MountainBike mbFound = mountainBikeDao.findOne(new Long(145));
//		offerDao.save(dataBaseInitializer.getGenericOffer());
//		offerDao.save(dataBaseInitializer.getMountainBikeOffer());
//		mountainBikeDao.save(dataBaseInitializer.getMountainBike());
//		roadBikeDao.save(dataBaseInitializer.getRoadBike());
//		handBookDao.save(dataBaseInitializer.getHandBook());
//		fantasyBookDao.save(dataBaseInitializer.getfantasyBook());
//		pastaDao.save(dataBaseInitializer.getPasta());
//		pizzaDao.save(dataBaseInitializer.getPizza());
//		gelatoDao.save(dataBaseInitializer.getGelato());
	
	    }
}
