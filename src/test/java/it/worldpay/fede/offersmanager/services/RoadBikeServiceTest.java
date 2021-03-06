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
import it.worldpay.fede.offersmanager.dao.product.bike.RoadBikeDao;
import it.worldpay.fede.offersmanager.dummy.DummyFactoryImpl;
import it.worldpay.fede.offersmanager.errors.exception.DuplicateResourceException;
import it.worldpay.fede.offersmanager.errors.exception.MissingParameterException;
import it.worldpay.fede.offersmanager.errors.exception.ProductExpiredException;
import it.worldpay.fede.offersmanager.errors.exception.ResourceNotFoundException;
import it.worldpay.fede.offersmanager.model.product.bike.RoadBike;
import it.worldpay.fede.offersmanager.services.product.bike.RoadBikeService;
import it.worldpay.fede.offersmanager.utils.DateTime;
import it.worldpay.fede.offersmanager.utils.DateUtils;

@RunWith(MockitoJUnitRunner.Silent.class)
public class RoadBikeServiceTest {
	
	
	@Before
	public void initializeTestVariable(){
		DummyFactoryImpl  dummyFactory= new DummyFactoryImpl();
		roadBikeDummy = (RoadBike)dummyFactory.getDummyProduct("ROADBIKE");
		roadBikeDummy.setDaysValidityPeriod(5);
		roadBikeDummy.setOfferStartingDate(new Date());
		roadBikeService.testing = true;
	}
	
	@InjectMocks
    private RoadBikeService roadBikeService ;
	
	
	@Mock
	DateUtils dateUtils;
	
	@Mock
	DateTime dateTime;
	
	@Mock
	RoadBikeDao roadBikeDao;
	
	@Mock
	ProductDao<RoadBike> productDao;
	
	private RoadBike roadBikeDummy;
		
	private RoadBike roadBikeFetched;
	
	 @Test(expected = DuplicateResourceException.class)
	 public void whenRoadBikeIsDuplicate_thenDuplicateProductExceptionIsThrown() throws ParseException{
	    
	 	given(roadBikeDao.findByProductId(anyLong())).willReturn(roadBikeDummy);
        given(dateUtils.addDates(any(Date.class),anyInt())).willReturn(new SimpleDateFormat("yyyy-MM-dd").parse("2019-01-22 11:00"));
       
        roadBikeService.saveProduct(roadBikeDummy);
	    }
	
	
	 @Test
	public void whenRoadBikeIsAdded_itIsPossibleToFetchItById()throws ParseException{

		given(productDao.findOne(anyLong())).willReturn(roadBikeDummy);
		given(dateUtils.addDates(any(Date.class),anyInt())).willReturn(new SimpleDateFormat("yyyy-MM-dd").parse("2019-01-22 11:00")); 
		given(dateTime.getDate()).willReturn(new Date());
		
		roadBikeService.saveProduct(roadBikeDummy);
	
		roadBikeFetched = (RoadBike)roadBikeService.getProduct(new Long(281));
		
		assertEquals(roadBikeFetched.getProductId(), roadBikeDummy.getProductId());
	}
	

	@Test(expected = ResourceNotFoundException.class)
	public void whenRoadBikeIsNotFound_ExceptionIsThrown() throws ParseException{
		
		given(roadBikeDao.findOne(anyLong())).willReturn(null);
		given(dateUtils.addDates(any(Date.class),anyInt())).willReturn(new SimpleDateFormat("yyyy-MM-dd").parse("2019-01-22 11:00")); 
		
		roadBikeService.saveProduct(roadBikeDummy);
		
		roadBikeFetched = (RoadBike)roadBikeService.getProduct(new Long(0));
		
	}
	
	
	@Test
	public void whenValidityPeriodIsGiven_itCanBeAddedToStartOfferingDate_ForSettingExpiringDate(){
		
		
		roadBikeDummy.setOfferStartingDate(dateUtils.parseStringToDate("2018-04-25 12:15"));
		
		roadBikeDummy.setOfferExpiringDate(dateUtils.addDates(roadBikeDummy.getOfferStartingDate(), roadBikeDummy.getDaysValidityPeriod()));
		
		assertEquals(dateUtils.parseStringToDate("2018-04-30 12:15"),roadBikeDummy	.getOfferExpiringDate());
	}
	

	@Test(expected = MissingParameterException.class)
	public void whenMandatortSavingParametersAreMissing_ExceptionIsTrhown(){
		
		given(productDao.findByProductId(anyLong())).willReturn(new RoadBike());
		
		roadBikeDummy.setDaysValidityPeriod(0);
		
		roadBikeService.saveProduct(roadBikeDummy);
		
	}
	
	@Test(expected = ProductExpiredException.class)
	public void whenAnInvalidExpiringDateisPassed_thenProductExpiredExceptionIsThrown() throws ParseException{
		
		given(productDao.findByProductId(anyLong())).willReturn(null);
		given(dateUtils.parseStringToDate(anyString())).willReturn(new SimpleDateFormat("yyyy-MM-dd").parse("2014-01-01 11:00"));
		given(dateUtils.addDates(any(Date.class), anyInt())).willReturn(new SimpleDateFormat("yyyy-MM-dd").parse("2014-01-01 11:00"));
		
		roadBikeService.saveProduct(roadBikeDummy);
	}
	
	@Test(expected= ResourceNotFoundException.class)
	public void whenTryingToGetADeletedroadBike_ExceptionIsThrown()  throws ProductExpiredException{
		 
		given(productDao.findByProductId(anyLong())).willReturn(null);
		
		roadBikeService.deleteProduct(roadBikeDummy.getProductId());
	}
	
	@Test(expected= ProductExpiredException.class)
	public void whenTryingToGetAnExpiredRoadBike_thenExceptionIsThrown()  throws ParseException{

		roadBikeDummy.setOfferExpiringDate(new SimpleDateFormat("yyyy-MM-dd").parse("2014-01-01 11:00"));
		
		given(productDao.findOne(anyLong())).willReturn(roadBikeDummy);
		given(dateTime.getDate()).willReturn(new Date());
		
		roadBikeService.getProduct(roadBikeDummy.getProductId());
		
	}


}
