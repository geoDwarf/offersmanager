package it.worldpay.fede.offersmanager.model.food;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

//@Entity
public class Pizza 
//extends Food
{
	
	@Id
	@NotNull
	@Column(unique = true,name="PIZZA_ID")
	private long pizzaId;
	
	@Column(name= "DRESSING")
	private String dressing;

	public long getPizzaId() {
		return pizzaId;
	}

	public void setPizzaId(long pizzaId) {
		this.pizzaId = pizzaId;
	}

	public String getDressing() {
		return dressing;
	}

	public void setDressing(String dressing) {
		this.dressing = dressing;
	}
	
	
}
