package it.worldpay.fede.offersmanager.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.worldpay.fede.offersmanager.dao.GelatoDao;
import it.worldpay.fede.offersmanager.dao.ProductDao;
import it.worldpay.fede.offersmanager.exceptions.ProductNotFounException;
import it.worldpay.fede.offersmanager.model.Product;


@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	ProductDao<Product> productDao;

	@Autowired
	 GelatoDao<Product> gelatoDao;

	
	
	@Override
	public Product getProduct(Long id) throws ProductNotFounException{
		
		Product productFound = productDao.findOne(id);
		if (null == productFound)
			throw new ProductNotFounException();
		return productFound;
		
	}

	@Override
	public Product saveProduct(Product product){
		
		productDao.save(product);
		return  productDao.save(product);
	}
	
	
}
