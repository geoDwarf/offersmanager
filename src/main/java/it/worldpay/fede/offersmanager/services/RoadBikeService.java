package it.worldpay.fede.offersmanager.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.worldpay.fede.offersmanager.dao.RoadBikeDao;
import it.worldpay.fede.offersmanager.errors.DuplicateResourceException;
import it.worldpay.fede.offersmanager.model.bikes.RoadBike;
import it.worldpay.fede.offersmanager.model.food.Pizza;


@Service
public class RoadBikeService extends BaseService<RoadBike>{
	
	@Autowired
	RoadBikeDao roadBikeDao;

	
	@Override
	public void saveProduct(RoadBike roadBike) throws DuplicateResourceException{
		
		checkForValidityPeriodAndStartingDate(roadBike);
		
		setExpiringDateByValidityPeriod(roadBike, roadBike.getDaysValidityPeriod());
		
		RoadBike roadBikeDuplicated = (RoadBike)roadBikeDao.findByProductId(roadBike.getProductId());
		
		checkIfProductIsDuplicated(roadBikeDuplicated);
		  
		roadBikeDao.save(roadBikeDuplicated);
		  
	}


}
