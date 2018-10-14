package it.worldpay.fede.offersmanager.dao;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import it.worldpay.fede.offersmanager.dao.product.bike.RoadBikeDao;
import it.worldpay.fede.offersmanager.dummy.DummyFactory;
import it.worldpay.fede.offersmanager.model.product.bike.RoadBike;

@DataJpaTest
@RunWith(SpringRunner.class)
public class RoadBikeDaoTest {
	@Before
	public void initializeTestVariable(){
		pizzaDummy = (RoadBike)dummyFactory.getDummyProduct("ROADBIKE");
	}
	
	@Autowired
	private DummyFactory dummyFactory;
			
	@Autowired
	RoadBikeDao pizzaoDao;
	
	RoadBike pizzaDummy;
	
	private RoadBike pizzaFound;
	
	private RoadBike pizzaSaved;
	
	@Test
	public void whenSaveGelato_thenItIsPossibleToFetchIt(){
		
		pizzaoDao.save(pizzaDummy);
		
		pizzaFound = pizzaoDao.findOne(pizzaDummy.getProductId());
		
		assertEquals(pizzaFound.getProductId(), pizzaDummy.getProductId());
			}

	@Test
	public void whenSaveGelato_thenItIsPossibleToDeleteIt(){
		
		pizzaoDao.save(pizzaDummy);
		
		pizzaoDao.delete(pizzaDummy);
		
		pizzaFound = pizzaoDao.findOne(pizzaDummy.getProductId());
		
		Assert.assertNull(pizzaFound);
	}
	
	

	@Test
	public void whneGelatoIsSaved_thenItIspossibleToFetchItById(){
		
		pizzaSaved =pizzaoDao.save(pizzaDummy);
		
		Long roadBikeSavedId = pizzaSaved.getProductId();
		
		pizzaFound = (RoadBike)pizzaoDao.findByProductId(roadBikeSavedId);
		}
}
