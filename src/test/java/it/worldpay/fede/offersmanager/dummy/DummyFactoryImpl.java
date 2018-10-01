package it.worldpay.fede.offersmanager.dummy;

import org.springframework.stereotype.Component;

import it.worldpay.fede.offersmanager.model.Product;
import it.worldpay.fede.offersmanager.model.food.Gelato;

@Component
public class DummyFactoryImpl implements DummyFactory{

	
public Product getDummyProduct(String product){
		if (product.equalsIgnoreCase("GELATO")){
			return getDummyGelato();
		}else{
			return null;
		}
		
	}
	

private Gelato getDummyGelato(){
	boolean isServedWithWippedCream = true;
	Long productId =new Long(281) ;
	
	return new Gelato(isServedWithWippedCream, productId);
}
}
