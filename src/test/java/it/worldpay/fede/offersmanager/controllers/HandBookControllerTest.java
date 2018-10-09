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
import it.worldpay.fede.offersmanager.model.books.HandBook;
import it.worldpay.fede.offersmanager.services.HandBookService;


@RunWith(MockitoJUnitRunner.Silent.class)
public class HandBookControllerTest {
	

	@Before
	public void initializeTestVariable() {
		
		dummyFactory = new DummyFactoryImpl() ;
	
		handBookDummy = (HandBook) dummyFactory.getDummyProduct("HANDBOOK");
		badRequestHandBookDummy = (HandBook)dummyFactory.getBadRequestDummyProduct("HANDBOOK");
		
		mockMvc = MockMvcBuilders
                 .standaloneSetup(handBookController)
                 .build();
	}
	
	@Mock
	private HandBookService handBookService;
	
	@InjectMocks
	private HandBookController handBookController;
	
	private MockMvc mockMvc;
	
	DummyFactory dummyFactory;
	
	private HandBook handBookDummy;

	private HandBook badRequestHandBookDummy;
	
	
	@Test
	public void post_whenHandBookIsValid_thenResponseIs201()  throws Exception{
		
		doNothing().when(handBookService).saveHandBook(handBookDummy);
		
		mockMvc.perform(post("/handBook/saveHandBook")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(handBookDummy)))
                .andExpect(status().isCreated());
	}
	
	
//	 @Test
//	 public void get_whenHandBookExists_thenResponseIs200() throws Exception {
//
//	        given(handBookService.getHandBook(anyLong())).willReturn(handBookDummy);
//	        
//	        mockMvc.perform(get(("/handBook/getHandBook/{productId}"), handBookDummy.getProductId())
//	                .contentType(MediaType.APPLICATION_JSON))
//	                .andExpect(status().isOk());
//
//	    }
	 
	   @Test
	    public void post_whenHandBookHasExpiringDateMissing_thenResponseIs400() throws Exception {
	    
		   doNothing().when(handBookService).saveHandBook(badRequestHandBookDummy);

	       mockMvc.perform(post("/handBook/saveHandBook")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(asJsonString(badRequestHandBookDummy)))
	                .andExpect(status().isBadRequest());
	    }
	   
//		 @Test
//		 public void delete_whenGelatoIsDeleted_thenResponseIs200()  throws Exception{
//			   
//			   doNothing().when(handBookService).deleteHandBook(handBookDummy);
//			   
//			   mockMvc.perform(delete("/handBook/deleteHandBook")
//		                .contentType(MediaType.APPLICATION_JSON)
//		                .content(asJsonString(handBookDummy)))
//		                .andExpect(status().isOk());
//		   }
	   
	 
	


}
