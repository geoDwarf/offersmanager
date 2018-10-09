package it.worldpay.fede.offersmanager.services;

import org.springframework.stereotype.Service;

import it.worldpay.fede.offersmanager.errors.DuplicateProductException;
import it.worldpay.fede.offersmanager.errors.ProductNotFoundException;
import it.worldpay.fede.offersmanager.model.bikes.RoadBike;


@Service
public interface RoadBikeService {
	
	//RoadBike getRoadBike(Long id);
	
	void saveRoadBike(RoadBike roadBike) throws DuplicateProductException;
	
	//void deleteRoadBike(RoadBike roadBike)throws ProductNotFoundException;


}
