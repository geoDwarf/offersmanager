package it.worldpay.fede.offersmanager.services;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import it.worldpay.fede.offersmanager.dao.GelatoDao;
import it.worldpay.fede.offersmanager.dummy.DummyFactory;
import it.worldpay.fede.offersmanager.dummy.DummyFactoryImpl;
import it.worldpay.fede.offersmanager.errors.DuplicateProductException;
import it.worldpay.fede.offersmanager.errors.ProductNotFoundException;
import it.worldpay.fede.offersmanager.model.food.Gelato;

@DataJpaTest
@RunWith(SpringRunner.class)
public class GelatoServiceTest {
	
	
	@Autowired
	DummyFactory dummyFactory;
	
	@Autowired
	GelatoService gelatoService;	
	
	@Autowired
	GelatoDao<Gelato> gelatoDao;
	
	@Before
	public void initializeTestVariable(){
		DummyFactory dummyFactory = new DummyFactoryImpl();
		gelatoDummy = (Gelato)dummyFactory.getDummyProduct("GELATO");
	}
	private Gelato gelatoDummy;

	private Gelato gelatoFetched;
	
	
	
	@Test
	public void whenGelatoIsAdded_itIsPossibleToFetchItById(){

		gelatoService.saveGelato(gelatoDummy);
		
		gelatoFetched = gelatoService.getGelato(new Long(281));
		
		assertEquals(gelatoFetched.getProductId(), gelatoDummy.getProductId());
	}
	
	@Test (expected = ProductNotFoundException.class)
	public void whenTrytoFetchaADeletedGelato_thenExceptionIsthrown(){
		
		gelatoService.saveGelato(gelatoDummy);
		
		gelatoService.deleteGelato(gelatoDummy);
		
		gelatoFetched = gelatoService.getGelato(new Long(281));
		
		//assertEquals(gelatoFetched, null);
		
	}
	
	
	@Test (expected = DuplicateProductException.class)
	public void whenGelatoIsUpdated_thanExceptionIsThrown(){
		
		gelatoService.saveGelato(gelatoDummy);		
		
		gelatoDummy.setOfferPrice(new Long(250));
		
		gelatoService.saveGelato(gelatoDummy);
	} 
	


}
