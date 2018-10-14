package it.worldpay.fede.offersmanager.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import it.worldpay.fede.offersmanager.dao.offer.OfferDao;
import it.worldpay.fede.offersmanager.dummy.DummyFactory;
import it.worldpay.fede.offersmanager.model.offer.Offer;
import it.worldpay.fede.offersmanager.model.product.Product;

@DataJpaTest
@RunWith(SpringRunner.class)
public class OfferDaoTest {

	
	@Before
	public void initializeTestVariable(){
		offerDummy = dummyFactory.getDummyOffer(new Long(222), new ArrayList<Product>() );
	}
	
	@Autowired
	private DummyFactory dummyFactory;

	@Autowired
	OfferDao offerDao;
	
	private Offer offerDummy;
	
	private Offer offerFound;
	
	private Offer offerSaved;
	
	
	@Test
	public void whenOfferIsSaved_thenItIsPossibleToFetchIt(){
		
		offerDao.save(offerDummy);
		
		offerFound = (Offer)offerDao.findOne(offerDummy.getOfferId());
		
		assertEquals(offerFound.getOfferId(), offerDummy.getOfferId());
			}
	
	@Test
	public void whenOfferIsSaved_thenItIsPossibleToDeleteIt(){
		
		offerDao.save(offerDummy);
		
		offerDao.delete(offerDummy);
		
		offerFound = offerDao.findOne(offerDummy.getOfferId());
		
		Assert.assertNull(offerFound);
	}
	
	

	@Test
	public void whenOfferIsSaved_thenItIspossibleToFetchItById(){
		
		offerSaved =offerDao.save(offerDummy);
		
		Long offerSavedId = offerSaved.getOfferId();
		
		offerFound = (Offer)offerDao.findOne(offerSavedId);
		}
	
}
