package it.worldpay.fede.offersmanager.services;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import it.worldpay.fede.offersmanager.dao.ProductDao;
import it.worldpay.fede.offersmanager.dummy.DummyFactory;
import it.worldpay.fede.offersmanager.exceptions.ProductNotFounException;
import it.worldpay.fede.offersmanager.model.Product;

@DataJpaTest
@RunWith(SpringRunner.class)
public class ProductServiceTest {
	
	@Before
	public void initializeTestVariable(){
		productDummy = dummyFactory.getDummyProduct("GELATO");
	}
	
	@Autowired
    private ProductService productService ;

	@Autowired
	DummyFactory dummyFactory;
	
	@Autowired
	ProductDao<Product> productDao;
	
	private Product productDummy;
	
	private Product productSaved;
	
	private Product productUpdated;
	
	private Product productFetched;
	
	@Test
	public void whenProductIsAdded_itIsPossibleToFetchItById(){

		productService.saveProduct(productDummy);
		
		productFetched = productService.getProduct(new Long(281));
		
		assertEquals(productFetched.getProductId(), productDummy.getProductId());
	}
	
	@Test 
	public void whenProductIsAdded_thanItIsPossibleToUpdateIt(){
		
		saveAndUpdateProduct();
		
		assertEquals(productSaved.getProductId(),productUpdated.getProductId());
	} 
	
	@Test
	public void whenProductIsUpdated_returnObjectsAreEquals(){
		
		saveAndUpdateProduct();
		
		assertEquals(productSaved, productUpdated);
		
	}
	
	
	@Test(expected = ProductNotFounException.class)
	public void whenProductIsNotFound_ExceptionIsThrown(){
		
		productService.saveProduct(productDummy);
		
		productFetched = productService.getProduct(new Long(0));
		
	}
	
	
	private void saveAndUpdateProduct(){
		
		productSaved = productService.saveProduct(productDummy);		
		
		productDummy.setOfferPrice(new Long(250));
		
		productUpdated = productService.saveProduct(productDummy);
	}
}
