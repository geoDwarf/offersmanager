package it.worldpay.fede.offersmanager.model;

//@Entity
//@Inheritance(strategy = InheritanceType.JOINED)
//@PrimaryKeyJoinColumn(name = "bikeId")
public abstract class Bike extends Product{
	
//	@Id
//	@NotNull
//	@Column(unique = true, name ="BIKE_ID")
//	private long bikeId;
//	
//	@Column( name ="BRAND")
//	private String brand; 
//	
//	@NotNull
//	@Column( name ="SPEEDS")
//	private int speeds;
//
//	public long getBikeId() {
//		return bikeId;
//	}
//
//	public void setBikeId(long bikeId) {
//		this.bikeId = bikeId;
//	}
//
//	public String getBrand() {
//		return brand;
//	}
//
//	public void setBrand(String brand) {
//		this.brand = brand;
//	}
//
//	public int getSpeeds() {
//		return speeds;
//	}
//
//	public void setSpeeds(int speeds) {
//		this.speeds = speeds;
//	}
//	
	public Bike(Long idProduct){
	super(idProduct);
	}
	
}
