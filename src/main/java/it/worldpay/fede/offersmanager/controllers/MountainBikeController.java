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

import it.worldpay.fede.offersmanager.model.bikes.MountainBike;
import it.worldpay.fede.offersmanager.services.MountainBikeService;


@RestController
@EnableAutoConfiguration
@RequestMapping(value="/mountainBike")
public class MountainBikeController {

	
	@Autowired
	MountainBikeService mountainBikeService;
	
	@RequestMapping(value="/saveMountainBike", method = RequestMethod.POST)
	public void saveMountainBike(@Valid @RequestBody MountainBike mountainBike, HttpServletResponse response){
		mountainBikeService.saveMountainBike(mountainBike);
		response.setStatus(HttpStatus.CREATED.value());
	}
	
//	@RequestMapping(value="/getMountainBike/{productId}", method = RequestMethod.GET)
//	public MountainBike getMountainBike(@PathVariable("productId") Long productId) {
//        return mountainBikeService.getMountainBike(productId);
//    }
	
//	@RequestMapping(value="/deleteMountainBike", method = RequestMethod.DELETE)
//	public void deleteGelato(@Valid @RequestBody MountainBike mountainBike, HttpServletResponse response)
//	{
//		 mountainBikeService.deleteMountainBike(mountainBike);
//		 response.setStatus(HttpStatus.OK.value());
//		 
//	}
}
