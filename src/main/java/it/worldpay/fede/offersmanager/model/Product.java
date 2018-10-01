package it.worldpay.fede.offersmanager.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.validation.constraints.NotNull;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Product {
	
	@Id
	@NotNull
	@Column(unique = true, name = "PRODUCT_ID")
	private Long productId;
	
	@Column(name = "OFFER_EXPIRING_DATE")
	private Date offerExpiringDate;
	
	@Column(name = "OFFER_STARTING_DATE")
	private Date offerStartingDate;
	
	@Column(name = "OFFER_PRICE")
	private long offerPrice;
	
	@Column(name = "OFFER_DESCRIPTION")
	private String offerDescription;
	
	@Column(name = "PRODUCT_NAME")
	private String productName;
	
	@Column(name = "DAYS_VALIDITY_PERIOD")
	private int daysValidityPeriod;
	

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Date getOfferExpiringDate() {
		return offerExpiringDate;
	}

	public void setOfferExpiringDate(Date offerExpiringDate) {
		this.offerExpiringDate = offerExpiringDate;
	}

	public Date getOfferStartingDate() {
		return offerStartingDate;
	}

	public void setOfferStartingDate(Date offerStartingDate) {
		this.offerStartingDate = offerStartingDate;
	}

	public long getOfferPrice() {
		return offerPrice;
	}

	public void setOfferPrice(long offerPrice) {
		this.offerPrice = offerPrice;
	}

	public String getOfferDescription() {
		return offerDescription;
	}

	public void setOfferDescription(String offerDescription) {
		this.offerDescription = offerDescription;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	public Product(Long productId){
		this.productId = productId;
	}
	
	public int getDaysValidityPeriod() {
		return daysValidityPeriod;
	}

	public void setDaysValidityPeriod(int validityPeriod) {
		this.daysValidityPeriod = validityPeriod;
	}

	public Product(){
		
	}

	
}
