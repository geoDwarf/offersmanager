package it.worldpay.fede.offersmanager.model.books;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

import it.worldpay.fede.offersmanager.model.Book;

@Entity
@PrimaryKeyJoinColumn(name = "fantasyBookId")
public class FantasyBook extends Book {
	
	@Column(name= "FANTASY_WORLD_NAME")
	private String fantasyWorldName;
	
	@Column(name= "SETTING")
	private String setting;
	
		
	public String getFantasyWorldName() {
		return fantasyWorldName;
	}

	public void setFantasyWorldName(String fantasyWorldName) {
		this.fantasyWorldName = fantasyWorldName;
	}

	public String getSetting() {
		return setting;
	}

	public void setSetting(String setting) {
		this.setting = setting;
	}
	

	public FantasyBook(Long idProduct){
		super(idProduct);		
	}
	
	public FantasyBook(){
		super();		
	}
}
