package it.worldpay.fede.offersmanager.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.worldpay.fede.offersmanager.dao.product.ProductDao;
import it.worldpay.fede.offersmanager.errors.exception.DuplicateResourceException;
import it.worldpay.fede.offersmanager.errors.exception.MissingParameterException;
import it.worldpay.fede.offersmanager.errors.exception.ProductExpiredException;
import it.worldpay.fede.offersmanager.errors.exception.ResourceNotFoundException;
import it.worldpay.fede.offersmanager.model.product.Product;
import it.worldpay.fede.offersmanager.utils.DateTime;
import it.worldpay.fede.offersmanager.utils.DateUtils;


@Service
public abstract class BaseService <T extends Product>{
	
	/*
	 The testing variable is used just for allowing testing the application from an external client.
	 * It allows to save an offer with a expiring date earlier than now if the save method is called not by a testing method .
	 * It should be oblviously removed in a production environment
	 */
	public  boolean testing;
	
	@Autowired
	DateTime dateTime;
	
	@Autowired 
	DateUtils dateUtils;
	
	@Autowired
	ProductDao<Product> productDao;
	
 
	public abstract void saveProduct(T product) throws DuplicateResourceException;
	
	
	public Product getProduct(Long id) throws ResourceNotFoundException, ProductExpiredException{
		
		Product productFound = productDao.findOne(id);
		
		checkIfProductIsNotFound(productFound,id);
			
		chekIfExpiringDateIsBeforeGettingProductTime(productFound);
			
		checkIfProductIsExpired(productDao.findOne(id));
		
		return productFound;
		
	}
	
	
	
	public void deleteProduct(Long productId) throws ResourceNotFoundException{
		
		Product productNotFound = (Product)productDao.findByProductId(productId);
		
		checkIfProductIsNotFound(productNotFound,productId);
		
		productDao.delete(productId);
		
	}
	
	
	
	protected void checkForValidityPeriodAndStartingDate(Product product) throws MissingParameterException {
		
		if (product.getDaysValidityPeriod() == 0 || product.getOfferStartingDate() == null)
			throw new MissingParameterException("missing parameter, please check your offer validity period and starting date", product);
		}

	
	protected void setExpiringDateByValidityPeriod(Product product,int validityPeriod){
		
		product.setOfferExpiringDate(dateUtils.addDates(product.getOfferStartingDate(), validityPeriod));
		
		if (product.getOfferExpiringDate().before(new Date())&& testing){
			setProductToExpired(product);
			throw new ProductExpiredException("the product you try to fetch is expired", product);
		}
	}
	
	protected void checkIfProductIsExpired(Product product) throws ProductExpiredException{
		if (product.isExpired())
			throw new ProductExpiredException("the product you try to fetch is expired", product);
	}
	
	protected void setProductToExpired(Product product){
 		product.setExpired(true);
 		
	}
	
	protected void checkIfProductIsDuplicated(Product product) throws DuplicateResourceException{
	
		if(product != null)
			throw new DuplicateResourceException("A product with the following id already exists: ", product);
	 } 
	
	protected void checkIfProductIsNotFound(Product product,Long productId) throws ResourceNotFoundException{
	
		if (null == product)
				throw new ResourceNotFoundException("No product found with the following Id ",productId );
	}
	
	protected void chekIfExpiringDateIsBeforeGettingProductTime(Product productFound){
		Date now = dateTime.getDate();
		if (productFound.getOfferExpiringDate().before(now))
			setProductToExpired(productFound);	
	}
}
