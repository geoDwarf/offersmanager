package it.worldpay.fede.offersmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan({"it.worldpay.fede"})
public class OffersManagerMain 
{
	

	 public static void main( String[] args )
    {
    	 SpringApplication.run(OffersManagerMain.class, args);
    }
}
