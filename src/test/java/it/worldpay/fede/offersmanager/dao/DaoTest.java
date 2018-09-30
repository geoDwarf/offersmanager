package it.worldpay.fede.offersmanager.dao;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import it.worldpay.fede.offersmanager.dummy.DummyFactory;
import it.worldpay.fede.offersmanager.model.food.Gelato;
import it.worldpay.fede.offersmanager.model.food.Gelato.Flavor;

@DataJpaTest
@RunWith(SpringRunner.class)
public class DaoTest {
	
	@Before
	public void initializeTestVariable(){
		gelatoDummy = (Gelato)dummyFactory.getDummyProduct("GELATO");
	}
	
	@Autowired
	private DummyFactory dummyFactory;
			
	@Autowired
	GelatoDao<Gelato> gelatoDao;
	
	Gelato gelatoDummy;
	
	private Gelato gelatoFound;
	
	private Gelato gelatoSaved;
	
	@Test
	public void whenSaveGelato_thenItIsPossibleToFetchIt(){
		
		gelatoDao.save(gelatoDummy);
		
		gelatoFound = gelatoDao.findOne(gelatoDummy.getProductId());
		
		assertEquals(gelatoFound.getProductId(), gelatoDummy.getProductId());
			}

	@Test
	public void whenSaveGelato_thenItIsPossibleToDeleteIt(){
		
		gelatoDao.save(gelatoDummy);
		
		gelatoDao.delete(gelatoDummy);
		
		gelatoFound = gelatoDao.findOne(gelatoDummy.getProductId());
		
		Assert.assertNull(gelatoFound);
	}
	
	
//	@Test
//	public void whenSaveGelato_thenItIsPossibleUpdateIt(){
//		
//		Gelato gelatoSaved = gelatoDao.save(gelatoDummy);
//		
//		gelatoSaved.setFlavorOne(Flavor.BANANA);
//		gelatoSaved.setFlavorTwo(Flavor.CHOCOLATE);
//		gelatoDao.save(gelatoSaved);
//		
//		assertEquals(Flavor.BANANA,gelatoSaved.getFlavorOne());
//	}
//	
	@Test
	public void whneGelatoIsSaved_thenItIspossibleToFetchItById(){
		
		gelatoSaved =gelatoDao.save(gelatoDummy);
		
		Long gleatoSavedId = gelatoSaved.getProductId();
		
		gelatoFound = (Gelato)gelatoDao.findByProductId(gleatoSavedId);
		}
}
