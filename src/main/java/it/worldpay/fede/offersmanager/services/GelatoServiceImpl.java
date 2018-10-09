package it.worldpay.fede.offersmanager.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.worldpay.fede.offersmanager.dao.GelatoDao;
import it.worldpay.fede.offersmanager.errors.DuplicateProductException;
import it.worldpay.fede.offersmanager.errors.ProductExpiredException;
import it.worldpay.fede.offersmanager.errors.ProductNotFoundException;
import it.worldpay.fede.offersmanager.model.food.Gelato;

@Service
public class GelatoServiceImpl extends BaseService implements GelatoService {

	
	@Autowired
	 GelatoDao gelatoDao;

	
	@Override
	public void saveGelato(Gelato gelato) throws DuplicateProductException{
		
		checkForValidityPeriodAndStartingDate(gelato);
		
		setExpiringDateByValidityPeriod(gelato, gelato.getDaysValidityPeriod());
		
		Gelato gelatoDuplicated = (Gelato)productDao.findByProductId(gelato.getProductId());
		
		checkIfProductIsDuplicated(gelatoDuplicated);
		 
		gelatoDao.save(gelato);
				
	        };
	 
		 
	
}
