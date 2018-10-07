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

import it.worldpay.fede.offersmanager.dao.RoadBikeDao;
import it.worldpay.fede.offersmanager.dummy.DummyFactoryImpl;
import it.worldpay.fede.offersmanager.errors.DuplicateProductException;
import it.worldpay.fede.offersmanager.errors.ProductExpiredException;
import it.worldpay.fede.offersmanager.errors.ProductNotFoundException;
import it.worldpay.fede.offersmanager.exceptions.MissingParameterException;
import it.worldpay.fede.offersmanager.model.bikes.RoadBike;
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
	}
	
	@InjectMocks
    private RoadBikeServiceImpl roadBikeServiceImpl ;

	@Mock
	DateUtils dateUtils;
	
	@Mock
	DateTime dateTime;
	
	@Mock
	RoadBikeDao roadBikeDao;
	
	private RoadBike roadBikeDummy;
		
	private RoadBike roadBikeFetched;
	
	 @Test(expected = DuplicateProductException.class)
	 public void whenRoadBikeIsDuplicate_thenDuplicateProductExceptionIsThrown() throws ParseException{
	    
	 	given(roadBikeDao.findByProductId(anyLong())).willReturn(new RoadBike());
        given(dateUtils.addDates(any(Date.class),anyInt())).willReturn(new SimpleDateFormat("yyyy-MM-dd").parse("2019-01-22 11:00"));
       
        roadBikeServiceImpl.saveRoadBike(roadBikeDummy);
	    }
	
	
	 @Test
	public void whenRoadBikeIsAdded_itIsPossibleToFetchItById()throws ParseException{

		given(roadBikeDao.findOne(anyLong())).willReturn(roadBikeDummy);
		given(dateUtils.addDates(any(Date.class),anyInt())).willReturn(new SimpleDateFormat("yyyy-MM-dd").parse("2019-01-22 11:00")); 
		given(dateTime.getDate()).willReturn(new Date());
		
		roadBikeServiceImpl.saveRoadBike(roadBikeDummy);
	
		roadBikeFetched = roadBikeServiceImpl.getRoadBike(new Long(281));
		
		assertEquals(roadBikeFetched.getProductId(), roadBikeDummy.getProductId());
	}
	

	@Test(expected = ProductNotFoundException.class)
	public void whenRoadBikeIsNotFound_ExceptionIsThrown() throws ParseException{
		
		given(roadBikeDao.findOne(anyLong())).willReturn(null);
		given(dateUtils.addDates(any(Date.class),anyInt())).willReturn(new SimpleDateFormat("yyyy-MM-dd").parse("2019-01-22 11:00")); 
		
		roadBikeServiceImpl.saveRoadBike(roadBikeDummy);
		
		roadBikeFetched = roadBikeServiceImpl.getRoadBike(new Long(0));
		
	}
	
	
	@Test
	public void whenValidityPeriodIsGiven_itCanBeAddedToStartOfferingDateForSettingExpiringDate(){
		
		
		roadBikeDummy.setOfferStartingDate(dateUtils.parseStringToDate("2018-04-25 12:15"));
		
		roadBikeDummy.setOfferExpiringDate(dateUtils.addDates(roadBikeDummy.getOfferStartingDate(), roadBikeDummy.getDaysValidityPeriod()));
		
		assertEquals(dateUtils.parseStringToDate("2018-04-30 12:15"),roadBikeDummy	.getOfferExpiringDate());
	}
	

	@Test(expected = MissingParameterException.class)
	public void whenMandatortSavingParametersAreMissing_ExceptionIsTrhown(){
		
		given(roadBikeDao.findByProductId(anyLong())).willReturn(new RoadBike());
		
		roadBikeDummy.setDaysValidityPeriod(0);
		
		roadBikeServiceImpl.saveRoadBike(roadBikeDummy);
		
	}
	
	@Test(expected = ProductExpiredException.class)
	public void whenAnInvalidExpiringDateisPassed_thenProductExpiredExceptionIsThrown() throws ParseException{
		
		given(roadBikeDao.findByProductId(anyLong())).willReturn(null);
		given(dateUtils.parseStringToDate(anyString())).willReturn(new SimpleDateFormat("yyyy-MM-dd").parse("2014-01-01 11:00"));
		given(dateUtils.addDates(any(Date.class), anyInt())).willReturn(new SimpleDateFormat("yyyy-MM-dd").parse("2014-01-01 11:00"));
		
		roadBikeServiceImpl.saveRoadBike(roadBikeDummy);
	}
	
	@Test(expected= ProductNotFoundException.class)
	public void whenTryingToGetADeletedroadBike_ExceptionIsThrown()  throws ProductExpiredException{
		 
		given(roadBikeDao.findByProductId(anyLong())).willReturn(null);
		
		roadBikeServiceImpl.deleteRoadBike(roadBikeDummy);
	}
	
	@Test(expected= ProductExpiredException.class)
	public void whenTryingToGetAnExpiredRoadBike_thenExceptionIsThrown()  throws ParseException{

		roadBikeDummy.setOfferExpiringDate(new SimpleDateFormat("yyyy-MM-dd").parse("2014-01-01 11:00"));
		
		given(roadBikeDao.findOne(anyLong())).willReturn(roadBikeDummy);
		given(dateTime.getDate()).willReturn(new Date());
		
		roadBikeServiceImpl.getRoadBike(roadBikeDummy.getProductId());
		
	}


}