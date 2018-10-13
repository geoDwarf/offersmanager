package it.worldpay.fede.offersmanager.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.worldpay.fede.offersmanager.model.Offer;

@Repository
public interface OfferDao extends CrudRepository<Offer, Long> {
	
	

}
