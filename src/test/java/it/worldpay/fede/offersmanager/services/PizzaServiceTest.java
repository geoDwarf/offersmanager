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

import it.worldpay.fede.offersmanager.dao.PizzaDao;
import it.worldpay.fede.offersmanager.dummy.DummyFactoryImpl;
import it.worldpay.fede.offersmanager.errors.DuplicateProductException;
import it.worldpay.fede.offersmanager.errors.ProductExpiredException;
import it.worldpay.fede.offersmanager.errors.ProductNotFoundException;
import it.worldpay.fede.offersmanager.exceptions.MissingParameterException;
import it.worldpay.fede.offersmanager.model.food.Gelato;
import it.worldpay.fede.offersmanager.model.food.Pizza;
import it.worldpay.fede.offersmanager.utils.DateUtils;

@RunWith(MockitoJUnitRunner.Silent.class)
public class PizzaServiceTest {

	
	@Before
	public void initializeTestVariable(){
		DummyFactoryImpl  dummyFactory= new DummyFactoryImpl();
		pizzaDummy = (Pizza)dummyFactory.getDummyProduct("PIZZA");
		pizzaDummy.setDaysValidityPeriod(5);
		pizzaDummy.setOfferStartingDate(new Date());
	}
	
	@InjectMocks
    private PizzaServiceImpl pizzaServiceImpl ;

	@Mock
	DateUtils dateUtils;
	
	@Mock
	PizzaDao pizzaDao;
	
	private Pizza pizzaDummy;
		
	private Pizza pizzaFetched;
	
	 @Test(expected = DuplicateProductException.class)
	 public void whenPizzaIsDuplicate_thenDuplicateProductExceptionIsThrown() throws ParseException{
	    
	 	given(pizzaDao.findByProductId(anyLong())).willReturn(new Pizza());
        given(dateUtils.addDates(any(Date.class),anyInt())).willReturn(new SimpleDateFormat("yyyy-MM-dd").parse("2019-01-22 11:00"));
       
        pizzaServiceImpl.savePizza(pizzaDummy);
	    }
	
	
	 @Test
	public void whenPizzaIsAdded_itIsPossibleToFetchItById()throws ParseException{

		given(pizzaDao.findOne(anyLong())).willReturn(pizzaDummy);
		given(dateUtils.addDates(any(Date.class),anyInt())).willReturn(new SimpleDateFormat("yyyy-MM-dd").parse("2019-01-22 11:00")); 
		
		pizzaServiceImpl.savePizza(pizzaDummy);
	
		pizzaFetched = pizzaServiceImpl.getPizza(new Long(281));
		
		assertEquals(pizzaFetched.getProductId(), pizzaDummy.getProductId());
	}
	

	@Test(expected = ProductNotFoundException.class)
	public void whenPizzaIsNotFound_ExceptionIsThrown() throws ParseException{
		
		given(pizzaDao.findOne(anyLong())).willReturn(null);
		given(dateUtils.addDates(any(Date.class),anyInt())).willReturn(new SimpleDateFormat("yyyy-MM-dd").parse("2019-01-22 11:00")); 
		
		pizzaServiceImpl.savePizza(pizzaDummy);
		
		pizzaFetched = pizzaServiceImpl.getPizza(new Long(0));
		
	}
	
	
	@Test
	public void whenValidityPeriodIsGiven_itCanBeAddedToStartOfferingDateForSettingExpiringDate(){
		
		
		pizzaDummy.setOfferStartingDate(dateUtils.parseStringToDate("2018-04-25 12:15"));
		
		pizzaDummy.setOfferExpiringDate(dateUtils.addDates(pizzaDummy.getOfferStartingDate(), pizzaDummy.getDaysValidityPeriod()));
		
		assertEquals(dateUtils.parseStringToDate("2018-04-30 12:15"),pizzaDummy	.getOfferExpiringDate());
	}
	

	@Test(expected = MissingParameterException.class)
	public void whenMandatortSavingParametersAreMissing_ExceptionIsTrhown(){
		
		given(pizzaDao.findByProductId(anyLong())).willReturn(new Gelato());
		
		pizzaDummy.setDaysValidityPeriod(0);
		
		pizzaServiceImpl.savePizza(pizzaDummy);
		
	}
	
	@Test(expected = ProductExpiredException.class)
	public void whenAnInvalidExpiringDateisPassed_thenProductExpiredExceptionIsThrown() throws ParseException{
		
		given(pizzaDao.findByProductId(anyLong())).willReturn(null);
		given(dateUtils.parseStringToDate(anyString())).willReturn(new SimpleDateFormat("yyyy-MM-dd").parse("2014-01-01 11:00"));
		given(dateUtils.addDates(any(Date.class), anyInt())).willReturn(new SimpleDateFormat("yyyy-MM-dd").parse("2014-01-01 11:00"));
		
		pizzaServiceImpl.savePizza(pizzaDummy);
	}
	
	@Test(expected= ProductNotFoundException.class)
	public void whenTryingToGetADeletedGelato_ExceptionIsThrown()  throws ProductExpiredException{
		 
		given(pizzaDao.findByProductId(anyLong())).willReturn(null);
		
		pizzaServiceImpl.deletePizza(pizzaDummy);
	}
}