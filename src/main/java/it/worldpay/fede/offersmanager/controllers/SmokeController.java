package it.worldpay.fede.offersmanager.controllers;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.worldpay.fede.offersmanager.model.SmokeyResult;

@RestController
@EnableAutoConfiguration
public class SmokeController {
	
	@RequestMapping("/smokeyTest")
	public SmokeyResult tryMyGet(@RequestParam(value="number", defaultValue="0") int number){
		return new SmokeyResult(number);
	}
	
	@RequestMapping("/")
	public String index(){
		return "foo";
	}

}
