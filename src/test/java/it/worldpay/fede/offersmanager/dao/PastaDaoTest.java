package it.worldpay.fede.offersmanager.dao;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import it.worldpay.fede.offersmanager.dao.product.food.PastaDao;
import it.worldpay.fede.offersmanager.dummy.DummyFactory;
import it.worldpay.fede.offersmanager.model.product.food.Pasta;

@DataJpaTest
@RunWith(SpringRunner.class)
public class PastaDaoTest {

	@Before
	public void initializeTestVariable(){
		pastaDummy = (Pasta)dummyFactory.getDummyProduct("PASTA");
	}
	
	@Autowired
	private DummyFactory dummyFactory;
			
	@Autowired
	PastaDao pastaDao;
	
	private Pasta pastaDummy;
	
	private Pasta pastaFound;
	
	private Pasta pastaSaved;
	
	@Test
	public void whenSavePasta_thenItIsPossibleToFetchIt(){
		
		pastaDao.save(pastaDummy);
		
		pastaFound = pastaDao.findOne(pastaDummy.getProductId());
		
		assertEquals(pastaFound.getProductId(), pastaDummy.getProductId());
			}

	@Test
	public void whenSavePasta_thenItIsPossibleToDeleteIt(){
		
		pastaDao.save(pastaDummy);
		
		pastaDao.delete(pastaDummy);
		
		pastaFound = pastaDao.findOne(pastaDummy.getProductId());
		
		Assert.assertNull(pastaFound);
	}
	
	

	@Test
	public void whnePastaIsSaved_thenItIspossibleToFetchItById(){
		
		pastaSaved =pastaDao.save(pastaDummy);
		
		Long pastaSavedId = pastaSaved.getProductId();
		
		pastaFound = (Pasta)pastaDao.findByProductId(pastaSavedId);
		}
}
