package br.com.controlefinancas.api.exceptions;

import java.sql.SQLIntegrityConstraintViolationException;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class ErrorHandler {

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<?> errorHandlerNotFound(){
		
		return ResponseEntity.notFound().build();
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> errorHandlerBadRequest(MethodArgumentNotValidException ex){
		
		var erros = ex.getFieldErrors();
		
		return ResponseEntity.badRequest().body(erros.stream().map(DataErrorsValidation::new).toList());
	}
	
	@ExceptionHandler(SQLIntegrityConstraintViolationException.class)
	public ResponseEntity<?> errorHandlerBadRequest(SQLIntegrityConstraintViolationException ex){
		
		var erro = "Dados insconsitentes na requisição";
		
		return ResponseEntity.badRequest().body(new GenericMessage(erro));
	}
	
	private record DataErrorsValidation(String field, String message) {
		
		public DataErrorsValidation(FieldError error) {
			this(error.getField(), error.getDefaultMessage());
		}
	}
	
	private record GenericMessage(String mensagem) {
	}
}
