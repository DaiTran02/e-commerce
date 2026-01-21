package ecommerce.core.user.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import ecommerce.core.user.dto.ErrorResponse;
import ecommerce.core.user.exception.EmailAlreadyExistsException;
import ecommerce.core.user.exception.InvalidCredentialsException;
import ecommerce.core.user.exception.UserNotFoundException;
import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(EmailAlreadyExistsException.class)
	public ResponseEntity<ErrorResponse> handleEmailExists(EmailAlreadyExistsException emailAlreadyExistsException, HttpServletRequest request){
		ErrorResponse errorResponse = ErrorResponse.of(HttpStatus.CONFLICT.value(), "Email conflit", emailAlreadyExistsException.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleUserNotFound(UserNotFoundException userNotFoundException, HttpServletRequest request){
		ErrorResponse errorResponse = ErrorResponse.of(HttpStatus.NOT_FOUND.value(), "Not found user", userNotFoundException.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
	}
	
	@ExceptionHandler(InvalidCredentialsException.class)
	public ResponseEntity<ErrorResponse> handleInvalidCredentials(InvalidCredentialsException invalidCredentialsException, HttpServletRequest request){
		ErrorResponse errorResponse = ErrorResponse.of(HttpStatus.NOT_ACCEPTABLE.value(), "Wrong password or email", invalidCredentialsException.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(errorResponse);
	}
	

}
