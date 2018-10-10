package it.worldpay.fede.offersmanager.controllers;

import static it.worldpay.fede.offersmanager.utils.TestUtils.changeProductForBadRequest;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import it.worldpay.fede.offersmanager.dummy.DummyFactory;
import it.worldpay.fede.offersmanager.dummy.DummyFactoryImpl;
import it.worldpay.fede.offersmanager.model.Product;
import it.worldpay.fede.offersmanager.model.bikes.MountainBike;
import it.worldpay.fede.offersmanager.model.bikes.RoadBike;
import it.worldpay.fede.offersmanager.model.books.FantasyBook;
import it.worldpay.fede.offersmanager.model.books.HandBook;
import it.worldpay.fede.offersmanager.model.food.Gelato;
import it.worldpay.fede.offersmanager.model.food.Pasta;
import it.worldpay.fede.offersmanager.model.food.Pizza;
import it.worldpay.fede.offersmanager.services.BaseService;

@RunWith(MockitoJUnitRunner.Silent.class)
public class OffersManagerControllerTest {

	
	@Before
	public void initializeTestVariable() {
		
		initializer();
		
	}
   
	@Autowired
    ObjectMapper objectMapper;
	
    @InjectMocks
	private OffersManagerController offersManagerController;
	
	@Mock
	private BaseService baseService;
	

	private MockMvc mockMvc;
		
	DummyFactory dummyFactory;
	
	private Product  gelatoDummy;
	
	
	private Pizza pizzaDummy;
	
	private Pasta pastaDummy;
	
	private MountainBike mountainBikeDummy;
	
	private RoadBike roadBikeDummy;
	
	private HandBook handBookDummy;
	
	private FantasyBook fantasyBookDummy;
	
	
	@Test
	public void post_whenProductIsValid_thenResponseIs201()  throws Exception{
	 
		gelatoDummy.setDaysValidityPeriod(8);
		doNothing().when(baseService).saveProduct(gelatoDummy);
		
		mockMvc.perform(post("/offers/saveProduct")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(gelatoDummy)))
                .andExpect(status().isCreated());
	}
	
	
	
	
	  @Test
	  public void get_whenProductExists_thenResponseIs200() throws Exception {
		 
		  	gelatoDummy.setDaysValidityPeriod(5);
	        given(baseService.getProduct(anyLong())).willReturn(gelatoDummy);
	        
	        mockMvc.perform(get(("/offers/getProduct/{productId}"), gelatoDummy.getProductId())
	                .contentType(MediaType.APPLICATION_JSON))
	                .andExpect(status().isOk());

	    }
	  

	 @Test
	 public void delete_whenProductIsDeletedResponseIs200()  throws Exception{
			   
			   doNothing().when(baseService).deleteProduct(gelatoDummy);
			   
			   mockMvc.perform(delete("/offers/deleteProduct")
		                .contentType(MediaType.APPLICATION_JSON)
		                .content(asJsonString(gelatoDummy)))
		                .andExpect(status().isOk());
		   }
		

	 @Test
	 public void post_whenGelatoHasWrongproperties_thenResponseIs400() throws Exception {
		   changeProductForBadRequest(gelatoDummy);	
		   doNothing().when(baseService).saveProduct(gelatoDummy);

	       mockMvc.perform(post("/offers/saveProduct")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(asJsonString(gelatoDummy)))
	                .andExpect(status().isBadRequest());
	    }
	 
	
	 public static String asJsonString(final Object obj) {
	        try {
	            return new ObjectMapper().writeValueAsString(obj);
	        } catch (Exception e) {
	            throw new RuntimeException(e);
	        }
	    }
	 
	 
	 private void initializer(){
		 
		 	dummyFactory = new DummyFactoryImpl() ;
			
			gelatoDummy = dummyFactory.getDummyProduct("GELATO");
			pizzaDummy = (Pizza) dummyFactory.getDummyProduct("PIZZA");
			pastaDummy = (Pasta)dummyFactory.getDummyProduct("PASTA");
			mountainBikeDummy = (MountainBike)dummyFactory.getDummyProduct("MOUNTAINBIKE");
			roadBikeDummy =(RoadBike)dummyFactory.getDummyProduct("ROADBIKE");
			handBookDummy =(HandBook)dummyFactory.getDummyProduct("HANDBOOK");
			fantasyBookDummy = (FantasyBook)dummyFactory.getDummyProduct("FANTASYBOOK");
			
			mockMvc = MockMvcBuilders
	                 .standaloneSetup(offersManagerController)
	                 .build();
	 }
}
