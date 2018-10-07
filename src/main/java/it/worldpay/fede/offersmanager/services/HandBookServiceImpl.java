package it.worldpay.fede.offersmanager.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.worldpay.fede.offersmanager.dao.HandBookDao;
import it.worldpay.fede.offersmanager.errors.DuplicateProductException;
import it.worldpay.fede.offersmanager.errors.ProductNotFoundException;
import it.worldpay.fede.offersmanager.model.books.HandBook;

@Service
public class HandBookServiceImpl extends BaseService implements HandBookService{
	

	
	@Autowired
	 HandBookDao handBookDao;

	@Override
	public HandBook getHandBook(Long id) throws ProductNotFoundException{
		
		HandBook handBookFound = handBookDao.findOne(id);
		
		checkIfProductIsNotFound(handBookFound,id);
		
		chekIfExpiringDateIsBeforeGettingProductTime(handBookFound);
		
		checkIfProductIsExpired(handBookDao.findOne(id));
		
		return handBookFound;
		
	}

	@Override
	public void saveHandBook(HandBook handBook) throws DuplicateProductException{
		
		checkForValidityPeriodAndStartingDate(handBook);
		
		setExpiringDateByValidityPeriod(handBook, handBook.getDaysValidityPeriod());
		
		HandBook handBookDuplicated = (HandBook)handBookDao.findByProductId(handBook.getProductId());
		
		checkIfProductIsDuplicated(handBookDuplicated);
		  
		handBookDao.save(handBook);
		  
	}
	
	@Override
	public void deleteHandBook(HandBook handBook) throws ProductNotFoundException{
		
		HandBook handBookNotFound = (HandBook)handBookDao.findByProductId(handBook.getProductId());
		
		checkIfProductIsNotFound(handBookNotFound,handBook.getProductId());
		  
	}



}
