package it.worldpay.fede.offersmanager.model.food;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

import it.worldpay.fede.offersmanager.model.Food;

@Entity
@PrimaryKeyJoinColumn(name = "gelatoId")
public class Gelato extends Food 
{

	@Column(name = "FLAVOR_ONE")
	private Flavor flavorOne;
	
	@Column(name= "FLAVOR_TWO")
	private Flavor flavorTwo;
	
	@Column(name = "FLAVOR_THREE")
	private Flavor flavorThree;
	
	@Column(name = "IS_SERVED_WITH_WIPPED_CREAM")
	private boolean isServedWithWippedCream;
	
	public enum Flavor{
		CHOCOLATE,
		VANILLA,
		PISTACHIO,
		BANANA,
		COCONUT
	}
	
	public Gelato(){
		super();
	}
	
	public Gelato(boolean isServedWithWippedCream,Long productId){
		super(productId);
		this.isServedWithWippedCream = isServedWithWippedCream;
	}

	public Flavor getFlavorOne() {
		return flavorOne;
	}

	public void setFlavorOne(Flavor flavorOne) {
		this.flavorOne = flavorOne;
	}

	public Flavor getFlavorTwo() {
		return flavorTwo;
	}

	public void setFlavorTwo(Flavor flavorTwo) {
		this.flavorTwo = flavorTwo;
	}

	public Flavor getFlavorThree() {
		return flavorThree;
	}

	public void setFlavorThree(Flavor flavorThree) {
		this.flavorThree = flavorThree;
	}

	public boolean isServedWithWippedCream() {
		return isServedWithWippedCream;
	}

	public void setServedWithWippedCream(boolean isServedWithWippedCream) {
		this.isServedWithWippedCream = isServedWithWippedCream;
	}
	
}
