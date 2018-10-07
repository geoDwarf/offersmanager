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
import it.worldpay.fede.offersmanager.model.books.HandBook;

@DataJpaTest
@RunWith(SpringRunner.class)
public class HandBookDaoTest {
	
	@Before
	public void initializeTestVariable(){
		handBookDummy = (HandBook)dummyFactory.getDummyProduct("HANDBOOK");
	}
	
	@Autowired
	private DummyFactory dummyFactory;
			
	@Autowired
	HandBookDao handBookoDao;
	
	HandBook handBookDummy;
	
	private HandBook handBookFound;
	
	private HandBook handBookSaved;
	
	@Test
	public void whenSaveGelato_thenItIsPossibleToFetchIt(){
		
		handBookoDao.save(handBookDummy);
		
		handBookFound = handBookoDao.findOne(handBookDummy.getProductId());
		
		assertEquals(handBookFound.getProductId(), handBookDummy.getProductId());
			}

	@Test
	public void whenSaveGelato_thenItIsPossibleToDeleteIt(){
		
		handBookoDao.save(handBookDummy);
		
		handBookoDao.delete(handBookDummy);
		
		handBookFound = handBookoDao.findOne(handBookDummy.getProductId());
		
		Assert.assertNull(handBookFound);
	}
	
	

	@Test
	public void whneGelatoIsSaved_thenItIspossibleToFetchItById(){
		
		handBookSaved =handBookoDao.save(handBookDummy);
		
		Long gleatoSavedId = handBookSaved.getProductId();
		
		handBookFound = (HandBook)handBookoDao.findByProductId(gleatoSavedId);
		}
	


}
