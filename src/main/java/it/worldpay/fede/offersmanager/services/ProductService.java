package it.worldpay.fede.offersmanager.services;

import org.springframework.stereotype.Service;

import it.worldpay.fede.offersmanager.errors.DuplicateProductException;
import it.worldpay.fede.offersmanager.errors.ProductNotFoundException;
import it.worldpay.fede.offersmanager.model.Product;


@Service
public interface ProductService {
	
	
	Product getProduct(Long id);
	
	//Product saveProduct(Product product) throws DuplicateProductException;;

	void deleteProduct(Product product) throws ProductNotFoundException;
	
	//Product getProductById(Long id);
	

	
}
