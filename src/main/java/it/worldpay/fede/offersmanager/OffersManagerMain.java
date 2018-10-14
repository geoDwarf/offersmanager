package it.worldpay.fede.offersmanager;

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


@SpringBootApplication
@ComponentScan({ "it.worldpay.fede" })
public class OffersManagerMain implements CommandLineRunner {
	
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

	public static void main(String[] args) {
		SpringApplication.run(OffersManagerMain.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {

		offerDao.save(dataBaseInitializer.getGenericOffer());

		mountainBikeDao.save(dataBaseInitializer.getMountainBike());
		roadBikeDao.save(dataBaseInitializer.getRoadBike());
		handBookDao.save(dataBaseInitializer.getHandBook());
		fantasyBookDao.save(dataBaseInitializer.getfantasyBook());
		pizzaDao.save(dataBaseInitializer.getPizza());
		pastaDao.save(dataBaseInitializer.getPasta());
		gelatoDao.save(dataBaseInitializer.getGelato());
		fantasyBookDao.save(dataBaseInitializer.getfantasyBook());
		pastaDao.save(dataBaseInitializer.getPasta());
		gelatoDao.save(dataBaseInitializer.getGelato());

	}
}
