package it.worldpay.fede.offersmanager.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.worldpay.fede.offersmanager.dao.ProductDao;
import it.worldpay.fede.offersmanager.errors.DuplicateProductException;
import it.worldpay.fede.offersmanager.errors.ProductExpiredException;
import it.worldpay.fede.offersmanager.errors.ProductNotFoundException;
import it.worldpay.fede.offersmanager.model.Product;



@Service
public class ProductServiceImpl extends BaseService implements ProductService {

	@Autowired
	ProductDao<Product> productDao;


	@Override
	public Product getProduct(Long id) throws ProductNotFoundException, ProductExpiredException{
		
		Product productFound = productDao.findOne(id);
		
		checkIfProductIsNotFound(productFound,id);
			
		chekIfExpiringDateIsBeforeGettingProductTime(productFound);
			
		checkIfProductIsExpired(productDao.findOne(id));
		
		return productFound;
		
	}

	@Override
	public Product saveProduct(Product product) throws DuplicateProductException{
		
		checkForValidityPeriodAndStartingDate(product);
		
		setExpiringDateByValidityPeriod(product, product.getDaysValidityPeriod());
		
		Product productDuplicated = productDao.findByProductId(product.getProductId());
		
		if(productDuplicated != null)
			throw new DuplicateProductException("A product with the following id already exists", productDuplicated.getProductId());
		 
		  return  productDao.save(product);
	}
	
	@Override
	public void deleteProduct(Product product) throws ProductNotFoundException{
		
	}
	

	}
	
	
	
	
	
	

