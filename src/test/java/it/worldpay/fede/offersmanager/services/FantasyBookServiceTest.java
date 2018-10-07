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
import it.worldpay.fede.offersmanager.errors.ProductExpiredException;
import it.worldpay.fede.offersmanager.errors.ProductNotFoundException;
import it.worldpay.fede.offersmanager.exceptions.MissingParameterException;
import it.worldpay.fede.offersmanager.model.books.FantasyBook;
import it.worldpay.fede.offersmanager.utils.DateTime;
import it.worldpay.fede.offersmanager.utils.DateUtils;

@RunWith(MockitoJUnitRunner.Silent.class)
public class FantasyBookServiceTest {
	
	@Before
	public void initializeTestVariable(){
		DummyFactoryImpl  dummyFactory= new DummyFactoryImpl();
		pizzaDummy = (FantasyBook)dummyFactory.getDummyProduct("FANTASYBOOK");
		pizzaDummy.setDaysValidityPeriod(5);
		pizzaDummy.setOfferStartingDate(new Date());
	}
	
	@InjectMocks
    private FantasyBookServiceImpl pizzaServiceImpl ;

	@Mock
	DateUtils dateUtils;
	
	@Mock
	DateTime dateTime;
	
	@Mock
	FantasyBookDao pizzaDao;
	
	private FantasyBook pizzaDummy;
		
	private FantasyBook pizzaFetched;
	
	 @Test(expected = DuplicateProductException.class)
	 public void whenFantasyBookIsDuplicate_thenDuplicateProductExceptionIsThrown() throws ParseException{
	    
	 	given(pizzaDao.findByProductId(anyLong())).willReturn(new FantasyBook());
        given(dateUtils.addDates(any(Date.class),anyInt())).willReturn(new SimpleDateFormat("yyyy-MM-dd").parse("2019-01-22 11:00"));
       
        pizzaServiceImpl.saveFantasyBook(pizzaDummy);
	    }
	
	
	 @Test
	public void whenFantasyBookIsAdded_itIsPossibleToFetchItById()throws ParseException{

		given(pizzaDao.findOne(anyLong())).willReturn(pizzaDummy);
		given(dateUtils.addDates(any(Date.class),anyInt())).willReturn(new SimpleDateFormat("yyyy-MM-dd").parse("2019-01-22 11:00")); 
		given(dateTime.getDate()).willReturn(new Date());
		
		pizzaServiceImpl.saveFantasyBook(pizzaDummy);
	
		pizzaFetched = pizzaServiceImpl.getFantasyBook(new Long(281));
		
		assertEquals(pizzaFetched.getProductId(), pizzaDummy.getProductId());
	}
	

	@Test(expected = ProductNotFoundException.class)
	public void whenFantasyBookIsNotFound_ExceptionIsThrown() throws ParseException{
		
		given(pizzaDao.findOne(anyLong())).willReturn(null);
		given(dateUtils.addDates(any(Date.class),anyInt())).willReturn(new SimpleDateFormat("yyyy-MM-dd").parse("2019-01-22 11:00")); 
		
		pizzaServiceImpl.saveFantasyBook(pizzaDummy);
		
		pizzaFetched = pizzaServiceImpl.getFantasyBook(new Long(0));
		
	}
	
	
	@Test
	public void whenValidityPeriodIsGiven_itCanBeAddedToStartOfferingDateForSettingExpiringDate(){
		
		
		pizzaDummy.setOfferStartingDate(dateUtils.parseStringToDate("2018-04-25 12:15"));
		
		pizzaDummy.setOfferExpiringDate(dateUtils.addDates(pizzaDummy.getOfferStartingDate(), pizzaDummy.getDaysValidityPeriod()));
		
		assertEquals(dateUtils.parseStringToDate("2018-04-30 12:15"),pizzaDummy	.getOfferExpiringDate());
	}
	

	@Test(expected = MissingParameterException.class)
	public void whenMandatortSavingParametersAreMissing_ExceptionIsTrhown(){
		
		given(pizzaDao.findByProductId(anyLong())).willReturn(new FantasyBook());
		
		pizzaDummy.setDaysValidityPeriod(0);
		
		pizzaServiceImpl.saveFantasyBook(pizzaDummy);
		
	}
	
	@Test(expected = ProductExpiredException.class)
	public void whenAnInvalidExpiringDateisPassed_thenProductExpiredExceptionIsThrown() throws ParseException{
		
		given(pizzaDao.findByProductId(anyLong())).willReturn(null);
		given(dateUtils.parseStringToDate(anyString())).willReturn(new SimpleDateFormat("yyyy-MM-dd").parse("2014-01-01 11:00"));
		given(dateUtils.addDates(any(Date.class), anyInt())).willReturn(new SimpleDateFormat("yyyy-MM-dd").parse("2014-01-01 11:00"));
		
		pizzaServiceImpl.saveFantasyBook(pizzaDummy);
	}
	
	@Test(expected= ProductNotFoundException.class)
	public void whenTryingToGetADeletedpizza_ExceptionIsThrown()  throws ProductExpiredException{
		 
		given(pizzaDao.findByProductId(anyLong())).willReturn(null);
		
		pizzaServiceImpl.deleteFantasyBook(pizzaDummy);
	}
	
	@Test(expected= ProductExpiredException.class)
	public void whenTryingToGetAnExpiredFantasyBook_thenExceptionIsThrown()  throws ParseException{

		pizzaDummy.setOfferExpiringDate(new SimpleDateFormat("yyyy-MM-dd").parse("2014-01-01 11:00"));
		
		given(pizzaDao.findOne(anyLong())).willReturn(pizzaDummy);
		given(dateTime.getDate()).willReturn(new Date());
		
		pizzaServiceImpl.getFantasyBook(pizzaDummy.getProductId());
		
	}

}
