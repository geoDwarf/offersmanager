package it.worldpay.fede.offersmanager.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import it.worldpay.fede.offersmanager.dao.FantasyBookDao;
import it.worldpay.fede.offersmanager.dummy.DummyFactoryImpl;
import it.worldpay.fede.offersmanager.errors.DuplicateProductException;
import it.worldpay.fede.offersmanager.errors.MissingParameterException;
import it.worldpay.fede.offersmanager.errors.ProductExpiredException;
import it.worldpay.fede.offersmanager.errors.ProductNotFoundException;
import it.worldpay.fede.offersmanager.model.books.FantasyBook;
import it.worldpay.fede.offersmanager.utils.DateTime;
import it.worldpay.fede.offersmanager.utils.DateUtils;

@RunWith(MockitoJUnitRunner.Silent.class)
public class FantasyBookServiceTest  {
	
	@Before
	public void initializeTestVariable(){
		DummyFactoryImpl  dummyFactory= new DummyFactoryImpl();
		fantasyBookDummy = (FantasyBook)dummyFactory.getDummyProduct("FANTASYBOOK");
		fantasyBookDummy.setDaysValidityPeriod(5);
		fantasyBookDummy.setOfferStartingDate(new Date());
		fantasyBookServiceImpl.testing = true;
	}
	
	@InjectMocks
    private FantasyBookServiceImpl fantasyBookServiceImpl ;

	@Mock
	DateUtils dateUtils;
	
	@Mock
	DateTime dateTime;
	
	@Mock
	FantasyBookDao fantasyBookDao;
	
	private FantasyBook fantasyBookDummy;
		
	private FantasyBook fantasyBookFetched;
	
	 @Test(expected = DuplicateProductException.class)
	 public void whenFantasyBookIsDuplicate_thenDuplicateProductExceptionIsThrown() throws ParseException{
	    
	 	given(fantasyBookDao.findByProductId(anyLong())).willReturn(new FantasyBook());
        given(dateUtils.addDates(any(Date.class),anyInt())).willReturn(new SimpleDateFormat("yyyy-MM-dd").parse("2019-01-22 11:00"));
       
        fantasyBookServiceImpl.saveFantasyBook(fantasyBookDummy);
	    }
	
	
//	 @Test
//	public void whenFantasyBookIsAdded_itIsPossibleToFetchItById()throws ParseException{
//
//		given(fantasyBookDao.findOne(anyLong())).willReturn(fantasyBookDummy);
//		given(dateUtils.addDates(any(Date.class),anyInt())).willReturn(new SimpleDateFormat("yyyy-MM-dd").parse("2019-01-22 11:00")); 
//		given(dateTime.getDate()).willReturn(new Date());
//		
//		fantasyBookServiceImpl.saveFantasyBook(fantasyBookDummy);
//	
//		fantasyBookFetched = fantasyBookServiceImpl.getFantasyBook(new Long(281));
//		
//		assertEquals(fantasyBookFetched.getProductId(), fantasyBookDummy.getProductId());
//	}
	

//	@Test(expected = ProductNotFoundException.class)
//	public void whenFantasyBookIsNotFound_ExceptionIsThrown() throws ParseException{
//	
//		given(fantasyBookDao.findOne(anyLong())).willReturn(null);
//		given(dateUtils.addDates(any(Date.class),anyInt())).willReturn(new SimpleDateFormat("yyyy-MM-dd").parse("2019-01-22 11:00")); 
//		
//		fantasyBookServiceImpl.saveFantasyBook(fantasyBookDummy);
//		
//		fantasyBookFetched = fantasyBookServiceImpl.getFantasyBook(new Long(0));
//		
//	}
//	
	
	@Test
	public void whenValidityPeriodIsGiven_itCanBeAddedToStartOfferingDateForSettingExpiringDate(){
		
		
		fantasyBookDummy.setOfferStartingDate(dateUtils.parseStringToDate("2018-04-25 12:15"));
		
		fantasyBookDummy.setOfferExpiringDate(dateUtils.addDates(fantasyBookDummy.getOfferStartingDate(), fantasyBookDummy.getDaysValidityPeriod()));
		
		assertEquals(dateUtils.parseStringToDate("2018-04-30 12:15"),fantasyBookDummy	.getOfferExpiringDate());
	}
	

	@Test(expected = MissingParameterException.class)
	public void whenMandatortSavingParametersAreMissing_ExceptionIsTrhown(){
		
		given(fantasyBookDao.findByProductId(anyLong())).willReturn(new FantasyBook());
		
		fantasyBookDummy.setDaysValidityPeriod(0);
		
		fantasyBookServiceImpl.saveFantasyBook(fantasyBookDummy);
		
	}
	
	@Test(expected = ProductExpiredException.class)
	public void whenAnInvalidExpiringDateisPassed_thenProductExpiredExceptionIsThrown() throws ParseException{
		
		given(fantasyBookDao.findByProductId(anyLong())).willReturn(null);
		given(dateUtils.parseStringToDate(anyString())).willReturn(new SimpleDateFormat("yyyy-MM-dd").parse("2014-01-01 11:00"));
		given(dateUtils.addDates(any(Date.class), anyInt())).willReturn(new SimpleDateFormat("yyyy-MM-dd").parse("2014-01-01 11:00"));
		
		fantasyBookServiceImpl.saveFantasyBook(fantasyBookDummy);
	}
	
//	@Test(expected= ProductNotFoundException.class)
//	public void whenTryingToGetADeletedfantasyBook_ExceptionIsThrown()  throws ProductExpiredException{
//		 
//		given(fantasyBookDao.findByProductId(anyLong())).willReturn(null);
//		
//		fantasyBookServiceImpl.deleteFantasyBook(fantasyBookDummy);
//	}
	
//	@Test(expected= ProductExpiredException.class)
//	public void whenTryingToGetAnExpiredFantasyBook_thenExceptionIsThrown()  throws ParseException{
//
//		fantasyBookDummy.setOfferExpiringDate(new SimpleDateFormat("yyyy-MM-dd").parse("2014-01-01 11:00"));
//		
//		given(fantasyBookDao.findOne(anyLong())).willReturn(fantasyBookDummy);
//		given(dateTime.getDate()).willReturn(new Date());
//		
//		fantasyBookServiceImpl.getFantasyBook(fantasyBookDummy.getProductId());
//		
//	}

}
