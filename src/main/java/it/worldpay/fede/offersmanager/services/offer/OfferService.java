package it.worldpay.fede.offersmanager.services.offer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.worldpay.fede.offersmanager.dao.offer.OfferDao;
import it.worldpay.fede.offersmanager.errors.exception.DuplicateResourceException;
import it.worldpay.fede.offersmanager.errors.exception.ResourceNotFoundException;
import it.worldpay.fede.offersmanager.model.offer.Offer;

@Service
public abstract class OfferService {
	
		@Autowired
		OfferDao offerDao;
	
		public abstract void saveOffer(Offer offer) throws DuplicateResourceException;
		
		public abstract void deleteOffer(Long idOffer) throws ResourceNotFoundException;
		
		public abstract Offer getOffer(Long idOffer) throws ResourceNotFoundException;
		
		
		
		protected void checkIfOfferIsDuplicated(Offer offer) throws DuplicateResourceException{
			
			if(offer != null)
				throw new DuplicateResourceException("A offer with the following id already exists: ", offer);
		 } 
		
		protected void checkIfOfferIsNotFound(Offer offer,Long offerId) throws ResourceNotFoundException{
		
			if (null == offer)
					throw new ResourceNotFoundException("No offer found with the following Id ",offerId );
		}
		

}
