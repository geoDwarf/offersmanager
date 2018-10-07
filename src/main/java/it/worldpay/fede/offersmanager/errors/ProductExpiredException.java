package it.worldpay.fede.offersmanager.errors;

import it.worldpay.fede.offersmanager.model.Product;

public class ProductExpiredException extends RuntimeException{
	private Long productId;
	private Product expiredProduct ;
	
	public ProductExpiredException(String message,Product expiredProduct){
		super(message);
		this.productId = expiredProduct.getProductId();
		this.expiredProduct = expiredProduct;
}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Product getExpiredProduct() {
		return expiredProduct;
	}

	public void setExpiredProduct(Product expiredProduct) {
		this.expiredProduct = expiredProduct;
	}
	
	
}