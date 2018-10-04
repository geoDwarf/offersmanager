package it.worldpay.fede.offersmanager.model.food;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

import it.worldpay.fede.offersmanager.model.Food;

@Entity
@PrimaryKeyJoinColumn(name = "pizzaId")
public class Pizza extends Food
{	
	@Column(name= "DRESSING")
	private String dressing;

	
	public String getDressing() {
		return dressing;
	}

	public void setDressing(String dressing) {
		this.dressing = dressing;
	}
	
	public Pizza(){
		super();
	}
}
