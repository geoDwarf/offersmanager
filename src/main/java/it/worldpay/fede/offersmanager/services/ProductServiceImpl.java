package it.worldpay.fede.offersmanager.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.worldpay.fede.offersmanager.dao.ProductDao;
import it.worldpay.fede.offersmanager.errors.DuplicateProductException;
import it.worldpay.fede.offersmanager.errors.ProductExpiredException;
import it.worldpay.fede.offersmanager.errors.ProductNotFoundException;
import it.worldpay.fede.offersmanager.model.Product;
import it.worldpay.fede.offersmanager.model.food.Pizza;



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
	public void deleteProduct(Product product) throws ProductNotFoundException{
		
		Product productNotFound = (Product)productDao.findByProductId(product.getProductId());
		
		checkIfProductIsNotFound(productNotFound,product.getProductId());
		
		productDao.delete(product);
		
		
		  
		
	}
	

	}
	
	
	
	
	
	

