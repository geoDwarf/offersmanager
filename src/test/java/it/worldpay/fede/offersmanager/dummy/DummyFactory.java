package it.worldpay.fede.offersmanager.dummy;

import org.springframework.stereotype.Component;

import it.worldpay.fede.offersmanager.model.Product;

@Component
public interface DummyFactory {

	public Product getDummyProduct(String product);
	
	public Product getBadRequestDummyProduct(String product);
	
}
