package it.worldpay.fede.offersmanager.services.product.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.worldpay.fede.offersmanager.dao.product.book.HandBookDao;
import it.worldpay.fede.offersmanager.errors.exception.DuplicateResourceException;
import it.worldpay.fede.offersmanager.model.product.book.HandBook;
import it.worldpay.fede.offersmanager.services.BaseService;

@Service
public class HandBookService extends BaseService<HandBook>{
	
	@Autowired
	HandBookDao handBookDao;

	@Override
	public void saveProduct(HandBook handBook) throws DuplicateResourceException{
		
		checkForValidityPeriodAndStartingDate(handBook);
		
		setExpiringDateByValidityPeriod(handBook, handBook.getDaysValidityPeriod());
		
		HandBook handBookDuplicated = (HandBook)handBookDao.findByProductId(handBook.getProductId());
		
		checkIfProductIsDuplicated(handBookDuplicated);
		  
		handBookDao.save(handBook);
		  
	}
}
