package it.worldpay.fede.offersmanager.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.worldpay.fede.offersmanager.dao.PizzaDao;
import it.worldpay.fede.offersmanager.errors.DuplicateProductException;
import it.worldpay.fede.offersmanager.errors.ProductNotFoundException;
import it.worldpay.fede.offersmanager.model.food.Pizza;

@Service
public class PizzaServiceImpl extends BaseService implements PizzaService{
	
	@Autowired
	 PizzaDao pizzaDao;

	
	
	@Override
	public Pizza getPizza(Long id) throws ProductNotFoundException{
		
		Pizza pizzaFound = pizzaDao.findOne(id);
		if (null == pizzaFound)
			throw new ProductNotFoundException();
		return pizzaFound;
		
	}

	@Override
	public void savePizza(Pizza pizza) throws DuplicateProductException{
		
		checkForValidityPeriodAndStartingDate(pizza);
		
		setExpiringDateByValidityPeriod(pizza, pizza.getDaysValidityPeriod());
		
		Pizza pizzaDuplicated = (Pizza)pizzaDao.findByProductId(pizza.getProductId());
		
		checkIfProductIsDuplicated(pizzaDuplicated);
		  
		pizzaDao.save(pizza);
		  
	}
	
	@Override
	public void deletePizza(Pizza pizza) throws ProductNotFoundException{
		
		Pizza pizzaNotFound = (Pizza)pizzaDao.findByProductId(pizza.getProductId());
		
		if(pizzaNotFound == null)
			throw new ProductNotFoundException();
		 pizzaDao.delete(pizza);
		  
	}

}
