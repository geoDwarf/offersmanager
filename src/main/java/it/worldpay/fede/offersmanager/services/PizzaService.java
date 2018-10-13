package it.worldpay.fede.offersmanager.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.worldpay.fede.offersmanager.dao.PizzaDao;
import it.worldpay.fede.offersmanager.errors.DuplicateResourceException;
import it.worldpay.fede.offersmanager.model.food.Pizza;



@Service
public class PizzaService extends BaseService<Pizza>{
	
	@Autowired
	PizzaDao pizzaDao;

	@Override
	public void saveProduct(Pizza pizza) throws DuplicateResourceException{
		
		checkForValidityPeriodAndStartingDate(pizza);
		
		setExpiringDateByValidityPeriod(pizza, pizza.getDaysValidityPeriod());
		
		Pizza pizzaDuplicated = (Pizza)pizzaDao.findByProductId(pizza.getProductId());
		
		checkIfProductIsDuplicated(pizzaDuplicated);
		  
		pizzaDao.save(pizza);
		  
	}
}
