package it.worldpay.fede.offersmanager.model.product.bike;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "fantasyBookId")
public class RoadBike extends Bike {

	@Column
	private double weight;
	
	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public RoadBike(Long idProduct){
		super(idProduct);
	}
	
	public RoadBike(){
		super();
	}
}
