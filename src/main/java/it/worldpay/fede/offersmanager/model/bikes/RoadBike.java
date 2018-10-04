package it.worldpay.fede.offersmanager.model.bikes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

import it.worldpay.fede.offersmanager.model.Bike;

@Entity
@PrimaryKeyJoinColumn(name = "fantasyBookId")
public class RoadBike extends Bike {

	@Column
	private long weight;
	
	public RoadBike(Long idProduct){
		super(idProduct);
	}
}
