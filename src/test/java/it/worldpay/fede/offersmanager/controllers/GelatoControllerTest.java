package it.worldpay.fede.offersmanager.controllers;

import static it.worldpay.fede.offersmanager.utils.TestUtils.asJsonString;
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
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import it.worldpay.fede.offersmanager.dummy.DummyFactory;
import it.worldpay.fede.offersmanager.dummy.DummyFactoryImpl;
import it.worldpay.fede.offersmanager.model.Product;
import it.worldpay.fede.offersmanager.model.food.Gelato;
import it.worldpay.fede.offersmanager.services.GelatoService;

@RunWith(MockitoJUnitRunner.Silent.class)
public class GelatoControllerTest {

	@Before
	public void initializeTestVariable() {
		dummyFactory = new DummyFactoryImpl() ;
	
		gelatoDummy = (Gelato) dummyFactory.getDummyProduct("GELATO");
		badRequestGelatoDummy = (Gelato)dummyFactory.getBadRequestDummyProduct("GELATO");
	
		mockMvc = MockMvcBuilders
             .standaloneSetup(gelatoController)
             .build();
}
	
	@InjectMocks
	private GelatoController gelatoController;
	
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
		
		mockMvc.perform(post("/gelato/saveGelato")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(gelatoDummy)))
                .andExpect(status().isCreated());
	
       }
	
//	 @Test
//	 public void get_whenGelatoExists_thenResponseIs200() throws Exception {
//
//	        given(gelatoService.getGelato(anyLong())).willReturn(gelatoDummy);
//	        
//	        mockMvc.perform(get(("/gelato/getGelato/{productId}"), gelatoDummy.getProductId())
//	                .contentType(MediaType.APPLICATION_JSON))
//	                .andExpect(status().isOk());
//
//	    }
	 
	 @Test
	 public void post_whenGelatoHasExpiringDateMissing_thenResponseIs400() throws Exception {
	    
		   doNothing().when(gelatoService).saveGelato(badRequestGelatoDummy);

	       mockMvc.perform(post("/gelato/saveGelato")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(asJsonString(badRequestGelatoDummy)))
	                .andExpect(status().isBadRequest());
	    }
	 
//	 @Test
//	 public void delete_whenGelatoIsDeleted_thenResponseIs200()  throws Exception{
//		   
//		   doNothing().when(gelatoService).deleteGelato(gelatoDummy);
//		   
//		   mockMvc.perform(delete("/gelato/deleteGelato")
//	                .contentType(MediaType.APPLICATION_JSON)
//	                .content(asJsonString(gelatoDummy)))
//	                .andExpect(status().isOk());
//	   }
}


