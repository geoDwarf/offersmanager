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

import it.worldpay.fede.offersmanager.dao.GelatoDao;
import it.worldpay.fede.offersmanager.dao.ProductDao;
import it.worldpay.fede.offersmanager.dummy.DummyFactoryImpl;
import it.worldpay.fede.offersmanager.errors.DuplicateResourceException;
import it.worldpay.fede.offersmanager.errors.MissingParameterException;
import it.worldpay.fede.offersmanager.errors.ProductExpiredException;
import it.worldpay.fede.offersmanager.errors.ResourceNotFoundException;
import it.worldpay.fede.offersmanager.model.food.Gelato;
import it.worldpay.fede.offersmanager.utils.DateTime;
import it.worldpay.fede.offersmanager.utils.DateUtils;


@RunWith(MockitoJUnitRunner.Silent.class)
public class GelatoServiceTest {
	
	@Before
	public void initializeTestVariable(){
	DummyFactoryImpl  dummyFactory= new DummyFactoryImpl();
	gelatoDummy = (Gelato)dummyFactory.getDummyProduct("GELATO");
	gelatoDummy.setDaysValidityPeriod(5);
	gelatoDummy.setOfferStartingDate(new Date());
	gelatoService.testing = true;
}
	

@InjectMocks
private GelatoService gelatoService ;
//
//@InjectMocks
//private ProductServiceImpl productServiceImpl;

@Mock
DateUtils dateUtils;

@Mock
DateTime dateTime;

@Mock
GelatoDao gelatoDao;

@Mock
ProductDao<Gelato> productDao;

private Gelato gelatoDummy;
	
private Gelato gelatoFetched;


 @Test(expected = DuplicateResourceException.class)
 public void whenGelatoIsDuplicate_thenDuplicateProductExceptionIsThrown() throws ParseException{
    
	given(gelatoDao.findOne(anyLong())).willReturn(gelatoDummy);
 	given(gelatoDao.findByProductId(anyLong())).willReturn(gelatoDummy);
    given(dateUtils.addDates(any(Date.class),anyInt())).willReturn(new SimpleDateFormat("yyyy-MM-dd").parse("2019-01-22 11:00"));
   
    gelatoService.saveProduct(gelatoDummy);
    }


 @Test
public void whenGelatoIsAdded_itIsPossibleToFetchItById()throws ParseException{

	given(productDao.findOne(anyLong())).willReturn(gelatoDummy);
	given(dateUtils.addDates(any(Date.class),anyInt())).willReturn(new SimpleDateFormat("yyyy-MM-dd").parse("2019-01-22 11:00")); 
	given(dateTime.getDate()).willReturn(new Date());
	
	gelatoService.saveProduct(gelatoDummy);

	gelatoFetched = (Gelato)gelatoService.getProduct(new Long(281));
	
	assertEquals(gelatoFetched.getProductId(), gelatoDummy.getProductId());
}


@Test(expected = ResourceNotFoundException.class)
public void whenGelatoIsNotFound_ExceptionIsThrown() throws ParseException{
	
	given(productDao.findOne(anyLong())).willReturn(null);
	given(dateUtils.addDates(any(Date.class),anyInt())).willReturn(new SimpleDateFormat("yyyy-MM-dd").parse("2019-01-22 11:00")); 
	
	gelatoService.saveProduct(gelatoDummy);
	
	gelatoFetched = (Gelato)gelatoService.getProduct(new Long(0));
	
}


@Test
public void whenValidityPeriodIsGiven_itCanBeAddedToStartOfferingDateForSettingExpiringDate(){
	
	
	gelatoDummy.setOfferStartingDate(dateUtils.parseStringToDate("2018-04-25 12:15"));
	
	gelatoDummy.setOfferExpiringDate(dateUtils.addDates(gelatoDummy.getOfferStartingDate(), gelatoDummy.getDaysValidityPeriod()));
	
	assertEquals(dateUtils.parseStringToDate("2018-04-30 12:15"),gelatoDummy.getOfferExpiringDate());
}


@Test(expected = MissingParameterException.class)
public void whenMandatortSavingParametersAreMissing_ExceptionIsTrhown(){
	
	given(gelatoDao.findByProductId(anyLong())).willReturn(new Gelato());
	
	gelatoDummy.setDaysValidityPeriod(0);
	
	gelatoService.saveProduct(gelatoDummy);
	
}

@Test(expected = ProductExpiredException.class)
public void whenAnInvalidExpiringDateisPassed_thenProductExpiredExceptionIsThrown() throws ParseException{
	
	given(gelatoDao.findByProductId(anyLong())).willReturn(null);
	given(dateUtils.parseStringToDate(anyString())).willReturn(new SimpleDateFormat("yyyy-MM-dd").parse("2014-01-01 11:00"));
	given(dateUtils.addDates(any(Date.class), anyInt())).willReturn(new SimpleDateFormat("yyyy-MM-dd").parse("2014-01-01 11:00"));
	
	gelatoService.saveProduct(gelatoDummy);
}


	
	
@Test(expected= ProductExpiredException.class)
public void whenTryingToGetAnExpiredGelato_thenExceptionIsThrown()  throws ParseException{

	gelatoDummy.setOfferExpiringDate(new SimpleDateFormat("yyyy-MM-dd").parse("2014-01-01 11:00"));
	
	given(productDao.findOne(anyLong())).willReturn(gelatoDummy);
	given(dateTime.getDate()).willReturn(new Date());
	
	gelatoService.getProduct(gelatoDummy.getProductId());
	
}


}
