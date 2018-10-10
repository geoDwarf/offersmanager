package it.worldpay.fede.offersmanager.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.worldpay.fede.offersmanager.dao.RoadBikeDao;
import it.worldpay.fede.offersmanager.errors.DuplicateProductException;
import it.worldpay.fede.offersmanager.errors.ProductNotFoundException;
import it.worldpay.fede.offersmanager.model.bikes.RoadBike;

@Service
public class RoadBikeServiceImpl  implements RoadBikeService{ //extends BaseService
	

	
//	@Autowired
//	RoadBikeDao roadBikeDao;
//
//
//	@Override
//	public void saveRoadBike(RoadBike roadBike) throws DuplicateProductException{
//		
//		checkForValidityPeriodAndStartingDate(roadBike);
//		
//		setExpiringDateByValidityPeriod(roadBike, roadBike.getDaysValidityPeriod());
//		
//		RoadBike roadBikeDuplicated = (RoadBike)productDao.findByProductId(roadBike.getProductId());
//		
//		checkIfProductIsDuplicated(roadBikeDuplicated);
//		  
//		roadBikeDao.save(roadBike);
		  
//	}
	




}
