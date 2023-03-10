package br.com.controlefinancas.api.exceptions;

public class BussinesRuleException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public BussinesRuleException(String msg) {
		super(msg);
	}

}
