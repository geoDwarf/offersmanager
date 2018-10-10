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
import it.worldpay.fede.offersmanager.model.bikes.RoadBike;
import it.worldpay.fede.offersmanager.services.RoadBikeService;

@RunWith(MockitoJUnitRunner.Silent.class)
public class RoadBikeControllerTest {

	
//	@Before
//	public void initializeTestVariable() {
//		
//		dummyFactory = new DummyFactoryImpl() ;
//	
//		roadBikeDummy = (RoadBike) dummyFactory.getDummyProduct("ROADBIKE");
//		badRequestRoadBikeDummy = (RoadBike)dummyFactory.getBadRequestDummyProduct("ROADBIKE");
//		
//		mockMvc = MockMvcBuilders
//                 .standaloneSetup(roadBikeController)
//                 .build();
//	}
//	
//	@Mock
//	private RoadBikeService roadBikeService;
//	
//	@InjectMocks
//	private RoadBikeController roadBikeController;
//	
//	private MockMvc mockMvc;
//	
//	DummyFactory dummyFactory;
//	
//	private RoadBike roadBikeDummy;
//
//	private RoadBike badRequestRoadBikeDummy;
//	
	
//	@Test
//	public void post_whenRoadBikeIsValid_thenResponseIs201()  throws Exception{
//		
//		doNothing().when(roadBikeService).saveRoadBike(roadBikeDummy);
//		
//		mockMvc.perform(post("/roadBike/saveRoadBike")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(asJsonString(roadBikeDummy)))
//                .andExpect(status().isCreated());
//	}
//	
	
//	 @Test
//	 public void get_whenRoadBikeExists_thenResponseIs200() throws Exception {
//
//	        given(roadBikeService.getRoadBike(anyLong())).willReturn(roadBikeDummy);
//	        
//	        mockMvc.perform(get(("/roadBike/getRoadBike/{productId}"), roadBikeDummy.getProductId())
//	                .contentType(MediaType.APPLICATION_JSON))
//	                .andExpect(status().isOk());
//
//	    }
//	 
//	   @Test
//	    public void post_whenRoadBikeHasExpiringDateMissing_thenResponseIs400() throws Exception {
//	    
//		   doNothing().when(roadBikeService).saveRoadBike(badRequestRoadBikeDummy);
//
//	       mockMvc.perform(post("/roadBike/saveRoadBike")
//	                .contentType(MediaType.APPLICATION_JSON)
//	                .content(asJsonString(badRequestRoadBikeDummy)))
//	                .andExpect(status().isBadRequest());
//	    }
	   
//		 @Test
//		 public void delete_whenGelatoIsDeleted_thenResponseIs200()  throws Exception{
//			   
//			   doNothing().when(roadBikeService).deleteRoadBike(roadBikeDummy);
//			   
//			   mockMvc.perform(delete("/roadBike/deleteRoadBike")
//		                .contentType(MediaType.APPLICATION_JSON)
//		                .content(asJsonString(roadBikeDummy)))
//		                .andExpect(status().isOk());
//		   }
	   
}
