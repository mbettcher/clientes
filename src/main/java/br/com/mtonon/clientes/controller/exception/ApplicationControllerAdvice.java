package br.com.mtonon.clientes.controller.exception;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import br.com.mtonon.clientes.controller.exception.dto.ApiErrors;

@RestControllerAdvice
public class ApplicationControllerAdvice {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiErrors handleValidationErros(MethodArgumentNotValidException ex) {
		BindingResult buildingResult = ex.getBindingResult();
		List<String> messagens = buildingResult.getAllErrors()
			.stream()
			.map(error -> error.getDefaultMessage())
			.collect(Collectors.toList());
		return new ApiErrors(messagens);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ExceptionHandler(ResponseStatusException.class)
	public ResponseEntity handleResponseStatusException(ResponseStatusException ex) {
		String messageError = ex.getMessage();
		HttpStatusCode codeError = ex.getStatusCode();
		ApiErrors apiErrors = new ApiErrors(messageError);
		return new ResponseEntity(apiErrors, codeError);
	}
}
