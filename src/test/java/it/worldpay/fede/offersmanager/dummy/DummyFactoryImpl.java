package it.worldpay.fede.offersmanager.dummy;

import org.springframework.stereotype.Component;

import it.worldpay.fede.offersmanager.model.Product;
import it.worldpay.fede.offersmanager.model.bikes.MountainBike;
import it.worldpay.fede.offersmanager.model.bikes.RoadBike;
import it.worldpay.fede.offersmanager.model.books.FantasyBook;
import it.worldpay.fede.offersmanager.model.books.HandBook;
import it.worldpay.fede.offersmanager.model.food.Gelato;
import it.worldpay.fede.offersmanager.model.food.Pasta;
import it.worldpay.fede.offersmanager.model.food.Pizza;

@Component
public class DummyFactoryImpl implements DummyFactory{

	private  Long  productId = new Long(281);
	private  Long  productIdNull = null;

	public Product getDummyProduct(String product){
		
		if (product.equalsIgnoreCase("GELATO")){
			return getDummyGelato();
		}else if(product.equalsIgnoreCase("PIZZA")){
			return getDummyPizza();	
		}else if(product.equalsIgnoreCase("PASTA")){
			return getDummyPasta();	
		}else if(product.equalsIgnoreCase("MOUNTAINBIKE")){
			return getMountainBike();
		}else if(product.equalsIgnoreCase("ROADBIKE")){
			return getRoadBike();
		}else if(product.equalsIgnoreCase("FANTASYBOOK")){
			return getDummyFantasyBook();
		}else if(product.equalsIgnoreCase("HANDBOOK")){
			return getDummyHandBook();
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


private Pasta getDummyPasta(){
	return new Pasta(productId);
}

private MountainBike getMountainBike(){
	return new MountainBike(productId);
}

private RoadBike getRoadBike(){
	return new RoadBike(productId);
}

private FantasyBook getDummyFantasyBook(){
	return new FantasyBook(productId);
}

private HandBook getDummyHandBook(){
	return new HandBook(productId);
}

@Override
public Product getBadRequestDummyProduct(String product){
	if (product.equalsIgnoreCase("GELATO")){
		return getBadRequestDummyGelato();
	}else if (product.equalsIgnoreCase("PIZZA")){
		return getBadRequestDummyPizza();	
	}else if (product.equalsIgnoreCase("PASTA")){
			return getBadRequestDummyPasta();
	}else if(product.equalsIgnoreCase("MOUNTAINBIKE")){
		return getBadRequestDummyMountainBike();
	}else if(product.equalsIgnoreCase("ROADBIKE")){
			return getBadRequestDummyRoadBike();//
	}else if(product.equalsIgnoreCase("FANTASYBOOK")){
		return getBadRequestDummyFantasyBook();
	}else if(product.equalsIgnoreCase("HANDBOOK")){
			return getBadRequestDummyHandBook();
	}else{
		
		
		return null;
	}
}


private Gelato getBadRequestDummyGelato(){
	
	boolean isServedWithWippedCream = true;
	
	return new Gelato(isServedWithWippedCream, productIdNull);
	
}


private Pizza getBadRequestDummyPizza(){
	return new Pizza(productIdNull);
	
}

private Pasta getBadRequestDummyPasta(){
	return new Pasta(productIdNull);
		
	}

private MountainBike getBadRequestDummyMountainBike(){
	return new MountainBike(productIdNull);
	
}

private RoadBike getBadRequestDummyRoadBike(){

	return new RoadBike(productIdNull);
}

protected FantasyBook getBadRequestDummyFantasyBook(){
	return new FantasyBook(productIdNull);
}

protected HandBook getBadRequestDummyHandBook(){
	return new HandBook(productIdNull);
}
}