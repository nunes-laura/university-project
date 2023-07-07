package com.example.demo.exceptions;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerException {

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<ErrorHandler> handle(MethodArgumentNotValidException ex) {
		
		List<FieldError> errorList = ex.getBindingResult().getFieldErrors();
		
		List<ErrorHandler> list = new ArrayList<>();
		
		errorList.forEach(error -> list.add(new ErrorHandler(error.getField(), error.getDefaultMessage())));
		
		
		return list;

}

}
