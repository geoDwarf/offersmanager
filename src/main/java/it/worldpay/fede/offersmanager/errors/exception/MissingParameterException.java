package it.worldpay.fede.offersmanager.errors.exception;

import it.worldpay.fede.offersmanager.model.product.Product;

public class MissingParameterException extends RuntimeException {
	private Long productId;
	private Product missingParameterProduct;
	
	public MissingParameterException(String message, Product missingParameterProduct){
		super(message);
		this.productId = missingParameterProduct.getProductId();
		this.missingParameterProduct = missingParameterProduct;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Product getMissingParameterProduct() {
		return missingParameterProduct;
	}

	public void setMissingParameterProduct(Product missingParameterProduct) {
		this.missingParameterProduct = missingParameterProduct;
	}
	
}
