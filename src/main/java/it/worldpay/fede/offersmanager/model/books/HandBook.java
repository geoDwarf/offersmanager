package it.worldpay.fede.offersmanager.model.books;

import it.worldpay.fede.offersmanager.model.Book;

//@Entity
public class HandBook extends Book{
	
//	@Id
//	@NotNull 
//	@Column(unique = true,name="HANDBOOK_ID")
//	private long handbookId;
//
//	@Column(name="LEVEL")
//	private Level level;
//	
//	@Column(name="FIELD")
//	private String field;
//	
//	
//	public enum Level {
//	    EXPERT,
//	    INTERMEDIATE,
//	    BEGINNER
//	}
//
//
//	public long getHandbookId() {
//		return handbookId;
//	}
//
//
//	public void setHandbookId(long handbookId) {
//		this.handbookId = handbookId;
//	}
//
//
//	public Level getLevel() {
//		return level;
//	}
//
//
//	public void setLevel(Level level) {
//		this.level = level;
//	}
//
//
//	public String getField() {
//		return field;
//	}
//
//
//	public void setField(String field) {
//		this.field = field;
//	}
	
	public HandBook(Long idProduct){
		super(idProduct);	
	}
}