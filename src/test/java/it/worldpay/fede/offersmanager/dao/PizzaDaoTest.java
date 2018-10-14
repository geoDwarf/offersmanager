package it.worldpay.fede.offersmanager.dao;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import it.worldpay.fede.offersmanager.dao.product.food.PizzaDao;
import it.worldpay.fede.offersmanager.dummy.DummyFactory;
import it.worldpay.fede.offersmanager.model.product.food.Pizza;

@DataJpaTest
@RunWith(SpringRunner.class)
public class PizzaDaoTest {

	@Before
	public void initializeTestVariable(){
		pizzaDummy = (Pizza)dummyFactory.getDummyProduct("PIZZA");
	}
	
	@Autowired
	private DummyFactory dummyFactory;
			
	@Autowired
	PizzaDao pizzaoDao;
	
	Pizza pizzaDummy;
	
	private Pizza pizzaFound;
	
	private Pizza pizzaSaved;
	
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
		
		Long pizzaSavedId = pizzaSaved.getProductId();
		
		pizzaFound = (Pizza)pizzaoDao.findByProductId(pizzaSavedId);
		}
	
}
