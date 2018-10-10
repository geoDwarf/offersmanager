package it.worldpay.fede.offersmanager.model.bikes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

import it.worldpay.fede.offersmanager.model.Bike;

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
