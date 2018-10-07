package it.worldpay.fede.offersmanager.errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends AbstractGlobalExceptionHandler {
	


    // HANDLING CUSTOM EXCEPTIONS
	@ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ExceptionResponse> productNotFound(ProductNotFoundException ex) {
    	return buildAndSendErrorResponse(ex, "not found", HttpStatus.NOT_FOUND);
   }
//
	@ExceptionHandler(DuplicateProductException.class)
	public ResponseEntity<ExceptionResponse> productDuplicated(DuplicateProductException ex) {
        return buildAndSendErrorResponse(ex, "duplicated", HttpStatus.NOT_FOUND);
    }
//
//    @ExceptionHandler(DuplicateProductIdException.class)
//    public ResponseEntity<ExceptionResponse> productIdDuplicated(DuplicateProductIdException ex) {
//        return buildAndSendErrorResponse(ex, ERROR_CODE_DUPLICATE_PRODUCT_ID, HttpStatus.CONFLICT);
//    }
//
//
//    // HANDLING SOME NON-CUSTOM EXCEPTIONS
//    @ExceptionHandler(ConstraintViolationException.class)
//    public ResponseEntity<ExceptionResponse> constraintViolation(ConstraintViolationException ex) {
//        return buildAndSendErrorResponse(ex, ERRROR_CODE_CONSTRAINT_VIOLATION, HttpStatus.BAD_REQUEST);
//    }
//
//    @ExceptionHandler(UnexpectedTypeException.class)
//    public ResponseEntity<ExceptionResponse> requestWithUnexpectedType(UnexpectedTypeException ex) {
//        return buildAndSendErrorResponse(ex, ERROR_CODE_MALFORMED_REQUEST, HttpStatus.BAD_REQUEST);
//    }
//
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<ExceptionResponse> requestWithInvalidArgument(MethodArgumentNotValidException ex) {
//        return buildAndSendErrorResponse(ex, ERROR_CODE_MALFORMED_REQUEST, HttpStatus.BAD_REQUEST);
//    }
//
//    @ExceptionHandler(HttpMessageNotReadableException.class)
//    public ResponseEntity<ExceptionResponse> requestNotReadable(HttpMessageNotReadableException ex) {
//        return buildAndSendErrorResponse(ex, ERROR_CODE_MALFORMED_REQUEST, HttpStatus.BAD_REQUEST);
//    }
//
//    @ExceptionHandler(JsonParseException.class)
//    public ResponseEntity<ExceptionResponse> requestNotParsable(JsonParseException ex) {
//        return buildAndSendErrorResponse(ex, ERROR_CODE_MALFORMED_REQUEST, HttpStatus.BAD_REQUEST);
//    }
//
//    @ExceptionHandler(TransactionSystemException.class)
//    public ResponseEntity<ExceptionResponse> invalidRequest(TransactionSystemException ex) {
//        return buildAndSendErrorResponse(ex, ERROR_CODE_MALFORMED_REQUEST, HttpStatus.BAD_REQUEST);
//    }

	
}
