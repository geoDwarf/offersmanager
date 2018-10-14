package it.worldpay.fede.offersmanager.services.product.food;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.worldpay.fede.offersmanager.dao.product.food.PastaDao;
import it.worldpay.fede.offersmanager.errors.exception.DuplicateResourceException;
import it.worldpay.fede.offersmanager.model.product.food.Pasta;
import it.worldpay.fede.offersmanager.services.BaseService;

@Service
public class PastaService extends BaseService<Pasta> {
	
	@Autowired
	PastaDao pastaDao;
	
	@Override
	public void saveProduct(Pasta pasta) throws DuplicateResourceException{
		
		checkForValidityPeriodAndStartingDate(pasta);
		
		setExpiringDateByValidityPeriod(pasta, pasta.getDaysValidityPeriod());
		
		Pasta pastaDuplicated = (Pasta)pastaDao.findByProductId(pasta.getProductId());
		
		checkIfProductIsDuplicated(pastaDuplicated);
		  
		pastaDao.save(pasta);
		  
	}
}
