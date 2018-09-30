package it.worldpay.fede.offersmanager.model.bikes;

import it.worldpay.fede.offersmanager.model.Bike;

//@Entity
public class MountainBike extends Bike  {
	
//	@Id
//	@NotNull @NotEmpty
//	@Column(unique = true,name="MOUNTAIN_BIKE")
//	private long mountainBikeId;
//	
//	@Column(name = "IS_FULL_SUSPENDED")
//	private boolean isFullSuspended;
//	
//	@Column(name = "FRONT_FORK_BRAND")
//	private String frontForkBrand;
//	
//	@Column(name = "REAR_FORK_BRAND")
//	private String rearForkBrand;
	
	public MountainBike(Long idProduct){
		super(idProduct);
	}
}
