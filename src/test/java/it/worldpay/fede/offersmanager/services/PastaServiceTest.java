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
import it.worldpay.fede.offersmanager.dummy.DummyFactoryImpl;
import it.worldpay.fede.offersmanager.errors.DuplicateProductException;
import it.worldpay.fede.offersmanager.errors.ProductExpiredException;
import it.worldpay.fede.offersmanager.errors.ProductNotFoundException;
import it.worldpay.fede.offersmanager.exceptions.MissingParameterException;
import it.worldpay.fede.offersmanager.model.food.Pasta;
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
	}

	@InjectMocks
	private PastaServiceImpl pastaServiceImpl ;

	@Mock
	DateUtils dateUtils;

	@Mock
	DateTime dateTime;

	@Mock
	PastaDao pastaDao;

	private Pasta pastaDummy;
		
	private Pasta pastaFetched;

	 @Test(expected = DuplicateProductException.class)
	 public void whenPastaIsDuplicate_thenDuplicateProductExceptionIsThrown() throws ParseException{
	    
	 	given(pastaDao.findByProductId(anyLong())).willReturn(new Pasta());
	    given(dateUtils.addDates(any(Date.class),anyInt())).willReturn(new SimpleDateFormat("yyyy-MM-dd").parse("2019-01-22 11:00"));
	   
	    pastaServiceImpl.savePasta(pastaDummy);
	    }


	 @Test
	public void whenPastaIsAdded_itIsPossibleToFetchItById()throws ParseException{

		given(pastaDao.findOne(anyLong())).willReturn(pastaDummy);
		given(dateUtils.addDates(any(Date.class),anyInt())).willReturn(new SimpleDateFormat("yyyy-MM-dd").parse("2019-01-22 11:00")); 
		given(dateTime.getDate()).willReturn(new Date());
		
		pastaServiceImpl.savePasta(pastaDummy);

		pastaFetched = pastaServiceImpl.getPasta(new Long(281));
		
		assertEquals(pastaFetched.getProductId(), pastaDummy.getProductId());
	}


	@Test(expected = ProductNotFoundException.class)
	public void whenPastaIsNotFound_ExceptionIsThrown() throws ParseException{
		
		given(pastaDao.findOne(anyLong())).willReturn(null);
		given(dateUtils.addDates(any(Date.class),anyInt())).willReturn(new SimpleDateFormat("yyyy-MM-dd").parse("2019-01-22 11:00")); 
		
		pastaServiceImpl.savePasta(pastaDummy);
		
		pastaFetched = pastaServiceImpl.getPasta(new Long(0));
		
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
		
		pastaServiceImpl.savePasta(pastaDummy);
		
	}

	@Test(expected = ProductExpiredException.class)
	public void whenAnInvalidExpiringDateisPassed_thenProductExpiredExceptionIsThrown() throws ParseException{
		
		given(pastaDao.findByProductId(anyLong())).willReturn(null);
		given(dateUtils.parseStringToDate(anyString())).willReturn(new SimpleDateFormat("yyyy-MM-dd").parse("2014-01-01 11:00"));
		given(dateUtils.addDates(any(Date.class), anyInt())).willReturn(new SimpleDateFormat("yyyy-MM-dd").parse("2014-01-01 11:00"));
		
		pastaServiceImpl.savePasta(pastaDummy);
	}


		
		
	@Test(expected= ProductExpiredException.class)
	public void whenTryingToGetAnExpiredPasta_thenExceptionIsThrown()  throws ParseException{

		pastaDummy.setOfferExpiringDate(new SimpleDateFormat("yyyy-MM-dd").parse("2014-01-01 11:00"));
		
		given(pastaDao.findOne(anyLong())).willReturn(pastaDummy);
		given(dateTime.getDate()).willReturn(new Date());
		
		pastaServiceImpl.getPasta(pastaDummy.getProductId());
		
	}


}
