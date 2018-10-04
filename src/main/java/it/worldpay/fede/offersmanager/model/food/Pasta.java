package it.worldpay.fede.offersmanager.model.food;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

import it.worldpay.fede.offersmanager.model.Food;

@Entity
@PrimaryKeyJoinColumn(name = "pastaId")
public class Pasta extends Food{

	
	@Column(name = "COOKING_TIME")
	private int cookingTime;
	
	@Column(name="DRESSING")
	private String dressing;
	
	@Column(name = "PASTA_TYPE")
	private String pastaType;

	
	public int getCookingTime() {
		return cookingTime;
	}

	public void setCookingTime(int cookingTime) {
		this.cookingTime = cookingTime;
	}

	public String getDressing() {
		return dressing;
	}

	public void setDressing(String dressing) {
		this.dressing = dressing;
	}

	public String getPastaType() {
		return pastaType;
	}

	public void setPastaType(String pastaType) {
		this.pastaType = pastaType;
	}
	
	public Pasta(){
		super();
	}
}
