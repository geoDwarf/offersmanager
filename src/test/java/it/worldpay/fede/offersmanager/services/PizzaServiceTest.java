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

import it.worldpay.fede.offersmanager.dao.product.ProductDao;
import it.worldpay.fede.offersmanager.dao.product.food.PizzaDao;
import it.worldpay.fede.offersmanager.dummy.DummyFactoryImpl;
import it.worldpay.fede.offersmanager.errors.exception.DuplicateResourceException;
import it.worldpay.fede.offersmanager.errors.exception.MissingParameterException;
import it.worldpay.fede.offersmanager.errors.exception.ProductExpiredException;
import it.worldpay.fede.offersmanager.errors.exception.ResourceNotFoundException;
import it.worldpay.fede.offersmanager.model.product.food.Pizza;
import it.worldpay.fede.offersmanager.services.product.food.PizzaService;
import it.worldpay.fede.offersmanager.utils.DateTime;
import it.worldpay.fede.offersmanager.utils.DateUtils;

@RunWith(MockitoJUnitRunner.Silent.class)
public class PizzaServiceTest {

	
	@Before
	public void initializeTestVariable(){
		DummyFactoryImpl  dummyFactory= new DummyFactoryImpl();
		pizzaDummy = (Pizza)dummyFactory.getDummyProduct("PIZZA");
		pizzaDummy.setDaysValidityPeriod(5);
		pizzaDummy.setOfferStartingDate(new Date());
		pizzaService.testing = true;
	}
	
	@InjectMocks
    private PizzaService pizzaService ;
	
	@Mock
	DateUtils dateUtils;
	
	@Mock
	DateTime dateTime;
	
	@Mock
	PizzaDao pizzaDao;
	
	@Mock
	ProductDao<Pizza> productDao;
	
	
	private Pizza pizzaDummy;
		
	private Pizza pizzaFetched;
	
	 @Test(expected = DuplicateResourceException.class)
	 public void whenPizzaIsDuplicate_thenDuplicateProductExceptionIsThrown() throws ParseException{
	    
		given(pizzaDao.findByProductId(anyLong())).willReturn(pizzaDummy);
		given(productDao.findByProductId(anyLong())).willReturn(pizzaDummy);
        given(dateUtils.addDates(any(Date.class),anyInt())).willReturn(new SimpleDateFormat("yyyy-MM-dd").parse("2019-01-22 11:00"));
       
        pizzaService.saveProduct(pizzaDummy);
	    }
	
	
	 @Test
	public void whenPizzaIsAdded_itIsPossibleToFetchItById()throws ParseException{


		given(productDao.findOne(anyLong())).willReturn(pizzaDummy);
		given(dateUtils.addDates(any(Date.class),anyInt())).willReturn(new SimpleDateFormat("yyyy-MM-dd").parse("2019-01-22 11:00")); 
		given(dateTime.getDate()).willReturn(new Date());
		
		pizzaService.saveProduct(pizzaDummy);
	
		pizzaFetched = (Pizza)pizzaService.getProduct(new Long(281));
		
		assertEquals(pizzaFetched.getProductId(), pizzaDummy.getProductId());
	}
	

	@Test(expected = ResourceNotFoundException.class)
	public void whenPizzaIsNotFound_ExceptionIsThrown() throws ParseException{
		
		given(productDao.findOne(anyLong())).willReturn(null);
		given(dateUtils.addDates(any(Date.class),anyInt())).willReturn(new SimpleDateFormat("yyyy-MM-dd").parse("2019-01-22 11:00")); 
		
		pizzaService.saveProduct(pizzaDummy);
		
		pizzaFetched = (Pizza)pizzaService.getProduct(new Long(0));
		
	}
	
	
	@Test
	public void whenValidityPeriodIsGiven_itCanBeAddedToStartOfferingDateForSettingExpiringDate(){
		
		
		pizzaDummy.setOfferStartingDate(dateUtils.parseStringToDate("2018-04-25 12:15"));
		
		pizzaDummy.setOfferExpiringDate(dateUtils.addDates(pizzaDummy.getOfferStartingDate(), pizzaDummy.getDaysValidityPeriod()));
		
		assertEquals(dateUtils.parseStringToDate("2018-04-30 12:15"),pizzaDummy	.getOfferExpiringDate());
	}
	

	@Test(expected = MissingParameterException.class)
	public void whenMandatortSavingParametersAreMissing_ExceptionIsTrhown(){
		
		given(productDao.findByProductId(anyLong())).willReturn(new Pizza());
		
		pizzaDummy.setDaysValidityPeriod(0);
		
		pizzaService.saveProduct(pizzaDummy);
		
	}
	
	@Test(expected = ProductExpiredException.class)
	public void whenAnInvalidExpiringDateisPassed_thenProductExpiredExceptionIsThrown() throws ParseException{
		
		given(productDao.findByProductId(anyLong())).willReturn(null);
		given(dateUtils.parseStringToDate(anyString())).willReturn(new SimpleDateFormat("yyyy-MM-dd").parse("2014-01-01 11:00"));
		given(dateUtils.addDates(any(Date.class), anyInt())).willReturn(new SimpleDateFormat("yyyy-MM-dd").parse("2014-01-01 11:00"));
		
		pizzaService.saveProduct(pizzaDummy);
	}
	
	@Test(expected= ResourceNotFoundException.class)
	public void whenTryingToGetADeletedpizza_ExceptionIsThrown()  throws ProductExpiredException{
		 
		given(pizzaDao.findByProductId(anyLong())).willReturn(null);
		
		
		pizzaService.deleteProduct(pizzaDummy.getProductId());
	}
	
	@Test(expected= ProductExpiredException.class)
	public void whenTryingToGetAnExpiredPizza_thenExceptionIsThrown()  throws ParseException{

		pizzaDummy.setOfferExpiringDate(new SimpleDateFormat("yyyy-MM-dd").parse("2014-01-01 11:00"));
		
		given(productDao.findOne(anyLong())).willReturn(pizzaDummy);
		given(dateTime.getDate()).willReturn(new Date());
		
		pizzaService.getProduct(pizzaDummy.getProductId());
		
	}
}
