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
import it.worldpay.fede.offersmanager.model.food.Pasta;
import it.worldpay.fede.offersmanager.services.PastaService;


@RunWith(MockitoJUnitRunner.Silent.class)
public class PastaControllerTest {

	
	@Before
	public void initializeTestVariable() {
		
		dummyFactory = new DummyFactoryImpl() ;
	
		pastaDummy = (Pasta) dummyFactory.getDummyProduct("PASTA");
		badRequestPastaDummy = (Pasta)dummyFactory.getBadRequestDummyProduct("PASTA");
		
		mockMvc = MockMvcBuilders
                 .standaloneSetup(pastaController)
                 .build();
	}
	
	@Mock
	private PastaService pastaService;
	
	@InjectMocks
	private PastaController pastaController;
	
	private MockMvc mockMvc;
	
	DummyFactory dummyFactory;
	
	private Pasta pastaDummy;

	private Pasta badRequestPastaDummy;
	
	
//	@Test
//	public void post_whenPastaIsValid_thenResponseIs201()  throws Exception{
//		
//		doNothing().when(pastaService).savePasta(pastaDummy);
//		
//		mockMvc.perform(post("/pasta/savePasta")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(asJsonString(pastaDummy)))
//                .andExpect(status().isCreated());
//	}
//	
	
//	 @Test
//	 public void get_whenPastaExists_thenResponseIs200() throws Exception {
//
//	        given(pastaService.getPasta(anyLong())).willReturn(pastaDummy);
//	        
//	        mockMvc.perform(get(("/pasta/getPasta/{productId}"), pastaDummy.getProductId())
//	                .contentType(MediaType.APPLICATION_JSON))
//	                .andExpect(status().isOk());
//
//	    }
	 
//	   @Test
//	    public void post_whenPastaHasExpiringDateMissing_thenResponseIs400() throws Exception {
//	    
//		   doNothing().when(pastaService).savePasta(badRequestPastaDummy);
//
//	       mockMvc.perform(post("/pasta/savePasta")
//	                .contentType(MediaType.APPLICATION_JSON)
//	                .content(asJsonString(badRequestPastaDummy)))
//	                .andExpect(status().isBadRequest());
//	    }
	   
//		 @Test
//		 public void delete_whenPastaIsDeleted_thenResponseIs200()  throws Exception{
//			   
//			   doNothing().when(pastaService).deletePasta(pastaDummy);
//			   
//			   mockMvc.perform(delete("/pasta/deletePasta")
//		                .contentType(MediaType.APPLICATION_JSON)
//		                .content(asJsonString(pastaDummy)))
//		                .andExpect(status().isOk());
//		   }
	   
}
