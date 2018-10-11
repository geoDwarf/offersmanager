package it.worldpay.fede.offersmanager.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.worldpay.fede.offersmanager.dao.PastaDao;
import it.worldpay.fede.offersmanager.errors.DuplicateProductException;
import it.worldpay.fede.offersmanager.model.food.Pasta;

@Service
public class PastaService extends BaseService<Pasta> {
	
	@Autowired
	PastaDao pastaDao;
	
	@Override
	public void saveProduct(Pasta pasta) throws DuplicateProductException{
		
		checkForValidityPeriodAndStartingDate(pasta);
		
		setExpiringDateByValidityPeriod(pasta, pasta.getDaysValidityPeriod());
		
		Pasta pastaDuplicated = (Pasta)pastaDao.findByProductId(pasta.getProductId());
		
		checkIfProductIsDuplicated(pastaDuplicated);
		  
		pastaDao.save(pasta);
		  
	}
}
