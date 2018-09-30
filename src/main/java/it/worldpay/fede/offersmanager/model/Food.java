package it.worldpay.fede.offersmanager.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.validation.constraints.NotNull;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Food extends Product{
	
	@NotNull 
	@Column(unique = true, name ="FOOD_ID")
	private long foodId;
	
	@Column(name ="ORIGIN")
	private String origin;

	@Column(name = "CALORIES")
	private double calories;

	@Column(name= "EXPIRING_DATE")
	private Date expiringDate;
	
	
	public long getFoodId() {
		return foodId;
	}

	public void setFoodId(long foodId) {
		this.foodId = foodId;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public double getCalories() {
		return calories;
	}

	public void setCalories(double calories) {
		this.calories = calories;
	}

	public Date getExpiringDate() {
		return expiringDate;
	}

	public void setExpiringDate(Date expiringDate) {
		this.expiringDate = expiringDate;
	}
	
	public Food(Long id){
		super(id);
	}
	
	public Food(){
	
	}
}
