package br.com.guzzmega.financial.exception;

import java.io.Serial;

public class ValidationException extends RuntimeException {

	@Serial
	private static final long serialVersionUID = -1030566094599439352L;

	public ValidationException(String message){
		super(message);
	}
}
