package it.worldpay.fede.offersmanager.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.worldpay.fede.offersmanager.dao.HandBookDao;
import it.worldpay.fede.offersmanager.errors.DuplicateProductException;
import it.worldpay.fede.offersmanager.errors.ProductNotFoundException;
import it.worldpay.fede.offersmanager.model.books.HandBook;

@Service
public class HandBookServiceImpl  implements HandBookService{ //extends BaseService
	
	
//	@Autowired
//	 HandBookDao handBookDao;
//	
//
//	@Override
//	public void saveHandBook(HandBook handBook) throws DuplicateProductException{
//		
//		checkForValidityPeriodAndStartingDate(handBook);
//		
//		setExpiringDateByValidityPeriod(handBook, handBook.getDaysValidityPeriod());
//		
//		HandBook handBookDuplicated = (HandBook)productDao.findByProductId(handBook.getProductId());
//		
//		checkIfProductIsDuplicated(handBookDuplicated);
//		  
//		handBookDao.save(handBook);
//		  
//	}
	




}
