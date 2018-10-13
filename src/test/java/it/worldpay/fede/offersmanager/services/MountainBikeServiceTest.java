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

import it.worldpay.fede.offersmanager.dao.MountainBikeDao;
import it.worldpay.fede.offersmanager.dao.ProductDao;
import it.worldpay.fede.offersmanager.dummy.DummyFactoryImpl;
import it.worldpay.fede.offersmanager.errors.DuplicateResourceException;
import it.worldpay.fede.offersmanager.errors.MissingParameterException;
import it.worldpay.fede.offersmanager.errors.ProductExpiredException;
import it.worldpay.fede.offersmanager.errors.ResourceNotFoundException;
import it.worldpay.fede.offersmanager.model.bikes.MountainBike;
import it.worldpay.fede.offersmanager.utils.DateTime;
import it.worldpay.fede.offersmanager.utils.DateUtils;

@RunWith(MockitoJUnitRunner.Silent.class)
public class MountainBikeServiceTest {
	
	@Before
	public void initializeTestVariable(){
	DummyFactoryImpl  dummyFactory= new DummyFactoryImpl();
	mountainBikeDummy = (MountainBike)dummyFactory.getDummyProduct("MOUNTAINBIKE");
	mountainBikeDummy.setDaysValidityPeriod(5);
	mountainBikeDummy.setOfferStartingDate(new Date());
	mountainBikeService.testing = true;
}

@InjectMocks
private MountainBikeService mountainBikeService ;



@Mock
DateUtils dateUtils;

@Mock
DateTime dateTime;

@Mock
MountainBikeDao mountainBikeDao;

@Mock
ProductDao<MountainBike> productDao;

private MountainBike mountainBikeDummy;
	
private MountainBike mountainBikeFetched;



@Test(expected = DuplicateResourceException.class)
 public void whenMountainBikeIsDuplicate_thenDuplicateProductExceptionIsThrown() throws ParseException{
    
 	given(mountainBikeDao.findByProductId(anyLong())).willReturn(new MountainBike());
    given(dateUtils.addDates(any(Date.class),anyInt())).willReturn(new SimpleDateFormat("yyyy-MM-dd").parse("2019-01-22 11:00"));
   
    mountainBikeService.saveProduct(mountainBikeDummy);
    }


@Test
public void whenMountainBikeIsAdded_itIsPossibleToFetchItById()throws ParseException{

	given(productDao.findOne(anyLong())).willReturn(mountainBikeDummy);
	given(dateUtils.addDates(any(Date.class),anyInt())).willReturn(new SimpleDateFormat("yyyy-MM-dd").parse("2019-01-22 11:00")); 
	given(dateTime.getDate()).willReturn(new Date());
	
	mountainBikeService.saveProduct(mountainBikeDummy);

	mountainBikeFetched = (MountainBike)mountainBikeService.getProduct(new Long(281));
	
	assertEquals(mountainBikeFetched.getProductId(), mountainBikeDummy.getProductId());
}


@Test(expected = ResourceNotFoundException.class)
public void whenMountainBikeIsNotFound_ExceptionIsThrown() throws ParseException{
	
	given(productDao.findByProductId(anyLong())).willReturn(null);
	given(dateUtils.addDates(any(Date.class),anyInt())).willReturn(new SimpleDateFormat("yyyy-MM-dd").parse("2019-01-22 11:00")); 
	
	mountainBikeService.saveProduct(mountainBikeDummy);
	
	mountainBikeFetched = (MountainBike)mountainBikeService.getProduct(new Long(0));
	
}


@Test
public void whenValidityPeriodIsGiven_itCanBeAddedToStartOfferingDateForSettingExpiringDate(){
	
	
	mountainBikeDummy.setOfferStartingDate(dateUtils.parseStringToDate("2018-04-25 12:15"));
	
	mountainBikeDummy.setOfferExpiringDate(dateUtils.addDates(mountainBikeDummy.getOfferStartingDate(), mountainBikeDummy.getDaysValidityPeriod()));
	
	assertEquals(dateUtils.parseStringToDate("2018-04-30 12:15"),mountainBikeDummy.getOfferExpiringDate());
}


@Test(expected = MissingParameterException.class)
public void whenMandatortSavingParametersAreMissing_ExceptionIsTrhown(){
	
	given(mountainBikeDao.findByProductId(anyLong())).willReturn(new MountainBike());
	
	mountainBikeDummy.setDaysValidityPeriod(0);
	
	mountainBikeService.saveProduct(mountainBikeDummy);
	
}


@Test(expected = ProductExpiredException.class)
public void whenAnInvalidExpiringDateisPassed_thenProductExpiredExceptionIsThrown() throws ParseException{
	
	given(productDao.findByProductId(anyLong())).willReturn(null);
	given(dateUtils.parseStringToDate(anyString())).willReturn(new SimpleDateFormat("yyyy-MM-dd").parse("2014-01-01 11:00"));
	given(dateUtils.addDates(any(Date.class), anyInt())).willReturn(new SimpleDateFormat("yyyy-MM-dd").parse("2014-01-01 11:00"));
	
	mountainBikeService.saveProduct(mountainBikeDummy);
}


@Test(expected= ProductExpiredException.class)
public void whenTryingToGetAnExpiredMountainBike_thenExceptionIsThrown()  throws ParseException{

	mountainBikeDummy.setOfferExpiringDate(new SimpleDateFormat("yyyy-MM-dd").parse("2014-01-01 11:00"));
	
	given(productDao.findOne(anyLong())).willReturn(mountainBikeDummy);
	given(dateTime.getDate()).willReturn(new Date());
	
	mountainBikeService.getProduct(mountainBikeDummy.getProductId());
	
}



}
