package it.worldpay.fede.offersmanager.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.worldpay.fede.offersmanager.dao.PizzaDao;
import it.worldpay.fede.offersmanager.dao.ProductDao;
import it.worldpay.fede.offersmanager.errors.DuplicateProductException;
import it.worldpay.fede.offersmanager.model.food.Pizza;

@Service
public class PizzaServiceImpl  implements PizzaService{ //extends BaseService
	
	@Autowired
	 PizzaDao pizzaDao;
	
	@Autowired
	ProductDao productDao;

//	@Override
//	public Pizza getPizza(Long id) throws ProductNotFoundException{
//		
//		Pizza pizzaFound = pizzaDao.findOne(id);
//		
//		checkIfProductIsNotFound(pizzaFound,id);
//		
//		chekIfExpiringDateIsBeforeGettingProductTime(pizzaFound);
//		
//		checkIfProductIsExpired(pizzaDao.findOne(id));
//		
//		return pizzaFound;
//		
//	}

//	@Override
//	public void savePizza(Pizza pizza) throws DuplicateProductException{
//		
//		checkForValidityPeriodAndStartingDate(pizza);
//		
//		setExpiringDateByValidityPeriod(pizza, pizza.getDaysValidityPeriod());
//		
//		Pizza pizzaDuplicated = (Pizza)productDao.findByProductId(pizza.getProductId());
//		
//		checkIfProductIsDuplicated(pizzaDuplicated);
//		  
//		pizzaDao.save(pizza);
//		  
//	}
	
//	@Override
//	public void deletePizza(Pizza pizza) throws ProductNotFoundException{
//		
//		Pizza pizzaNotFound = (Pizza)pizzaDao.findByProductId(pizza.getProductId());
//		
//		checkIfProductIsNotFound(pizzaNotFound,pizza.getProductId());
//		
//		pizzaDao.delete(pizza);
//		  
//	}

}
