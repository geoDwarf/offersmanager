package it.worldpay.fede.offersmanager.errors;

public class ProductNotFoundException extends RuntimeException{
	
	private Long productId;
	
	public ProductNotFoundException(String message,Long productId){
		super(message+": "+ productId);
		this.productId = productId;
	}
}
