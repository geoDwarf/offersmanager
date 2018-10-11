package it.worldpay.fede.offersmanager.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.worldpay.fede.offersmanager.dao.MountainBikeDao;
import it.worldpay.fede.offersmanager.errors.DuplicateProductException;
import it.worldpay.fede.offersmanager.model.bikes.MountainBike;
import it.worldpay.fede.offersmanager.model.books.FantasyBook;

@Service
public class MountainBikeService extends BaseService<MountainBike> {
	
	@Autowired
	MountainBikeDao mountainBikeDao;
	
	@Override
	public void saveProduct(MountainBike mountainBike) throws DuplicateProductException{
		
		checkForValidityPeriodAndStartingDate(mountainBike);
		
		setExpiringDateByValidityPeriod(mountainBike, mountainBike.getDaysValidityPeriod());
		
		MountainBike mountainBikeDuplicated = (MountainBike)mountainBikeDao .findByProductId(mountainBike.getProductId());
		
		checkIfProductIsDuplicated(mountainBikeDuplicated);
		  
		mountainBikeDao.save(mountainBike);
		  
	}

	
}
