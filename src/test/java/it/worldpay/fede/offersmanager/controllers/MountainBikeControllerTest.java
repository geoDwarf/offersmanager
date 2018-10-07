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
import it.worldpay.fede.offersmanager.model.bikes.MountainBike;
import it.worldpay.fede.offersmanager.services.MountainBikeService;

@RunWith(MockitoJUnitRunner.Silent.class)
public class MountainBikeControllerTest {
	
	@Before
	public void initializeTestVariable() {
		
		dummyFactory = new DummyFactoryImpl() ;
	
		mountainBikeDummy = (MountainBike) dummyFactory.getDummyProduct("MOUNTAINBIKE");
		badRequestMountainBikeDummy = (MountainBike)dummyFactory.getBadRequestDummyProduct("MOUNTAINBIKE");
		
		mockMvc = MockMvcBuilders
                 .standaloneSetup(mountainBikeController)
                 .build();
	}
	
	@Mock
	private MountainBikeService mountainBikeService;
	
	@InjectMocks
	private MountainBikeController mountainBikeController;
	
	private MockMvc mockMvc;
	
	DummyFactory dummyFactory;
	
	private MountainBike mountainBikeDummy;

	private MountainBike badRequestMountainBikeDummy;
	
	
	@Test
	public void post_whenMountainBikeIsValid_thenResponseIs201()  throws Exception{
		
		doNothing().when(mountainBikeService).saveMountainBike(mountainBikeDummy);
		
		mockMvc.perform(post("/mountainBike/saveMountainBike")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(mountainBikeDummy)))
                .andExpect(status().isCreated());
	}
	
	
	 @Test
	 public void get_whenMountainBikeExists_thenResponseIs200() throws Exception {

	        given(mountainBikeService.getMountainBike(anyLong())).willReturn(mountainBikeDummy);
	        
	        mockMvc.perform(get(("/mountainBike/getMountainBike/{productId}"), mountainBikeDummy.getProductId())
	                .contentType(MediaType.APPLICATION_JSON))
	                .andExpect(status().isOk());

	    }
	 
	   @Test
	    public void post_whenMountainBikeHasExpiringDateMissing_thenResponseIs400() throws Exception {
	    
		   doNothing().when(mountainBikeService).saveMountainBike(badRequestMountainBikeDummy);

	       mockMvc.perform(post("/mountainBike/saveMountainBike")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(asJsonString(badRequestMountainBikeDummy)))
	                .andExpect(status().isBadRequest());
	    }
	   
		 @Test
		 public void delete_whenGelatoIsDeleted_thenResponseIs200()  throws Exception{
			   
			   doNothing().when(mountainBikeService).deleteMountainBike(mountainBikeDummy);
			   
			   mockMvc.perform(delete("/mountainBike/deleteMountainBike")
		                .contentType(MediaType.APPLICATION_JSON)
		                .content(asJsonString(mountainBikeDummy)))
		                .andExpect(status().isOk());
		   }
	   
	 
	


}
