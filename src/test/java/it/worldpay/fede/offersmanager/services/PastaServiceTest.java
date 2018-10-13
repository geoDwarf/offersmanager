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

import it.worldpay.fede.offersmanager.dao.PastaDao;
import it.worldpay.fede.offersmanager.dao.ProductDao;
import it.worldpay.fede.offersmanager.dummy.DummyFactoryImpl;
import it.worldpay.fede.offersmanager.errors.DuplicateResourceException;
import it.worldpay.fede.offersmanager.errors.MissingParameterException;
import it.worldpay.fede.offersmanager.errors.ProductExpiredException;
import it.worldpay.fede.offersmanager.errors.ResourceNotFoundException;
import it.worldpay.fede.offersmanager.model.food.Pasta;
import it.worldpay.fede.offersmanager.model.food.Pizza;
import it.worldpay.fede.offersmanager.utils.DateTime;
import it.worldpay.fede.offersmanager.utils.DateUtils;



@RunWith(MockitoJUnitRunner.Silent.class)
public class PastaServiceTest {
	
	@Before
	public void initializeTestVariable(){
		DummyFactoryImpl  dummyFactory= new DummyFactoryImpl();
		pastaDummy = (Pasta)dummyFactory.getDummyProduct("PASTA");
		pastaDummy.setDaysValidityPeriod(5);
		pastaDummy.setOfferStartingDate(new Date());
		pastaService.testing = true;
	}

	@InjectMocks
	private PastaService pastaService ;
	
	@Mock
	DateUtils dateUtils;

	@Mock
	DateTime dateTime;

	@Mock
	PastaDao pastaDao;
	
	@Mock
	ProductDao<Pasta> productDao;

	private Pasta pastaDummy;
		
	private Pasta pastaFetched;

	 @Test(expected = DuplicateResourceException.class)
	 public void whenPastaIsDuplicate_thenDuplicateProductExceptionIsThrown() throws ParseException{
	    
	 	given(pastaDao.findByProductId(anyLong())).willReturn(pastaDummy);
	    given(dateUtils.addDates(any(Date.class),anyInt())).willReturn(new SimpleDateFormat("yyyy-MM-dd").parse("2019-01-22 11:00"));
	   
	    pastaService.saveProduct(pastaDummy);
	    }


	 @Test
	public void whenPastaIsAdded_itIsPossibleToFetchItById()throws ParseException{

		given(productDao.findOne(anyLong())).willReturn(pastaDummy);
		given(dateUtils.addDates(any(Date.class),anyInt())).willReturn(new SimpleDateFormat("yyyy-MM-dd").parse("2019-01-22 11:00")); 
		given(dateTime.getDate()).willReturn(new Date());
		
		pastaService.saveProduct(pastaDummy);

		pastaFetched = (Pasta)pastaService.getProduct(new Long(281));
		
		assertEquals(pastaFetched.getProductId(), pastaDummy.getProductId());
	}


	@Test(expected = ResourceNotFoundException.class)
	public void whenPastaIsNotFound_ExceptionIsThrown() throws ParseException{
		
		given(productDao.findOne(anyLong())).willReturn(null);
		given(dateUtils.addDates(any(Date.class),anyInt())).willReturn(new SimpleDateFormat("yyyy-MM-dd").parse("2019-01-22 11:00")); 
		
		pastaService.saveProduct(pastaDummy);
		
		pastaFetched = (Pasta)pastaService.getProduct(new Long(0));
		
	}


	@Test
	public void whenValidityPeriodIsGiven_itCanBeAddedToStartOfferingDateForSettingExpiringDate(){
		
		
		pastaDummy.setOfferStartingDate(dateUtils.parseStringToDate("2018-04-25 12:15"));
		
		pastaDummy.setOfferExpiringDate(dateUtils.addDates(pastaDummy.getOfferStartingDate(), pastaDummy.getDaysValidityPeriod()));
		
		assertEquals(dateUtils.parseStringToDate("2018-04-30 12:15"),pastaDummy.getOfferExpiringDate());
	}


	@Test(expected = MissingParameterException.class)
	public void whenMandatortSavingParametersAreMissing_ExceptionIsTrhown(){
		
		given(pastaDao.findByProductId(anyLong())).willReturn(new Pasta());
		
		pastaDummy.setDaysValidityPeriod(0);
		
		pastaService.saveProduct(pastaDummy);
		
	}

	@Test(expected = ProductExpiredException.class)
	public void whenAnInvalidExpiringDateisPassed_thenProductExpiredExceptionIsThrown() throws ParseException{
		
		given(pastaDao.findByProductId(anyLong())).willReturn(null);
		given(dateUtils.parseStringToDate(anyString())).willReturn(new SimpleDateFormat("yyyy-MM-dd").parse("2014-01-01 11:00"));
		given(dateUtils.addDates(any(Date.class), anyInt())).willReturn(new SimpleDateFormat("yyyy-MM-dd").parse("2014-01-01 11:00"));
		
		pastaService.saveProduct(pastaDummy);
	}


		
		
	@Test(expected= ProductExpiredException.class)
	public void whenTryingToGetAnExpiredPasta_thenExceptionIsThrown()  throws ParseException{

		pastaDummy.setOfferExpiringDate(new SimpleDateFormat("yyyy-MM-dd").parse("2014-01-01 11:00"));
		
		given(productDao.findOne(anyLong())).willReturn(pastaDummy);
		given(dateTime.getDate()).willReturn(new Date());
		
		pastaService.getProduct(pastaDummy.getProductId());
		
	}


}
