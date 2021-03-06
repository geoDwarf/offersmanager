package it.worldpay.fede.offersmanager.model.product.bike;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.PrimaryKeyJoinColumn;

import it.worldpay.fede.offersmanager.model.product.Product;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@PrimaryKeyJoinColumn(name = "bikeId")
public abstract class Bike extends Product{
	
	@Column( name ="BRAND")
	private String brand; 
	
	
	@Column( name ="SPEEDS")
	private int speeds;

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public int getSpeeds() {
		return speeds;
	}

	public void setSpeeds(int speeds) {
		this.speeds = speeds;
	}
	
	public Bike(Long idProduct){
	super(idProduct);
	}
	public Bike(){
		super();
		}
	
}
