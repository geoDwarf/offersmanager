package it.worldpay.fede.offersmanager.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import it.worldpay.fede.offersmanager.errors.exception.ExceptionResponse;
import it.worldpay.fede.offersmanager.model.product.Product;



public abstract class AbstractGlobalExceptionHandler {
	
	   public ResponseEntity<ExceptionResponse> buildAndSendErrorResponse(Exception ex, String errorCode,Product product, HttpStatus httpStatus) {
	        ExceptionResponse response = new ExceptionResponse();
	        response.setErrorCode(errorCode);
	        response.setErrorMessage(ex.getMessage());
	        response.setProduct(product);
	        return new ResponseEntity<>(response, httpStatus);
	    }

}
