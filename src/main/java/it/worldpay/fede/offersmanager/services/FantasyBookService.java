package it.worldpay.fede.offersmanager.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.worldpay.fede.offersmanager.dao.FantasyBookDao;
import it.worldpay.fede.offersmanager.errors.DuplicateResourceException;
import it.worldpay.fede.offersmanager.model.books.FantasyBook;

@Service
public class FantasyBookService extends BaseService<FantasyBook>{ 
	
	@Autowired
	FantasyBookDao fantasyBookDao;
	
	@Override
	public void saveProduct(FantasyBook fantasyBook) throws DuplicateResourceException{
		
		checkForValidityPeriodAndStartingDate(fantasyBook);
		
		setExpiringDateByValidityPeriod(fantasyBook, fantasyBook.getDaysValidityPeriod());
		
		FantasyBook fantasyBookDuplicated = (FantasyBook)fantasyBookDao.findByProductId(fantasyBook.getProductId());
		
		checkIfProductIsDuplicated(fantasyBookDuplicated);
		  
		fantasyBookDao.save(fantasyBook);
		  
	}
	


	
	

}
