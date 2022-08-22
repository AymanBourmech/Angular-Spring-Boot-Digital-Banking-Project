package com.esps.ebankingbackend.exceptions;

public class BalanceNotSufficientException extends Exception {

	private static final long serialVersionUID = 1L;

	public BalanceNotSufficientException(String message) {
		super(message);
	}

}
