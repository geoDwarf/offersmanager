package it.worldpay.fede.offersmanager.services.product.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.worldpay.fede.offersmanager.dao.product.book.FantasyBookDao;
import it.worldpay.fede.offersmanager.errors.exception.DuplicateResourceException;
import it.worldpay.fede.offersmanager.model.product.book.FantasyBook;
import it.worldpay.fede.offersmanager.services.BaseService;

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
