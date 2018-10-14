package it.worldpay.fede.offersmanager.utils;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import it.worldpay.fede.offersmanager.dummy.DummyFactory;
import it.worldpay.fede.offersmanager.dummy.DummyFactoryImpl;
import it.worldpay.fede.offersmanager.model.product.food.Gelato;

@DataJpaTest
@RunWith(SpringRunner.class)
public class DateTest {

	@Before
	public void initializeTestVariable() {
		
		DummyFactory dummyFactory = new DummyFactoryImpl();
		
		gelatoDummy = (Gelato) dummyFactory.getDummyProduct("GELATO");
		
		dateStringDummy = "2018-12-25 12:25";

		}

	private Gelato gelatoDummy;
	
	private String dateStringDummy;

	SimpleDateFormat simpleDataFormat;
	
	@Autowired
	DateUtils dateUtils ;	

	@Test
	public void test_FormatDate() {
		
		Date parsedDate = dateUtils.parseStringToDate(dateStringDummy);
		
		assertEquals(parsedDate.getClass(), new Date().getClass());
	}
	
	@Test
	public void test_SetExpiringDateOnProduct() {
		
		Date expiringDate =  dateUtils.parseStringToDate(dateStringDummy);
		
		gelatoDummy.setExpiringDate(expiringDate);
		
		assertEquals(gelatoDummy.getExpiringDate(),expiringDate);
	}
	
	
	@Test
	public void test_SetStartingDateOnProduct() {
		
		Date startingDate =  dateUtils.parseStringToDate(dateStringDummy);
		
		gelatoDummy.setOfferStartingDate(startingDate);
		
		assertEquals(gelatoDummy.getOfferStartingDate(),startingDate);
	}
	
	@Test
	public void test_AddValidityPeriodToOffer_asDays() throws ParseException{
		
		int validityPeriod =  5;
		Date expectedDate = dateUtils.parseStringToDate("2018-10-06 00:11");
		setGelatoDummyForDateTest(validityPeriod);
		expectedDate = setDateAsCalendar(expectedDate);
		Date startingDate = gelatoDummy.getOfferStartingDate();
		Date expiringDate = dateUtils.addDates(startingDate,validityPeriod);
		
		assertEquals(expectedDate, expiringDate);
	}
	
	private void setGelatoDummyForDateTest(int validityPeriod){
		gelatoDummy.setDaysValidityPeriod(validityPeriod);
		gelatoDummy.setOfferStartingDate(dateUtils.parseStringToDate("2018-10-01 00:11"));
	}
	
	private Date setDateAsCalendar(Date date){
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		date = calendar.getTime();
		return date;
	}
}
