package it.worldpay.fede.offersmanager.services.product.bike;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.worldpay.fede.offersmanager.dao.product.bike.RoadBikeDao;
import it.worldpay.fede.offersmanager.errors.exception.DuplicateResourceException;
import it.worldpay.fede.offersmanager.model.product.bike.RoadBike;
import it.worldpay.fede.offersmanager.model.product.food.Pizza;
import it.worldpay.fede.offersmanager.services.BaseService;


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
