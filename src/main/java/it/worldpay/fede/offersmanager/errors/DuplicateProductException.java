package it.worldpay.fede.offersmanager.errors;

public class DuplicateProductException extends RuntimeException{
	
	private Long productId;
	
	public DuplicateProductException(String message, Long productId){
		super(message+": "+ productId);
		this.productId = productId;
	}
}
