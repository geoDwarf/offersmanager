package it.worldpay.fede.offersmanager.services;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.BDDMockito.when;
import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import it.worldpay.fede.offersmanager.dao.OfferDao;
import it.worldpay.fede.offersmanager.dummy.DummyFactoryImpl;
import it.worldpay.fede.offersmanager.errors.DuplicateResourceException;
import it.worldpay.fede.offersmanager.errors.ProductExpiredException;
import it.worldpay.fede.offersmanager.errors.ResourceNotFoundException;
import it.worldpay.fede.offersmanager.model.Offer;

@RunWith(MockitoJUnitRunner.Silent.class)
public class OfferServiceTest {
	
	@Before
	public void initializeTestVariable(){
		
		DummyFactoryImpl  dummyFactory= new DummyFactoryImpl();
		offerDummy = dummyFactory.getDummyOffer(new Long(3),new ArrayList<>());
	
	}
	
	private Offer offerDummy;
	
	private Offer offerFetched;
	
	@Mock
	OfferDao offerDao;
	
	@InjectMocks
	OfferServiceDefault offerServiceDefault;
	
	@Test(expected = DuplicateResourceException.class)
	public void whenOfferIsDuplicated_thenDuplicateResourceExceptionIsThrown() throws ParseException{
	    
		given(offerDao.findOne(anyLong())).willReturn(offerDummy);
   
		offerServiceDefault.saveOffer(offerDummy);
	    
	}

	 @Test(expected = ResourceNotFoundException.class)
	public void whenOfferIsNotFound_ExceptionIsThrown() throws ParseException{
	 	
	 	given(offerDao.findOne(anyLong())).willReturn(null);

	 	offerServiceDefault.saveOffer(offerDummy);
	
	 	offerFetched = offerServiceDefault.getOffer(new Long(0));
 	
	 }
	 
	 @Test(expected= ResourceNotFoundException.class)
	 public void whenTryingToGetADeletedOffer_ExceptionIsThrown()  throws ProductExpiredException{
			 
		when(offerDao.findOne(anyLong())).thenReturn(offerDummy, null);
			
		offerServiceDefault.deleteOffer(offerDummy.getOfferId());
		
		offerServiceDefault.getOffer(offerDummy.getOfferId());
		
	 }
		

}
