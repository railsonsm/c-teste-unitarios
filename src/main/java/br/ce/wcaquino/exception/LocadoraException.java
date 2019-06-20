package br.ce.wcaquino.exception;

public class LocadoraException extends Exception{
	
	private String msg;

	private static final long serialVersionUID = 1L;

	public LocadoraException(String msg) {
		super(msg);
	}
	
	

}
