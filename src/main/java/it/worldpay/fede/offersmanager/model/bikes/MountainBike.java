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
	
	
	public boolean isFullSuspended() {
		return isFullSuspended;
	}
	public void setFullSuspended(boolean isFullSuspended) {
		this.isFullSuspended = isFullSuspended;
	}
	public String getFrontForkBrand() {
		return frontForkBrand;
	}
	public void setFrontForkBrand(String frontForkBrand) {
		this.frontForkBrand = frontForkBrand;
	}
	public String getRearForkBrand() {
		return rearForkBrand;
	}
	public void setRearForkBrand(String rearForkBrand) {
		this.rearForkBrand = rearForkBrand;
	}
	public MountainBike(Long idProduct){
		super(idProduct);
	}
	public MountainBike(){
		super();
	}
}
