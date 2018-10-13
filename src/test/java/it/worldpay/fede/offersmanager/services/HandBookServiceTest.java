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

import it.worldpay.fede.offersmanager.dao.HandBookDao;
import it.worldpay.fede.offersmanager.dao.ProductDao;
import it.worldpay.fede.offersmanager.dummy.DummyFactoryImpl;
import it.worldpay.fede.offersmanager.errors.DuplicateResourceException;
import it.worldpay.fede.offersmanager.errors.MissingParameterException;
import it.worldpay.fede.offersmanager.errors.ProductExpiredException;
import it.worldpay.fede.offersmanager.errors.ResourceNotFoundException;
import it.worldpay.fede.offersmanager.model.books.HandBook;
import it.worldpay.fede.offersmanager.utils.DateTime;
import it.worldpay.fede.offersmanager.utils.DateUtils;


@RunWith(MockitoJUnitRunner.Silent.class)
public class HandBookServiceTest {

	@Before
	public void initializeTestVariable(){
		DummyFactoryImpl  dummyFactory= new DummyFactoryImpl();
		handBookDummy = (HandBook)dummyFactory.getDummyProduct("HANDBOOK");
		handBookDummy.setDaysValidityPeriod(5);
		handBookDummy.setOfferStartingDate(new Date());
		handBookService.testing = true;
	}
	
	@InjectMocks
    private HandBookService handBookService;
	
//	@InjectMocks
//	private ProductServiceImpl productServiceImpl;

	@Mock
	DateUtils dateUtils;
	
	@Mock
	DateTime dateTime;
	
	@Mock
	HandBookDao handBookDao;
	
	@Mock
	ProductDao<HandBook> productDao;
	
	private HandBook handBookDummy;
		
	private HandBook handBookFetched;
	
	 @Test(expected = DuplicateResourceException.class)
	 public void whenHandBookIsDuplicate_thenDuplicateProductExceptionIsThrown() throws ParseException{
	    
	 	given(handBookDao.findByProductId(anyLong())).willReturn(handBookDummy);
        given(dateUtils.addDates(any(Date.class),anyInt())).willReturn(new SimpleDateFormat("yyyy-MM-dd").parse("2019-01-22 11:00"));
       
        handBookService.saveProduct(handBookDummy);
	    }
	
	
	 @Test
	public void whenHandBookIsAdded_itIsPossibleToFetchItById()throws ParseException{

		given(productDao.findOne(anyLong())).willReturn(handBookDummy);
		given(dateUtils.addDates(any(Date.class),anyInt())).willReturn(new SimpleDateFormat("yyyy-MM-dd").parse("2019-01-22 11:00")); 
		given(dateTime.getDate()).willReturn(new Date());
		
		handBookService.saveProduct(handBookDummy);
	
		handBookFetched = (HandBook)handBookService.getProduct(new Long(281));
		
		assertEquals(handBookFetched.getProductId(), handBookDummy.getProductId());
	}
	

	@Test(expected = ResourceNotFoundException.class)
	public void whenHandBookIsNotFound_ExceptionIsThrown() throws ParseException{
		
		given(productDao.findOne(anyLong())).willReturn(null);
		given(dateUtils.addDates(any(Date.class),anyInt())).willReturn(new SimpleDateFormat("yyyy-MM-dd").parse("2019-01-22 11:00")); 
		
		handBookService.saveProduct(handBookDummy);
		
		handBookFetched = (HandBook)handBookService.getProduct(new Long(0));
		
	}
	
	
	@Test
	public void whenValidityPeriodIsGiven_itCanBeAddedToStartOfferingDateForSettingExpiringDate(){
		
		handBookDummy.setOfferStartingDate(dateUtils.parseStringToDate("2018-04-25 12:15"));
		
		handBookDummy.setOfferExpiringDate(dateUtils.addDates(handBookDummy.getOfferStartingDate(), handBookDummy.getDaysValidityPeriod()));
		
		assertEquals(dateUtils.parseStringToDate("2018-04-30 12:15"),handBookDummy	.getOfferExpiringDate());
	}
	

	@Test(expected = MissingParameterException.class)
	public void whenMandatortSavingParametersAreMissing_ExceptionIsTrhown(){
		
		given(productDao.findByProductId(anyLong())).willReturn(new HandBook());
		
		handBookDummy.setDaysValidityPeriod(0);
		
		handBookService.saveProduct(handBookDummy);
		
	}
	
	@Test(expected = ProductExpiredException.class)
	public void whenAnInvalidExpiringDateisPassed_thenProductExpiredExceptionIsThrown() throws ParseException{
		
		given(productDao.findByProductId(anyLong())).willReturn(null);
		given(dateUtils.parseStringToDate(anyString())).willReturn(new SimpleDateFormat("yyyy-MM-dd").parse("2014-01-01 11:00"));
		given(dateUtils.addDates(any(Date.class), anyInt())).willReturn(new SimpleDateFormat("yyyy-MM-dd").parse("2014-01-01 11:00"));
		
		handBookService.saveProduct(handBookDummy);
	}
	
	@Test(expected= ResourceNotFoundException.class)
	public void whenTryingToGetADeletedhandBook_ExceptionIsThrown()  throws ProductExpiredException{
		 
		given(handBookDao.findByProductId(anyLong())).willReturn(null);
		
		handBookService.deleteProduct(handBookDummy.getProductId());
	}
	
	@Test(expected= ProductExpiredException.class)
	public void whenTryingToGetAnExpiredHandBook_thenExceptionIsThrown()  throws ParseException{

		handBookDummy.setOfferExpiringDate(new SimpleDateFormat("yyyy-MM-dd").parse("2014-01-01 11:00"));
		
		given(productDao.findOne(anyLong())).willReturn(handBookDummy);
		given(dateTime.getDate()).willReturn(new Date());
		
		handBookService.getProduct(handBookDummy.getProductId());
		
	}

}
