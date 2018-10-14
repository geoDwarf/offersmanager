package it.worldpay.fede.offersmanager.model.product.book;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.PrimaryKeyJoinColumn;

import it.worldpay.fede.offersmanager.model.product.Product;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@PrimaryKeyJoinColumn(name = "bookId")
public abstract class Book extends Product {
	
	@Column(name= "AUTHOR")
	private String author;
	
	@Column(name ="NUMBER_OF_PAGES")
	private int numberOfPages;
	
	@Column(name ="COVER_TYPE")
	private String coverType;

	
	public int getNumberOfPages() {
		return numberOfPages;
	}

	public void setNumberOfPages(int numberOfPages) {
		this.numberOfPages = numberOfPages;
	}

	public String getCoverType() {
		return coverType;
	}

	public void setCoverType(String coverType) {
		this.coverType = coverType;
	}

public Book(Long productId){
	super(productId);
	
}

public Book(){
	super();
	
}
}
