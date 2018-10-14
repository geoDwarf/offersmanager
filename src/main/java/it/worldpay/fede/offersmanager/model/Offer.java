package it.worldpay.fede.offersmanager.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Entity
public class Offer implements Serializable {
	
	
	 @OneToMany(mappedBy = "offer", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	 private List<Product> products;
	 
	 @Id
	 @NotNull
	 @Column(unique = true, updatable = false, name = "OFFER_ID")
	 private Long offerId;

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public Long getOfferId() {
		return offerId;
	}

	public void setOfferId(Long offerId) {
		this.offerId = offerId;
	}
	
	public Offer(Long offerId, List<Product> products){
		this.offerId = offerId;
		this.products = products;
	}
	
	public Offer(){
		
	} 
	 
}
