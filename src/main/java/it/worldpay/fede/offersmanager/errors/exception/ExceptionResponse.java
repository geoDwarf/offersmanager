package it.worldpay.fede.offersmanager.errors.exception;

import it.worldpay.fede.offersmanager.model.product.Product;
import it.worldpay.fede.offersmanager.model.product.food.Gelato;

public class ExceptionResponse {

    private String errorCode;
    private String errorMessage;
    private Product product;

    public ExceptionResponse() {
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
    
    
}