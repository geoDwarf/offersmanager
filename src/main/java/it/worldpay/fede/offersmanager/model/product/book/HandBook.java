package it.worldpay.fede.offersmanager.model.product.book;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "handBookId")
public class HandBook extends Book{
	
	@Column(name="LEVEL")
	private Level level;
	
	@Column(name="FIELD")
	private String field;
	
	
	public enum Level {
	    EXPERT,
	    INTERMEDIATE,
	    BEGINNER
	}

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}
	
	public HandBook(Long idProduct){
		super(idProduct);	
	}
	
	public HandBook(){
		super();	
	}
}