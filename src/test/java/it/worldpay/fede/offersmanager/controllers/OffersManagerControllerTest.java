package it.worldpay.fede.offersmanager.controllers;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import it.worldpay.fede.offersmanager.dummy.DummyFactory;
import it.worldpay.fede.offersmanager.dummy.DummyFactoryImpl;
import it.worldpay.fede.offersmanager.model.Product;
import it.worldpay.fede.offersmanager.model.food.Gelato;
import it.worldpay.fede.offersmanager.services.GelatoService;
import it.worldpay.fede.offersmanager.services.ProductService;

@DataJpaTest
@RunWith(SpringRunner.class)
public class OffersManagerControllerTest {

	
	@Before
	public void initializeTestVariable() {
		dummyFactory = new DummyFactoryImpl() ;
		gelatoDummy = (Gelato) dummyFactory.getDummyProduct("GELATO");
		badRequestGelatoDummy = (Gelato)dummyFactory.getBadRequestDummyProduct("GELATO");
		mockMvc = MockMvcBuilders
                 .standaloneSetup(offersManagerController)
                 .build();
	}
	
	@InjectMocks
	private OffersManagerController offersManagerController;
	
	@Mock
	private ProductService productService;
	 
	@Mock
	private GelatoService gelatoService;
	
	@Mock
	private Product product;

	private MockMvc mockMvc;
		
	DummyFactory dummyFactory;
	
	private Gelato gelatoDummy;
	
	private Gelato badRequestGelatoDummy;



	
	@Test
	public void post_whenGelatoIsValid_thenResponseIs201()  throws Exception{
		
		doNothing().when(gelatoService).saveGelato(gelatoDummy);
		
		mockMvc.perform(post("/offers/saveGelato")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(gelatoDummy)))
                .andExpect(status().isCreated());
	
       }
	
	
	 @Test
	 public void get_whenGelatoExists_thenResponseIs200() throws Exception {

	        given(gelatoService.getGelato(anyLong())).willReturn(gelatoDummy);
	        
	        mockMvc.perform(get(("/offers/getGelato/{productId}"), gelatoDummy.getProductId())
	                .contentType(MediaType.APPLICATION_JSON))
	                .andExpect(status().isOk());

	    }
	 
	   @Test
	    public void post_whenGelatoHasExpiringDateMissing_thenResponseIs400() throws Exception {
	    
		   doNothing().when(gelatoService).saveGelato(badRequestGelatoDummy);

	       mockMvc.perform(post("/offers/saveGelato")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(asJsonString(badRequestGelatoDummy)))
	                .andExpect(status().isBadRequest());
	    }
	
	  @Test
	  public void get_whenProductExists_thenResponseIs200() throws Exception {

	        given(productService.getProduct(anyLong())).willReturn(gelatoDummy);
	        
	        mockMvc.perform(get(("/offers/getProduct/{productId}"), gelatoDummy.getProductId())
	                .contentType(MediaType.APPLICATION_JSON))
	                .andExpect(status().isOk());

	    }
	  
	  

	
	 public static String asJsonString(final Object obj) {
	        try {
	            return new ObjectMapper().writeValueAsString(obj);
	        } catch (Exception e) {
	            throw new RuntimeException(e);
	        }
	    }
}
