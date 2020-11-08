package com.udec.exception;

public class BussinesException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public BussinesException(String mensaje) {
		super(mensaje);
	}

}
