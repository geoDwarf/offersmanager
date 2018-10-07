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
import it.worldpay.fede.offersmanager.model.books.FantasyBook;
import it.worldpay.fede.offersmanager.services.FantasyBookService;


@RunWith(MockitoJUnitRunner.Silent.class)
public class FantasyBookControllerTest {
	
	@Before
	public void initializeTestVariable() {
		
		dummyFactory = new DummyFactoryImpl() ;
	
		fantasyBookDummy = (FantasyBook) dummyFactory.getDummyProduct("FANTASYBOOK");
		badRequestFantasyBookDummy = (FantasyBook)dummyFactory.getBadRequestDummyProduct("FANTASYBOOK");
		
		mockMvc = MockMvcBuilders
                 .standaloneSetup(fantasyBookController)
                 .build();
	}
	
	@Mock
	private FantasyBookService fantasyBookService;
	
	@InjectMocks
	private FantasyBookController fantasyBookController;
	
	private MockMvc mockMvc;
	
	DummyFactory dummyFactory;
	
	private FantasyBook fantasyBookDummy;

	private FantasyBook badRequestFantasyBookDummy;
	
	
	@Test
	public void post_whenFantasyBookIsValid_thenResponseIs201()  throws Exception{
		
		doNothing().when(fantasyBookService).saveFantasyBook(fantasyBookDummy);
		
		mockMvc.perform(post("/fantasyBook/saveFantasyBook")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(fantasyBookDummy)))
                .andExpect(status().isCreated());
	}
	
	
	 @Test
	 public void get_whenFantasyBookExists_thenResponseIs200() throws Exception {

	        given(fantasyBookService.getFantasyBook(anyLong())).willReturn(fantasyBookDummy);
	        
	        mockMvc.perform(get(("/fantasyBook/getFantasyBook/{productId}"), fantasyBookDummy.getProductId())
	                .contentType(MediaType.APPLICATION_JSON))
	                .andExpect(status().isOk());

	    }
	 
	   @Test
	    public void post_whenFantasyBookHasExpiringDateMissing_thenResponseIs400() throws Exception {
	    
		   doNothing().when(fantasyBookService).saveFantasyBook(badRequestFantasyBookDummy);

	       mockMvc.perform(post("/fantasyBook/saveFantasyBook")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(asJsonString(badRequestFantasyBookDummy)))
	                .andExpect(status().isBadRequest());
	    }
	   
		 @Test
		 public void delete_whenGelatoIsDeleted_thenResponseIs200()  throws Exception{
			   
			   doNothing().when(fantasyBookService).deleteFantasyBook(fantasyBookDummy);
			   
			   mockMvc.perform(delete("/fantasyBook/deleteFantasyBook")
		                .contentType(MediaType.APPLICATION_JSON)
		                .content(asJsonString(fantasyBookDummy)))
		                .andExpect(status().isOk());
		   }
	   
	 
	


}
