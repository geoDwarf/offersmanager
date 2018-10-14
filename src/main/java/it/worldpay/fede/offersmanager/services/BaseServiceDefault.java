package it.worldpay.fede.offersmanager.services;

import org.springframework.stereotype.Service;

import it.worldpay.fede.offersmanager.errors.exception.DuplicateResourceException;
import it.worldpay.fede.offersmanager.errors.exception.ProductExpiredException;
import it.worldpay.fede.offersmanager.errors.exception.ResourceNotFoundException;
import it.worldpay.fede.offersmanager.model.product.Product;


@Service
public class BaseServiceDefault  extends BaseService<Product>{ 
	

	@Override
	public void saveProduct(Product product) throws DuplicateResourceException{
		
		checkForValidityPeriodAndStartingDate(product);
		
		setExpiringDateByValidityPeriod(product, product.getDaysValidityPeriod());
		
		Product productDuplicated = (Product)productDao.findByProductId(product.getProductId());
		
		checkIfProductIsDuplicated(productDuplicated);
		  
		productDao.save(product);
		  
	}
	

}
