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

import it.worldpay.fede.offersmanager.model.food.Pizza;
import it.worldpay.fede.offersmanager.services.PizzaService;

@RunWith(MockitoJUnitRunner.Silent.class)
public class PizzaControllerTest {

	
	@Before
	public void initializeTestVariable() {
		
		dummyFactory = new DummyFactoryImpl() ;
	
		pizzaDummy = (Pizza) dummyFactory.getDummyProduct("PIZZA");
		badRequestPizzaDummy = (Pizza)dummyFactory.getBadRequestDummyProduct("PIZZA");
		
		mockMvc = MockMvcBuilders
                 .standaloneSetup(pizzaController)
                 .build();
	}
	
	@Mock
	private PizzaService pizzaService;
	
	@InjectMocks
	private PizzaController pizzaController;
	
	private MockMvc mockMvc;
	
	DummyFactory dummyFactory;
	
	private Pizza pizzaDummy;

	private Pizza badRequestPizzaDummy;
	
	
	@Test
	public void post_whenPizzaIsValid_thenResponseIs201()  throws Exception{
		
		doNothing().when(pizzaService).savePizza(pizzaDummy);
		
		mockMvc.perform(post("/pizza/savePizza")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(pizzaDummy)))
                .andExpect(status().isCreated());
	}
	
	
	 @Test
	 public void get_whenPizzaExists_thenResponseIs200() throws Exception {

	        given(pizzaService.getPizza(anyLong())).willReturn(pizzaDummy);
	        
	        mockMvc.perform(get(("/pizza/getPizza/{productId}"), pizzaDummy.getProductId())
	                .contentType(MediaType.APPLICATION_JSON))
	                .andExpect(status().isOk());

	    }
	 
	   @Test
	    public void post_whenPizzaHasExpiringDateMissing_thenResponseIs400() throws Exception {
	    
		   doNothing().when(pizzaService).savePizza(badRequestPizzaDummy);

	       mockMvc.perform(post("/pizza/savePizza")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(asJsonString(badRequestPizzaDummy)))
	                .andExpect(status().isBadRequest());
	    }
	   
		 @Test
		 public void delete_whenGelatoIsDeleted_thenResponseIs200()  throws Exception{
			   
			   doNothing().when(pizzaService).deletePizza(pizzaDummy);
			   
			   mockMvc.perform(delete("/pizza/deletePizza")
		                .contentType(MediaType.APPLICATION_JSON)
		                .content(asJsonString(pizzaDummy)))
		                .andExpect(status().isOk());
		   }
	   
	 
	
}
