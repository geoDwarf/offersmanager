package it.worldpay.fede.offersmanager.dao;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import it.worldpay.fede.offersmanager.dao.product.book.FantasyBookDao;
import it.worldpay.fede.offersmanager.dummy.DummyFactory;
import it.worldpay.fede.offersmanager.model.product.book.FantasyBook;

@DataJpaTest
@RunWith(SpringRunner.class)
public class FantasyBookDaoTest {
	
	@Before
	public void initializeTestVariable(){
		fantasyBookDummy = (FantasyBook)dummyFactory.getDummyProduct("FANTASYBOOK");
	}
	
	@Autowired
	private DummyFactory dummyFactory;
			
	@Autowired
	FantasyBookDao fantasyBookDao;
	
	FantasyBook fantasyBookDummy;
	
	private FantasyBook fantasyBookFound;
	
	private FantasyBook fantasyBookSaved;
	
	@Test
	public void whenSaveFantasyBook_thenItIsPossibleToFetchIt(){
		
		fantasyBookDao.save(fantasyBookDummy);
		
		fantasyBookFound = fantasyBookDao.findOne(fantasyBookDummy.getProductId());
		
		assertEquals(fantasyBookFound.getProductId(), fantasyBookDummy.getProductId());
			}

	@Test
	public void whenSaveFantasyBook_thenItIsPossibleToDeleteIt(){
		
		fantasyBookDao.save(fantasyBookDummy);
		
		fantasyBookDao.delete(fantasyBookDummy);
		
		fantasyBookFound = fantasyBookDao.findOne(fantasyBookDummy.getProductId());
		
		Assert.assertNull(fantasyBookFound);
	}
	
	@Test
	public void whneFantasyBookIsSaved_thenItIspossibleToFetchItById(){
		
		fantasyBookSaved =fantasyBookDao.save(fantasyBookDummy);
		
		Long fantasyBookSavedId = fantasyBookSaved.getProductId();
		
		fantasyBookFound = (FantasyBook)fantasyBookDao.findByProductId(fantasyBookSavedId);
		}

}
