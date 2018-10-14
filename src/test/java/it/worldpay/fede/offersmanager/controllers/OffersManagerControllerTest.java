package it.worldpay.fede.offersmanager.controllers;

import static it.worldpay.fede.offersmanager.utils.TestUtils.changeProductForBadRequest;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;

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
import it.worldpay.fede.offersmanager.model.Offer;
import it.worldpay.fede.offersmanager.model.bikes.MountainBike;
import it.worldpay.fede.offersmanager.model.bikes.RoadBike;
import it.worldpay.fede.offersmanager.model.books.FantasyBook;
import it.worldpay.fede.offersmanager.model.books.HandBook;
import it.worldpay.fede.offersmanager.model.food.Gelato;
import it.worldpay.fede.offersmanager.model.food.Pasta;
import it.worldpay.fede.offersmanager.model.food.Pizza;
import it.worldpay.fede.offersmanager.services.BaseServiceDefault;
import it.worldpay.fede.offersmanager.services.FantasyBookService;
import it.worldpay.fede.offersmanager.services.GelatoService;
import it.worldpay.fede.offersmanager.services.HandBookService;
import it.worldpay.fede.offersmanager.services.MountainBikeService;
import it.worldpay.fede.offersmanager.services.OfferServiceDefault;
import it.worldpay.fede.offersmanager.services.PastaService;
import it.worldpay.fede.offersmanager.services.PizzaService;
import it.worldpay.fede.offersmanager.services.RoadBikeService;

@RunWith(MockitoJUnitRunner.Silent.class)
public class OffersManagerControllerTest {

	@Before
	public void initializeTestVariable() {
		init();
	}

	@Autowired
	ObjectMapper objectMapper;

	@InjectMocks
	private OffersManagerController offersManagerController;

	@Mock
	private BaseServiceDefault baseServiceDefault;

	
	@Mock
	GelatoService gelatoService;

	@Mock
	PizzaService pizzaService;

	@Mock
	PastaService pastaService;

	@Mock
	MountainBikeService mountainBikeService;

	@Mock
	RoadBikeService roadBikeService;
	
	@Mock
	HandBookService handBookService;

	@Mock
	FantasyBookService fantasyBookService;

	@Mock
	OfferServiceDefault offerServiceDefault;

	private MockMvc mockMvc;

	DummyFactory dummyFactory;

	private Gelato gelatoDummy;

	private Pizza pizzaDummy;

	private Pasta pastaDummy;

	private MountainBike mountainBikeDummy;

	private RoadBike roadBikeDummy;

	private HandBook handBookDummy;

	private FantasyBook fantasyBookDummy;

	private Offer offerDummy;
	

	@Test
	public void post_whenGelatoIsValid_thenResponseIs201() throws Exception {

		doNothing().when(gelatoService).saveProduct(gelatoDummy);

		mockMvc.perform(
				post("/offers/saveGelato").contentType(MediaType.APPLICATION_JSON).content(asJsonString(gelatoDummy)))
				.andExpect(status().isCreated());
	}

	@Test
	public void get_whenGelatoExists_thenResponseIs200() throws Exception {

		gelatoDummy.setDaysValidityPeriod(5);
		given(gelatoService.getProduct(anyLong())).willReturn(gelatoDummy);

		mockMvc.perform(get(("/offers/getProduct/{productId}"), gelatoDummy.getProductId())
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

	}

	@Test
	public void delete_whenGelatoIsDeletedResponseIs200() throws Exception {

		doNothing().when(gelatoService).deleteProduct(gelatoDummy.getProductId());

		mockMvc.perform(delete(("/offers/deleteProduct/{productId}"), gelatoDummy.getProductId())
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	public void post_whenGelatoHasWrongproperties_thenResponseIs400() throws Exception {
		changeProductForBadRequest(gelatoDummy);
		doNothing().when(gelatoService).saveProduct(gelatoDummy);

		mockMvc.perform(
				post("/offers/saveGelato").contentType(MediaType.APPLICATION_JSON).content(asJsonString(gelatoDummy)))
				.andExpect(status().isBadRequest());
	}


	@Test
	public void post_whenPizzaIsValid_thenResponseIs201() throws Exception {

		doNothing().when(pizzaService).saveProduct(pizzaDummy);

		mockMvc.perform(
				post("/offers/savePizza").contentType(MediaType.APPLICATION_JSON).content(asJsonString(pizzaDummy)))
				.andExpect(status().isCreated());
	}

	@Test
	public void get_whenPizzaExists_thenResponseIs200() throws Exception {

		pizzaDummy.setDaysValidityPeriod(5);
		given(pizzaService.getProduct(anyLong())).willReturn(pizzaDummy);

		mockMvc.perform(get(("/offers/getProduct/{productId}"), pizzaDummy.getProductId())
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	public void delete_whenpizzaIsDeletedResponseIs200() throws Exception {

		doNothing().when(pizzaService).deleteProduct(pizzaDummy.getProductId());

		mockMvc.perform(delete(("/offers/deleteProduct/{productId}"), pizzaDummy.getProductId())
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	public void post_whenPizzaHasWrongProperties_thenResponseIs400() throws Exception {
		changeProductForBadRequest(pizzaDummy);
		doNothing().when(pizzaService).saveProduct(pizzaDummy);

		mockMvc.perform(
				post("/offers/savePizza").contentType(MediaType.APPLICATION_JSON).content(asJsonString(pizzaDummy)))
				.andExpect(status().isBadRequest());
	}


	@Test
	public void post_whenPastaIsValid_thenResponseIs201() throws Exception {

		doNothing().when(pastaService).saveProduct(pastaDummy);

		mockMvc.perform(
				post("/offers/savePasta").contentType(MediaType.APPLICATION_JSON).content(asJsonString(pastaDummy)))
				.andExpect(status().isCreated());
	}

	@Test
	public void get_whenPastaExists_thenResponseIs200() throws Exception {

		pastaDummy.setDaysValidityPeriod(5);
		given(pastaService.getProduct(anyLong())).willReturn(pastaDummy);

		mockMvc.perform(get(("/offers/getProduct/{productId}"), pastaDummy.getProductId())
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

	}

	@Test
	public void delete_whenPastaIsDeletedResponseIs200() throws Exception {

		doNothing().when(baseServiceDefault).deleteProduct(pastaDummy.getProductId());

		mockMvc.perform(delete(("/offers/deleteProduct/{productId}"), pastaDummy.getProductId())
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	public void post_whenPastaHasWrongproperties_thenResponseIs400() throws Exception {
		changeProductForBadRequest(pastaDummy);
		doNothing().when(pastaService).saveProduct(pastaDummy);

		mockMvc.perform(
				post("/offers/savePasta").contentType(MediaType.APPLICATION_JSON).content(asJsonString(pastaDummy)))
				.andExpect(status().isBadRequest());
	}


	@Test
	public void post_whenMountainbikeIsValid_thenResponseIs201() throws Exception {

		doNothing().when(mountainBikeService).saveProduct(mountainBikeDummy);

		mockMvc.perform(post("/offers/saveMountainBike").contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(mountainBikeDummy))).andExpect(status().isCreated());
	}

	@Test
	public void get_whenMountainBikeExists_thenResponseIs200() throws Exception {

		mountainBikeDummy.setDaysValidityPeriod(5);
		given(mountainBikeService.getProduct(anyLong())).willReturn(mountainBikeDummy);

		mockMvc.perform(get(("/offers/getProduct/{productId}"), mountainBikeDummy.getProductId())
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	public void delete_whenMountainBikeIsDeletedResponseIs200() throws Exception {

		doNothing().when(mountainBikeService).deleteProduct(mountainBikeDummy.getProductId());

		mockMvc.perform(delete(("/offers/deleteProduct/{productId}"), mountainBikeDummy.getProductId())
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	public void post_whenMountainBikeHasWrongproperties_thenResponseIs400() throws Exception {
		changeProductForBadRequest(mountainBikeDummy);
		doNothing().when(mountainBikeService).saveProduct(mountainBikeDummy);

		mockMvc.perform(post("/offers/saveMountainBike").contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(mountainBikeDummy))).andExpect(status().isBadRequest());
	}

	
	@Test
	public void post_whenrRoadBikeIsValid_thenResponseIs201() throws Exception {

		doNothing().when(roadBikeService).saveProduct(roadBikeDummy);

		mockMvc.perform(post("/offers/saveRoadBike").contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(roadBikeDummy))).andExpect(status().isCreated());
	}

	@Test
	public void get_whenRoadBikeExists_thenResponseIs200() throws Exception {

		roadBikeDummy.setDaysValidityPeriod(5);
		given(roadBikeService.getProduct(anyLong())).willReturn(roadBikeDummy);

		mockMvc.perform(get(("/offers/getProduct/{productId}"), roadBikeDummy.getProductId())
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	public void delete_whenRoadBikeIsDeletedResponseIs200() throws Exception {

		doNothing().when(roadBikeService).deleteProduct(roadBikeDummy.getProductId());

		mockMvc.perform(delete(("/offers/deleteProduct/{productId}"), roadBikeDummy.getProductId())
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	public void post_whenRoadBikeHasWrongProperties_thenResponseIs400() throws Exception {
		changeProductForBadRequest(roadBikeDummy);
		doNothing().when(roadBikeService).saveProduct(roadBikeDummy);

		mockMvc.perform(post("/offers/saveRoadBike").contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(roadBikeDummy))).andExpect(status().isBadRequest());
	}

	
	@Test
	public void post_whenHandBookIsValid_thenResponseIs201() throws Exception {

		doNothing().when(handBookService).saveProduct(handBookDummy);

		mockMvc.perform(
				post("/offers/saveHandBook").contentType(MediaType.APPLICATION_JSON).content(asJsonString(handBookDummy)))
				.andExpect(status().isCreated());
	}

	@Test
	public void get_whenHandBookExits_thenResponseIs200() throws Exception {

		pizzaDummy.setDaysValidityPeriod(5);
		given(handBookService.getProduct(anyLong())).willReturn(handBookDummy);

		mockMvc.perform(get(("/offers/getProduct/{productId}"), handBookDummy.getProductId())
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

	}

	@Test
	public void delete_whenHandBookIsDeletedResponseIs200() throws Exception {

		doNothing().when(handBookService).deleteProduct(handBookDummy.getProductId());

		mockMvc.perform(delete(("/offers/deleteProduct/{productId}"), handBookDummy.getProductId())
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	public void post_whenHandBookHasWrongProperties_thenResponseIs400() throws Exception {
		
		changeProductForBadRequest(handBookDummy);
		doNothing().when(handBookService).saveProduct(handBookDummy);

		mockMvc.perform(
				post("/offers/saveHandBook").contentType(MediaType.APPLICATION_JSON).content(asJsonString(handBookDummy)))
				.andExpect(status().isBadRequest());
	}

	
	@Test
	public void post_whenFantasyBookIsValid_thenResponseIs201() throws Exception {

		doNothing().when(fantasyBookService).saveProduct(fantasyBookDummy);

		mockMvc.perform(post("/offers/saveFantasyBook").contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(fantasyBookDummy))).andExpect(status().isCreated());
	}

	@Test
	public void get_whenFantasyBookExists_thenResponseIs200() throws Exception {

		fantasyBookDummy.setDaysValidityPeriod(5);
		given(fantasyBookService.getProduct(anyLong())).willReturn(fantasyBookDummy);

		mockMvc.perform(get(("/offers/getProduct/{productId}"), fantasyBookDummy.getProductId())
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

	}
	
	@Test
	public void delete_whenFantasyBookIsDeletedResponseIs200() throws Exception {

		doNothing().when(fantasyBookService).deleteProduct(fantasyBookDummy.getProductId());

		mockMvc.perform(delete(("/offers/deleteProduct/{productId}"), fantasyBookDummy.getProductId())
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}
	
	@Test
	public void post_whenFantasyBookHasWrongProperties_thenResponseIs400() throws Exception {
		
		changeProductForBadRequest(fantasyBookDummy);
		doNothing().when(fantasyBookService).saveProduct(fantasyBookDummy);

		mockMvc.perform(
				post("/offers/saveFantasyBook").contentType(MediaType.APPLICATION_JSON).content(asJsonString(fantasyBookDummy)))
				.andExpect(status().isBadRequest());
	}


	@Test
	public void post_whenOfferIsValid_thenResponseIs201() throws Exception {

		doNothing().when(offerServiceDefault).saveOffer(offerDummy);

		mockMvc.perform(post("/offers/saveOffer").contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(offerDummy))).andExpect(status().isCreated());
	}
	
	@Test
	public void post_whenOfferExists_thenResponseIs201() throws Exception {

		given(offerServiceDefault.getOffer(anyLong())).willReturn(offerDummy);

		mockMvc.perform(get(("/offers/getOffer/{offerId}"), offerDummy.getOfferId())
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

	}

	@Test
	public void delete_whenOfferIsDeletedResponseIs200() throws Exception {

		doNothing().when(offerServiceDefault).deleteOffer(offerDummy.getOfferId());

		mockMvc.perform(delete(("/offers/deleteOffer/{offerId}"), offerDummy.getOfferId())
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}
	
	
	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private void init() {

		dummyFactory = new DummyFactoryImpl();

		gelatoDummy = (Gelato) dummyFactory.getDummyProduct("GELATO");
		pizzaDummy = (Pizza) dummyFactory.getDummyProduct("PIZZA");
		pastaDummy = (Pasta) dummyFactory.getDummyProduct("PASTA");
		mountainBikeDummy = (MountainBike) dummyFactory.getDummyProduct("MOUNTAINBIKE");
		roadBikeDummy = (RoadBike) dummyFactory.getDummyProduct("ROADBIKE");
		handBookDummy = (HandBook) dummyFactory.getDummyProduct("HANDBOOK");
		fantasyBookDummy = (FantasyBook) dummyFactory.getDummyProduct("FANTASYBOOK");
		offerDummy = dummyFactory.getDummyOffer(new Long(22),new ArrayList<>());

		mockMvc = MockMvcBuilders.standaloneSetup(offersManagerController).build();
	}
}
