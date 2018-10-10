package it.worldpay.fede.offersmanager.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.worldpay.fede.offersmanager.dao.FantasyBookDao;
import it.worldpay.fede.offersmanager.dao.ProductDao;
import it.worldpay.fede.offersmanager.errors.DuplicateProductException;
import it.worldpay.fede.offersmanager.model.books.FantasyBook;

@Service
public class FantasyBookServiceImpl implements FantasyBookService{ //extends BaseService 
	

	
	@Autowired
	FantasyBookDao fantasyBookDao;
	

	
//	@Override
//	public FantasyBook getFantasyBook(Long id) throws ProductNotFoundException{
//		
//		FantasyBook fantasyBookFound = fantasyBookDao.findOne(id);
//		
//		checkIfProductIsNotFound(fantasyBookFound,id);
//		
//		chekIfExpiringDateIsBeforeGettingProductTime(fantasyBookFound);
//		
//		checkIfProductIsExpired(fantasyBookDao.findOne(id));
//		
//		return fantasyBookFound;
//		
//	}
//
//	@Override
//	public void saveFantasyBook(FantasyBook fantasyBook) throws DuplicateProductException{
//		
//		checkForValidityPeriodAndStartingDate(fantasyBook);
//		
//		setExpiringDateByValidityPeriod(fantasyBook, fantasyBook.getDaysValidityPeriod());
//		
//		FantasyBook fantasyBookDuplicated = (FantasyBook)fantasyBookDao.findByProductId(fantasyBook.getProductId());
//		
//		checkIfProductIsDuplicated(fantasyBookDuplicated);
//		  
//		fantasyBookDao.save(fantasyBook);
//		  
//	}
	
//	@Override
//	public void deleteFantasyBook(FantasyBook fantasyBook) throws ProductNotFoundException{
//		
//		FantasyBook fantasyBookNotFound = (FantasyBook)fantasyBookDao.findByProductId(fantasyBook.getProductId());
//		
//		checkIfProductIsNotFound(fantasyBookNotFound,fantasyBook.getProductId());
//		  
//	}


	
	

}
