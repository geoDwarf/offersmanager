package it.worldpay.fede.offersmanager.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.worldpay.fede.offersmanager.errors.ProductExpiredException;
import it.worldpay.fede.offersmanager.exceptions.MissingParameterException;
import it.worldpay.fede.offersmanager.model.Product;
import it.worldpay.fede.offersmanager.utils.DateUtils;


@Service
public class BaseService {
	
	
	@Autowired 
	DateUtils dateUtils;
	
	protected void checkForValidityPeriodAndStartingDate(Product product) throws MissingParameterException {
		
		if (product.getDaysValidityPeriod() == 0 || product.getOfferStartingDate() == null)
			throw new MissingParameterException();
		
	}

	
	protected Product setExpiringDateByValidityPeriod(Product product,int validityPeriod){
		
		product.setOfferExpiringDate(dateUtils.addDates(product.getOfferStartingDate(), validityPeriod));
		
		if (product.getOfferExpiringDate().before(new Date())){
			setProductToExpired(product);
			throw new ProductExpiredException();
		}
		
		return product;
	}
	

	
	protected void checkIfProductIsExpired(Product product) throws ProductExpiredException{
		
		if (product.isExpired())
			throw new ProductExpiredException();
		
	}
	
	protected void setProductToExpired(Product product){
		product.setExpired(true);
	}
}