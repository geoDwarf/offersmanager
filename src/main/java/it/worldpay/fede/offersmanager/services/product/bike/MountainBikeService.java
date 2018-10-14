package it.worldpay.fede.offersmanager.services.product.bike;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.worldpay.fede.offersmanager.dao.product.bike.MountainBikeDao;
import it.worldpay.fede.offersmanager.errors.exception.DuplicateResourceException;
import it.worldpay.fede.offersmanager.model.product.bike.MountainBike;
import it.worldpay.fede.offersmanager.model.product.book.FantasyBook;
import it.worldpay.fede.offersmanager.services.BaseService;

@Service
public class MountainBikeService extends BaseService<MountainBike> {
	
	@Autowired
	MountainBikeDao mountainBikeDao;
	
	@Override
	public void saveProduct(MountainBike mountainBike) throws DuplicateResourceException{
		
		checkForValidityPeriodAndStartingDate(mountainBike);
		
		setExpiringDateByValidityPeriod(mountainBike, mountainBike.getDaysValidityPeriod());
		
		MountainBike mountainBikeDuplicated = (MountainBike)mountainBikeDao .findByProductId(mountainBike.getProductId());
		
		checkIfProductIsDuplicated(mountainBikeDuplicated);
		  
		mountainBikeDao.save(mountainBike);
		  
	}

	
}
