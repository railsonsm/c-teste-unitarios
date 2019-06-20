package br.ce.wcaquino.exception;

public class FilmeSemEstoqueExption extends Exception{
	
	private String msg;

	private static final long serialVersionUID = 1L;

	public FilmeSemEstoqueExption(String msg) {
		super(msg);
	}
	
	

}
