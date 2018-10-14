package it.worldpay.fede.offersmanager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import it.worldpay.fede.offersmanager.dao.offer.OfferDao;
import it.worldpay.fede.offersmanager.dao.product.bike.MountainBikeDao;
import it.worldpay.fede.offersmanager.dao.product.bike.RoadBikeDao;
import it.worldpay.fede.offersmanager.dao.product.book.FantasyBookDao;
import it.worldpay.fede.offersmanager.dao.product.book.HandBookDao;
import it.worldpay.fede.offersmanager.dao.product.food.GelatoDao;
import it.worldpay.fede.offersmanager.dao.product.food.PastaDao;
import it.worldpay.fede.offersmanager.dao.product.food.PizzaDao;
import it.worldpay.fede.offersmanager.init.DataBaseInitializer;


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
