package it.worldpay.fede.offersmanager.errors;

import it.worldpay.fede.offersmanager.model.Product;

public class DuplicateProductException extends RuntimeException{
	
	private Long productId;
	private Product duplicatedProduct;
	
	public DuplicateProductException(String message,  Product duplicatedProduct){
		super(message+": "+ duplicatedProduct.getProductId());
		this.productId = duplicatedProduct.getProductId();
		this.duplicatedProduct = duplicatedProduct;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Product getDuplicatedProduct() {
		return duplicatedProduct;
	}

	public void setDuplicatedProduct(Product duplicatedProduct) {
		this.duplicatedProduct = duplicatedProduct;
	}
	
}
