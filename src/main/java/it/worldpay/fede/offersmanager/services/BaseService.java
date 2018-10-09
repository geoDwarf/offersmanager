package it.worldpay.fede.offersmanager.services;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;

import it.worldpay.fede.offersmanager.dao.ProductDao;
import it.worldpay.fede.offersmanager.errors.DuplicateProductException;
import it.worldpay.fede.offersmanager.errors.MissingParameterException;
import it.worldpay.fede.offersmanager.errors.ProductExpiredException;
import it.worldpay.fede.offersmanager.errors.ProductNotFoundException;
import it.worldpay.fede.offersmanager.model.Product;
import it.worldpay.fede.offersmanager.utils.DateTime;
import it.worldpay.fede.offersmanager.utils.DateUtils;


@Service

public class BaseService {
	
	public  boolean testing;
	
	@Autowired
	DateTime dateTime;
	
	@Autowired 
	DateUtils dateUtils;
	
	@Autowired
	ProductDao<Product> productDao;
	
	Product productExpired;
	
	protected void checkForValidityPeriodAndStartingDate(Product product) throws MissingParameterException {
		
		if (product.getDaysValidityPeriod() == 0 || product.getOfferStartingDate() == null)
			throw new MissingParameterException("missing parameter, please check your offer validity period and starting date", product);
		}

	protected Product setExpiringDateByValidityPeriod(Product product,int validityPeriod){
		
		product.setOfferExpiringDate(dateUtils.addDates(product.getOfferStartingDate(), validityPeriod));
		
		if (product.getOfferExpiringDate().before(new Date()) && testing){
			setProductToExpired(product);
			throw new ProductExpiredException("the product you try to fetch is expired", product);
		}
		
		return product;
	}
	
	protected void checkIfProductIsExpired(Product product) throws ProductExpiredException{
		if (product.isExpired())
			throw new ProductExpiredException("the product you try to fetch is expired", product);
	}
	
	protected void setProductToExpired(Product product){
 		product.setExpired(true);
 		
	}
	
	protected void checkIfProductIsDuplicated(Product product) throws DuplicateProductException{
	
		if(product != null)
			throw new DuplicateProductException("A product with the following id already exists: ", product);
	 } 
	
	protected void checkIfProductIsNotFound(Product product,Long productId) throws ProductNotFoundException{
	
		if (null == product)
				throw new ProductNotFoundException("No product found with the following Id ",productId );
	}
	
	protected void chekIfExpiringDateIsBeforeGettingProductTime(Product productFound){
		Date now = dateTime.getDate();
		if (productFound.getOfferExpiringDate().before(now))
			setProductToExpired(productFound);	
	}
	

	protected void setExpiringDateByScheduler(Product product,int delay){
		
		
		ScheduledExecutorService scheduler  = Executors.newSingleThreadScheduledExecutor();
		Product productExpired ;
		Runnable task = new Task(product);
		{
			
		//private Product productExpired;
			
         
            
//            private void saveExpiredproduct(){
//            	productDao.save(productExpired);
//            }
        };
        
        
        
        scheduler.schedule(task, delay, TimeUnit.SECONDS);
        scheduler.shutdown();
		
	}
	
	//@EnableJpaRepositories("dao")
	public class Task  implements Runnable{
		
			Product product;
			
			public Task(Product product){
				this.product = product;
			}
			
		@Override
		   public void run() {
           	
           setProductToExpired(product);
           	
           	productDao.delete(productExpired);
           	
           	productDao.save(productExpired);
           	
           	Long id = productExpired.getProductId();
           	Product found = productDao.findOne(id);
           	
           	System.out.println(found.getProductId());
           }
	}
	
}
