package it.worldpay.fede.offersmanager.utils;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class DateTimeImpl implements DateTime{

	
	@Override
	public Date getDate(){
		
		return new Date();
	}
}
