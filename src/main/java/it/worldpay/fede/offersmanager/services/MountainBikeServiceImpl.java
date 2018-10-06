package it.worldpay.fede.offersmanager.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.worldpay.fede.offersmanager.dao.MountainBikeDao;
import it.worldpay.fede.offersmanager.errors.DuplicateProductException;
import it.worldpay.fede.offersmanager.errors.ProductNotFoundException;
import it.worldpay.fede.offersmanager.model.bikes.MountainBike;

@Service
public class MountainBikeServiceImpl extends BaseService implements MountainBikeService  {

	@Autowired
	 MountainBikeDao mountainBikeDao;

	@Override
	public MountainBike getMountainBike(Long id) throws ProductNotFoundException{
		
		MountainBike mountainBikeFound = mountainBikeDao.findOne(id);
		
		checkIfProductIsNotFound(mountainBikeFound);
		
		chekIfExpiringDateIsBeforeGettingProductTime(mountainBikeFound);
		
		checkIfProductIsExpired(mountainBikeFound);
		
		return mountainBikeFound;
		
	}

	@Override
	public void saveMountainBike(MountainBike mountainBike) throws DuplicateProductException{
		
		checkForValidityPeriodAndStartingDate(mountainBike);
		
		setExpiringDateByValidityPeriod(mountainBike, mountainBike.getDaysValidityPeriod());
		
		MountainBike mountainBikeDuplicated = (MountainBike)mountainBikeDao.findByProductId(mountainBike.getProductId());
		
		checkIfProductIsDuplicated(mountainBikeDuplicated);
		 
		mountainBikeDao.save(mountainBike);
		  
	}
	
	@Override
	public void deleteMountainBike(MountainBike mountainBike) throws ProductNotFoundException{
		
		MountainBike mountainBikeNotFound = (MountainBike)mountainBikeDao.findByProductId(mountainBike.getProductId());
		
		checkIfProductIsNotFound(mountainBikeNotFound);
		
		mountainBikeDao.delete(mountainBike);
		  
	}
	
	
	
}
