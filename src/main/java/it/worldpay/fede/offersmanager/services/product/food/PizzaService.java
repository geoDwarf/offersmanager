package it.worldpay.fede.offersmanager.services.product.food;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.worldpay.fede.offersmanager.dao.product.food.PizzaDao;
import it.worldpay.fede.offersmanager.errors.exception.DuplicateResourceException;
import it.worldpay.fede.offersmanager.model.product.food.Pizza;
import it.worldpay.fede.offersmanager.services.BaseService;



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
