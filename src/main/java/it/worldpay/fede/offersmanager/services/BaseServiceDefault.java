package it.worldpay.fede.offersmanager.services;

import org.springframework.stereotype.Service;

import it.worldpay.fede.offersmanager.errors.DuplicateProductException;
import it.worldpay.fede.offersmanager.errors.ProductExpiredException;
import it.worldpay.fede.offersmanager.errors.ProductNotFoundException;
import it.worldpay.fede.offersmanager.model.Product;


@Service
public class BaseServiceDefault  extends BaseService<Product>{ 
	

	@Override
	public void saveProduct(Product product) throws DuplicateProductException{
		
		checkForValidityPeriodAndStartingDate(product);
		
		setExpiringDateByValidityPeriod(product, product.getDaysValidityPeriod());
		
		Product productDuplicated = (Product)productDao.findByProductId(product.getProductId());
		
		checkIfProductIsDuplicated(productDuplicated);
		  
		productDao.save(product);
		  
	}
	

}