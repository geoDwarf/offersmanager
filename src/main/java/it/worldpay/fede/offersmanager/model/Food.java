package it.worldpay.fede.offersmanager.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.PrimaryKeyJoinColumn;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@PrimaryKeyJoinColumn(name = "bookId")
public abstract class Food extends Product{
	
	@Column(name ="ORIGIN")
	private String origin;

	@Column(name = "CALORIES")
	private double calories;

	@Column(name= "EXPIRING_DATE")
	private Date expiringDate;
	
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
		super();
	}
}
