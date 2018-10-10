package it.worldpay.fede.offersmanager.services;

import org.springframework.stereotype.Service;

import it.worldpay.fede.offersmanager.errors.DuplicateProductException;
import it.worldpay.fede.offersmanager.errors.ProductExpiredException;
import it.worldpay.fede.offersmanager.errors.ProductNotFoundException;
import it.worldpay.fede.offersmanager.model.Product;


@Service
public class BaseServiceDefault  extends BaseService{
	
	@Override
	public Product getProduct(Long id) throws ProductNotFoundException, ProductExpiredException{
		
		Product productFound = productDao.findOne(id);
		
		checkIfProductIsNotFound(productFound,id);
			
		chekIfExpiringDateIsBeforeGettingProductTime(productFound);
			
		checkIfProductIsExpired(productDao.findOne(id));
		
		return productFound;
		
	}

	@Override
	public void deleteProduct(Product product) throws ProductNotFoundException{
		
		Product productNotFound = (Product)productDao.findByProductId(product.getProductId());
		
		checkIfProductIsNotFound(productNotFound,product.getProductId());
		
		productDao.delete(product);
		
	}
	
	@Override
	public void saveProduct(Product product) throws DuplicateProductException{
		
		checkForValidityPeriodAndStartingDate(product);
		
		setExpiringDateByValidityPeriod(product, product.getDaysValidityPeriod());
		
		Product productDuplicated = (Product)productDao.findByProductId(product.getProductId());
		
		checkIfProductIsDuplicated(productDuplicated);
		  
		productDao.save(product);
		  
	}
	

}
