package it.worldpay.fede.offersmanager.errors.exception;

import it.worldpay.fede.offersmanager.model.product.Product;

public class ResourceNotFoundException extends RuntimeException{
	
	private Long productId;
	private Product productNotFound = null;
	
	public ResourceNotFoundException(String message,Long productId){
		super(message+": "+ productId);
		this.productId = productId;
	}


	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Product getProductNotFound() {
		return productNotFound;
	}

	public void setProductNotFound(Product productNotFound) {
		this.productNotFound = productNotFound;
	}
	
}
