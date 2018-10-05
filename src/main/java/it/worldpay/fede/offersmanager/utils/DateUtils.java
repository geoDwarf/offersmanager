package it.worldpay.fede.offersmanager.utils;

import java.util.Date;

public interface DateUtils {

	
	public Date parseStringToDate(String stringDate);
	
	public Date manageParsingDataexception(String dataString);
	
	public Date addDates(Date startingDate,int validityPeriod);
	
}
