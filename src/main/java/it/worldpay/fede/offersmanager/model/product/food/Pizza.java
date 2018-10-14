package it.worldpay.fede.offersmanager.model.product.food;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

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
	
	public Pizza(Long productId){
		super(productId);
		
	}
}
