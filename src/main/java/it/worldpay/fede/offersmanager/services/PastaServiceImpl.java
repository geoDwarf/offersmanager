package it.worldpay.fede.offersmanager.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.worldpay.fede.offersmanager.dao.PastaDao;
import it.worldpay.fede.offersmanager.errors.DuplicateProductException;
import it.worldpay.fede.offersmanager.errors.ProductNotFoundException;
import it.worldpay.fede.offersmanager.model.food.Pasta;

@Service
public class PastaServiceImpl  implements  PastaService{//extends BaseService

	@Autowired
	 PastaDao pastaDao;

	
	
//	@Override
//	public Pasta getPasta(Long id) throws ProductNotFoundException{
//		
//		Pasta pastaFound = pastaDao.findOne(id);
//		
//		checkIfProductIsNotFound(pastaFound,id);
//		
//		chekIfExpiringDateIsBeforeGettingProductTime(pastaFound);
//		
//		checkIfProductIsExpired(pastaFound);
//		
//		return pastaFound;
//		
//	}

//	@Override
//	public void savePasta(Pasta pasta) throws DuplicateProductException{
//		
//		checkForValidityPeriodAndStartingDate(pasta);
//		
//		setExpiringDateByValidityPeriod(pasta, pasta.getDaysValidityPeriod());
//		
//		Pasta pastaDuplicated = (Pasta)productDao.findByProductId(pasta.getProductId());
//		
//		checkIfProductIsDuplicated(pastaDuplicated);
//		 
//		pastaDao.save(pasta);
//		  
//	}
	
//	@Override
//	public void deletePasta(Pasta pasta) throws ProductNotFoundException{
//		
//		Pasta pastaNotFound = (Pasta)pastaDao.findByProductId(pasta.getProductId());
//		
//		checkIfProductIsNotFound(pastaNotFound,pasta.getProductId());
//		
//		pastaDao.delete(pasta);
//		  
//	}
//	
}
