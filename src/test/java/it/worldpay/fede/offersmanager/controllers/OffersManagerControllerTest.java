package it.worldpay.fede.offersmanager.controllers;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
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
import it.worldpay.fede.offersmanager.services.GelatoService;
import it.worldpay.fede.offersmanager.services.ProductService;


@RunWith(MockitoJUnitRunner.Silent.class)
public class OffersManagerControllerTest {

	
	@Before
	public void initializeTestVariable() {
		
		initializer();
		
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
	
	private Pizza pizzaDummy;
	
	private Pasta pastaDummy;
	
	private MountainBike mountainBikeDummy;
	
	private RoadBike roadBikeDummy;
	
	private HandBook handBookDummy;
	
	private FantasyBook fantasyBookDummy;
	
	
	  @Test
	  public void get_whenProductExists_thenResponseIs200() throws Exception {

	        given(productService.getProduct(anyLong())).willReturn(gelatoDummy);
	        
	        mockMvc.perform(get(("/offers/getProduct/{productId}"), gelatoDummy.getProductId())
	                .contentType(MediaType.APPLICATION_JSON))
	                .andExpect(status().isOk());

	    }
	  

		   
		   @Test
		   public void delete_whenProductIsDeletedResponseIs200()  throws Exception{
			   
			   doNothing().when(productService).deleteProduct(gelatoDummy);
			   
			   mockMvc.perform(delete("/offers/deleteGelato")
		                .contentType(MediaType.APPLICATION_JSON)
		                .content(asJsonString(gelatoDummy)))
		                .andExpect(status().isOk());
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
			
			gelatoDummy = (Gelato) dummyFactory.getDummyProduct("GELATO");
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
