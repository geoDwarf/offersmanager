package it.worldpay.fede.offersmanager.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.worldpay.fede.offersmanager.dao.GelatoDao;
import it.worldpay.fede.offersmanager.errors.DuplicateProductException;
import it.worldpay.fede.offersmanager.errors.ProductNotFoundException;
import it.worldpay.fede.offersmanager.exceptions.ProductNotFounException;
import it.worldpay.fede.offersmanager.model.Product;
import it.worldpay.fede.offersmanager.model.food.Gelato;

@Service
public class GelatoServiceImpl extends BaseService implements GelatoService {

	
	@Autowired
	 GelatoDao gelatoDao;

	
	
	@Override
	public Gelato getGelato(Long id) throws ProductNotFoundException{
		
		Gelato gelatoFound = gelatoDao.findOne(id);
		if (null == gelatoFound)
			throw new ProductNotFoundException();
		return gelatoFound;
		
	}

	@Override
	public void saveGelato(Gelato gelato) throws DuplicateProductException{
		
		Gelato gelatoDuplicated = (Gelato)gelatoDao.findByProductId(gelato.getProductId());
		
		if(gelatoDuplicated != null)
			throw new DuplicateProductException();
		  gelatoDao.save(gelato);
		  
	}
	
	@Override
	public void deleteGelato(Gelato gelato) throws ProductNotFoundException{
		
		Gelato gelatoNotFound = (Gelato)gelatoDao.findByProductId(gelato.getProductId());
		
		if(gelatoNotFound == null)
			throw new ProductNotFoundException();
		  gelatoDao.delete(gelato);
		  
	}
	
	
}
