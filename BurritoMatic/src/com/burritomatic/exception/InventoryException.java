package com.burritomatic.exception;

public class InventoryException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6251728053619388195L;

	public InventoryException(String err) {
		super(err);
	}
}
