package it.worldpay.fede.offersmanager.model.bikes;

import javax.persistence.Column;
import javax.persistence.Entity;

import it.worldpay.fede.offersmanager.model.Bike;

@Entity
public class MountainBike extends Bike  {
	
	@Column(name = "IS_FULL_SUSPENDED")
	private boolean isFullSuspended;
	
	@Column(name = "FRONT_FORK_BRAND")
	private String frontForkBrand;
	
	@Column(name = "REAR_FORK_BRAND")
	private String rearForkBrand;
	
	public MountainBike(Long idProduct){
		super(idProduct);
	}
	public MountainBike(){
		super();
	}
}
