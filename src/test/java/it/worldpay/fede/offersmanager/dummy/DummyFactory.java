package it.worldpay.fede.offersmanager.dummy;

import java.util.List;

import org.springframework.stereotype.Component;

import it.worldpay.fede.offersmanager.model.Offer;
import it.worldpay.fede.offersmanager.model.Product;

@Component
public interface DummyFactory {

	public Product getDummyProduct(String product);
	
	public Product getBadRequestDummyProduct(String product);
	
	public Offer getDummyOffer(Long idOffer, List<Product> product);
	
}
