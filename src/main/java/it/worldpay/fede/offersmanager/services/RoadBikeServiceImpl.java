package it.worldpay.fede.offersmanager.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.worldpay.fede.offersmanager.dao.RoadBikeDao;
import it.worldpay.fede.offersmanager.errors.DuplicateProductException;
import it.worldpay.fede.offersmanager.errors.ProductNotFoundException;
import it.worldpay.fede.offersmanager.model.bikes.RoadBike;

@Service
public class RoadBikeServiceImpl extends BaseService implements RoadBikeService{
	

	
	@Autowired
	RoadBikeDao roadBikeDao;

	@Override
	public RoadBike getRoadBike(Long id) throws ProductNotFoundException{
		
		RoadBike roadBikeFound = roadBikeDao.findOne(id);
		
		checkIfProductIsNotFound(roadBikeFound,id);
		
		chekIfExpiringDateIsBeforeGettingProductTime(roadBikeFound);
		
		checkIfProductIsExpired(roadBikeDao.findOne(id));
		
		return roadBikeFound;
		
	}

	@Override
	public void saveRoadBike(RoadBike roadBike) throws DuplicateProductException{
		
		checkForValidityPeriodAndStartingDate(roadBike);
		
		setExpiringDateByValidityPeriod(roadBike, roadBike.getDaysValidityPeriod());
		
		RoadBike roadBikeDuplicated = (RoadBike)roadBikeDao.findByProductId(roadBike.getProductId());
		
		checkIfProductIsDuplicated(roadBikeDuplicated);
		  
		roadBikeDao.save(roadBike);
		  
	}
	
	@Override
	public void deleteRoadBike(RoadBike roadBike) throws ProductNotFoundException{
		
		RoadBike roadBikeNotFound = (RoadBike)roadBikeDao.findByProductId(roadBike.getProductId());
		
		checkIfProductIsNotFound(roadBikeNotFound,roadBike.getProductId());
		  
	}



}
