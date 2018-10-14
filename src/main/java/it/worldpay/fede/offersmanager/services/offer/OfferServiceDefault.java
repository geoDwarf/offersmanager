package it.worldpay.fede.offersmanager.services.offer;

import org.springframework.stereotype.Service;

import it.worldpay.fede.offersmanager.errors.exception.DuplicateResourceException;
import it.worldpay.fede.offersmanager.errors.exception.ProductExpiredException;
import it.worldpay.fede.offersmanager.errors.exception.ResourceNotFoundException;
import it.worldpay.fede.offersmanager.model.offer.Offer;

@Service
public class OfferServiceDefault extends OfferService {

	
	@Override
	public void saveOffer(Offer offer) throws DuplicateResourceException{
		
		Offer offerDuplicated = offerDao.findOne(offer.getOfferId());
		
		checkIfOfferIsDuplicated(offerDuplicated);
		  
		offerDao.save(offer);
		  
	}
	
	
	@Override
	public Offer getOffer(Long id) throws ResourceNotFoundException, ProductExpiredException{
		
		Offer offerFound = offerDao.findOne(id);
		
		checkIfOfferIsNotFound(offerFound,id);
			
		return offerFound;
		
	}
	
	@Override
	public void deleteOffer(Long offerId) throws ResourceNotFoundException{
		
		Offer offerNotFound = offerDao.findOne(offerId);
		
		checkIfOfferIsNotFound(offerNotFound, offerId);
		
		offerDao.delete(offerId);
		
	}
	
	
}
