package it.worldpay.fede.offersmanager.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.worldpay.fede.offersmanager.dao.GelatoDao;
import it.worldpay.fede.offersmanager.errors.DuplicateProductException;
import it.worldpay.fede.offersmanager.errors.ProductNotFoundException;
import it.worldpay.fede.offersmanager.model.food.Gelato;

@Service
public class GelatoServiceImpl extends BaseService implements GelatoService {

	
	@Autowired
	 GelatoDao gelatoDao;

	
	
	@Override
	public Gelato getGelato(Long id) throws ProductNotFoundException{
		
		Gelato gelatoFound = gelatoDao.findOne(id);
		
		checkIfProductIsNotFound(gelatoFound,id);
		
		chekIfExpiringDateIsBeforeGettingProductTime(gelatoFound);
		
		checkIfProductIsExpired(gelatoFound);
		
		return gelatoFound;
		
	}

	@Override
	public void saveGelato(Gelato gelato) throws DuplicateProductException{
		
		checkForValidityPeriodAndStartingDate(gelato);
		
		setExpiringDateByValidityPeriod(gelato, gelato.getDaysValidityPeriod());
		
		Gelato gelatoDuplicated = (Gelato)gelatoDao.findByProductId(gelato.getProductId());
		
		checkIfProductIsDuplicated(gelatoDuplicated);
		 
		gelatoDao.save(gelato);
		  
	}
	
	@Override
	public void deleteGelato(Gelato gelato) throws ProductNotFoundException{
		
		Gelato gelatoNotFound = (Gelato)gelatoDao.findByProductId(gelato.getProductId());
		
		checkIfProductIsNotFound(gelatoNotFound,gelato.getProductId());
		
		gelatoDao.delete(gelato);
		  
	}
	
	
}
