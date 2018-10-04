package it.worldpay.fede.offersmanager.dummy;

import org.springframework.stereotype.Component;

import it.worldpay.fede.offersmanager.model.Product;
import it.worldpay.fede.offersmanager.model.food.Gelato;
import it.worldpay.fede.offersmanager.model.food.Pizza;

@Component
public class DummyFactoryImpl implements DummyFactory{

	Long productId = new Long(281);

			public Product getDummyProduct(String product){
		if (product.equalsIgnoreCase("GELATO")){
			return getDummyGelato();
		}else if(product.equalsIgnoreCase("PIZZA")){
			return getDummyPizza();	
		}else{
			return null;
		}
		
	}

private Gelato getDummyGelato(){
	
	boolean isServedWithWippedCream = true;
	return new Gelato(isServedWithWippedCream, productId);
}

private Pizza getDummyPizza(){
	
	return new Pizza(productId);
}


@Override
public Product getBadRequestDummyProduct(String product){
	if (product.equalsIgnoreCase("GELATO")){
		return getBadRequestDummyGelato();
	}else{
		return null;
	}
}


private Gelato getBadRequestDummyGelato(){
	
	boolean isServedWithWippedCream = true;
	Long productId =null ;
	
	return new Gelato(isServedWithWippedCream, productId);
	
}
	



}
