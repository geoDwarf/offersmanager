package it.worldpay.fede.offersmanager.model;

//@Entity
//@Inheritance(strategy = InheritanceType.JOINED)
//@PrimaryKeyJoinColumn(name = "bookId")
public abstract class Book extends Product {
	
	
//	@Id 
//	@NotNull @NotEmpty
//	@Column(unique = true, name ="BOOK_ID")
//	private long bookId;
//
//	@Column(name= "AUTHOR")
//	private String author;
//	
//	@Column(name ="NUMBER_OF_PAGES")
//	private int numberOfPages;
//	
//	@Column(name ="COVER_TYPE")
//	private String coverType;
//
//	
//	
//	
//	public long getBookId() {
//		return bookId;
//	}
//
//	public void setBookId(long bookId) {
//		this.bookId = bookId;
//	}
//
//	public int getNumberOfPages() {
//		return numberOfPages;
//	}
//
//	public void setNumberOfPages(int numberOfPages) {
//		this.numberOfPages = numberOfPages;
//	}
//
//	public String getCoverType() {
//		return coverType;
//	}
//
//	public void setCoverType(String coverType) {
//		this.coverType = coverType;
//	}

public Book(Long productId){
	super(productId);
	
}
	
}
