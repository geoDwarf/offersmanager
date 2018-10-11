package it.worldpay.fede.offersmanager.dao;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import it.worldpay.fede.offersmanager.dummy.DummyFactory;
import it.worldpay.fede.offersmanager.model.books.FantasyBook;

@DataJpaTest
@RunWith(SpringRunner.class)
public class FantasyBookDaoTest {
	
	@Before
	public void initializeTestVariable(){
		gelatoDummy = (FantasyBook)dummyFactory.getDummyProduct("FANTASYBOOK");
	}
	
	@Autowired
	private DummyFactory dummyFactory;
			
	@Autowired
	FantasyBookDao fantasyBookDao;
	
	FantasyBook gelatoDummy;
	
	private FantasyBook gelatoFound;
	
	private FantasyBook gelatoSaved;
	
	@Test
	public void whenSaveFantasyBook_thenItIsPossibleToFetchIt(){
		
		fantasyBookDao.save(gelatoDummy);
		
		gelatoFound = fantasyBookDao.findOne(gelatoDummy.getProductId());
		
		assertEquals(gelatoFound.getProductId(), gelatoDummy.getProductId());
			}

	@Test
	public void whenSaveFantasyBook_thenItIsPossibleToDeleteIt(){
		
		fantasyBookDao.save(gelatoDummy);
		
		fantasyBookDao.delete(gelatoDummy);
		
		gelatoFound = fantasyBookDao.findOne(gelatoDummy.getProductId());
		
		Assert.assertNull(gelatoFound);
	}
	
	@Test
	public void whneFantasyBookIsSaved_thenItIspossibleToFetchItById(){
		
		gelatoSaved =fantasyBookDao.save(gelatoDummy);
		
		Long gleatoSavedId = gelatoSaved.getProductId();
		
		gelatoFound = (FantasyBook)fantasyBookDao.findByProductId(gleatoSavedId);
		}

}
