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

import it.worldpay.fede.offersmanager.dao.ProductDao;
import it.worldpay.fede.offersmanager.dummy.DummyFactoryImpl;
import it.worldpay.fede.offersmanager.errors.DuplicateProductException;
import it.worldpay.fede.offersmanager.errors.ProductExpiredException;
import it.worldpay.fede.offersmanager.errors.ProductNotFoundException;
import it.worldpay.fede.offersmanager.exceptions.MissingParameterException;
import it.worldpay.fede.offersmanager.model.Product;
import it.worldpay.fede.offersmanager.model.food.Gelato;
import it.worldpay.fede.offersmanager.utils.DateTime;
import it.worldpay.fede.offersmanager.utils.DateUtils;


@RunWith(MockitoJUnitRunner.Silent.class)
public class ProductServiceTest {
	
	@Before
	public void initializeTestVariable(){
		DummyFactoryImpl  dummyFactory= new DummyFactoryImpl();
		productDummy = dummyFactory.getDummyProduct("GELATO");
		productDummy.setOfferStartingDate(new Date());
		productDummy.setDaysValidityPeriod(5);
	}
	
	@InjectMocks
    private ProductServiceImpl productServiceImpl ;

	@Mock
	DateUtils dateUtils;
	
	@Mock
	DateTime dateTime;
	
	@Mock
	ProductDao<Product> productDao;
	
	private Product productDummy;
	
	
	
	private Product productUpdated;
	
	private Product productFetched;
	
	 @Test(expected = DuplicateProductException.class)
	 public void whenProductIsDuplicate_thenDuplicateProductExceptionIsThrown() throws ParseException{
	    
	 	given(productDao.findByProductId(anyLong())).willReturn(new Gelato());
        given(dateUtils.addDates(any(Date.class),anyInt())).willReturn(new SimpleDateFormat("yyyy-MM-dd").parse("2019-01-22 11:00"));
       
        productServiceImpl.saveProduct(productDummy);
	    }
	
	
	 @Test
	public void whenProductIsAdded_itIsPossibleToFetchItById()throws ParseException{

		given(productDao.findOne(anyLong())).willReturn(productDummy);
		given(dateUtils.addDates(any(Date.class),anyInt())).willReturn(new SimpleDateFormat("yyyy-MM-dd").parse("2019-01-22 11:00")); 
		given(dateTime.getDate()).willReturn(new Date());
		
		productServiceImpl.saveProduct(productDummy);
	
		productFetched = productServiceImpl.getProduct(new Long(281));
		
		assertEquals(productFetched.getProductId(), productDummy.getProductId());
	}
	

	@Test(expected = ProductNotFoundException.class)
	public void whenProductIsNotFound_ExceptionIsThrown() throws ParseException{
		
		given(productDao.findOne(anyLong())).willReturn(null);
		given(dateUtils.addDates(any(Date.class),anyInt())).willReturn(new SimpleDateFormat("yyyy-MM-dd").parse("2019-01-22 11:00")); 
		
		productServiceImpl.saveProduct(productDummy);
		
		productFetched = productServiceImpl.getProduct(new Long(0));
		
	}
	
	
	@Test
	public void whenValidityPeriodIsGiven_itCanBeAddedToStartOfferingDateForSettingExpiringDate(){
		
		
		productDummy.setOfferStartingDate(dateUtils.parseStringToDate("2018-04-25 12:15"));
		
		productDummy.setOfferExpiringDate(dateUtils.addDates(productDummy.getOfferStartingDate(), productDummy.getDaysValidityPeriod()));
		
		assertEquals(dateUtils.parseStringToDate("2018-04-30 12:15"),productDummy.getOfferExpiringDate());
	}
	

	@Test(expected = MissingParameterException.class)
	public void whenMandatortSavingParametersAreMissing_ExceptionIsTrhown(){
		
		given(productDao.findByProductId(anyLong())).willReturn(new Gelato());
		productDummy.setDaysValidityPeriod(0);
		
		productServiceImpl.saveProduct(productDummy);
		
	}
	
	@Test(expected = ProductExpiredException.class)
	public void whenAnInvalidExpiringDateisPassed_thenProductExpiredExceptionIsThrown() throws ParseException{
		
		given(productDao.findByProductId(anyLong())).willReturn(null);
		given(dateUtils.parseStringToDate(anyString())).willReturn(new SimpleDateFormat("yyyy-MM-dd").parse("2014-01-01 11:00"));
		given(dateUtils.addDates(any(Date.class), anyInt())).willReturn(new SimpleDateFormat("yyyy-MM-dd").parse("2014-01-01 11:00"));
		
		
		productServiceImpl.saveProduct(productDummy);
	}
	
	@Test(expected = ProductExpiredException.class)
	public void whenAnExpiredProductIsFound_ProductExpiredExceptionIsThrown()  throws ParseException{
		
		productDummy.setOfferExpiringDate(new SimpleDateFormat("yyyy-MM-dd").parse("2014-01-01 11:00"));
		
		given(productDao.findOne(anyLong())).willReturn(productDummy);
		given(dateTime.getDate()).willReturn(new Date());
		
		productServiceImpl.getProduct(productDummy.getProductId());
	}
	
	
	@Test
	public void whenProductIsDeleted_ItWillThorwoExceptionIfWeTryToFetchit(){
		//TODO
	}
	
	
}
