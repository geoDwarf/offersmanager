package it.worldpay.fede.offersmanager.controllers;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import it.worldpay.fede.offersmanager.model.bikes.RoadBike;
import it.worldpay.fede.offersmanager.services.RoadBikeService;

@RestController
@EnableAutoConfiguration
@RequestMapping(value="/roadBike")
public class RoadBikeController {
	

	
	
	@Autowired
	RoadBikeService roadBikeService;
	
	@RequestMapping(value="/saveRoadBike", method = RequestMethod.POST)
	public void saveRoadBike(@Valid @RequestBody RoadBike roadBike, HttpServletResponse response){
		roadBikeService.saveRoadBike(roadBike);
		response.setStatus(HttpStatus.CREATED.value());
	}
	
//	@RequestMapping(value="/getRoadBike/{productId}", method = RequestMethod.GET)
//	public RoadBike getRoadBike(@PathVariable("productId") Long productId) {
//        return roadBikeService.getRoadBike(productId);
//    }
	
//	@RequestMapping(value="/deleteRoadBike", method = RequestMethod.DELETE)
//	public void deleteGelato(@Valid @RequestBody RoadBike roadBike, HttpServletResponse response)
//	{
//		 roadBikeService.deleteRoadBike(roadBike);
//		 response.setStatus(HttpStatus.OK.value());
//		 
//	}


}
