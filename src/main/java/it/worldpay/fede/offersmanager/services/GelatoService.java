package it.worldpay.fede.offersmanager.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.worldpay.fede.offersmanager.dao.GelatoDao;
import it.worldpay.fede.offersmanager.errors.DuplicateResourceException;
import it.worldpay.fede.offersmanager.model.food.Gelato;

@Service
public class GelatoService extends BaseService<Gelato> {
	
	@Autowired
	GelatoDao gelatoDao;	
	
	@Override
	public void saveProduct(Gelato gelato) throws DuplicateResourceException{
		
		checkForValidityPeriodAndStartingDate(gelato);
		
		setExpiringDateByValidityPeriod(gelato, gelato.getDaysValidityPeriod());
		
		Gelato gelatoDuplicated = (Gelato)gelatoDao.findByProductId(gelato.getProductId());
		
		checkIfProductIsDuplicated(gelatoDuplicated);
		  
		gelatoDao.save(gelato);
		  
	}
}
