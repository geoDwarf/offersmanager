package it.worldpay.fede.offersmanager.dao.offer;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.worldpay.fede.offersmanager.model.offer.Offer;

@Repository
public interface OfferDao extends CrudRepository<Offer, Long> {
	
	

}
