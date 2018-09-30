package it.worldpay.fede.offersmanager.model.books;

import it.worldpay.fede.offersmanager.model.Book;

//@Entity
public class FantasyBook extends Book {
	
	
//	@Id
//	@NotNull 
//	@Column(unique = true,name="FANTASYBOOK_ID")
//	private long fantasyBookId;
//	
//	@Column(name= "FANTASY_WORLD_NAME")
//	private String fantasyWorldName;
//	
//	@Column(name= "SETTING")
//	private String setting;
	
	public FantasyBook(Long idProduct){
		super(idProduct);		
	}
}
