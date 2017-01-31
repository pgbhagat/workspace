package com.burrito.cart;

public class Bill {

	float baseAmount;
	float vat;
	float serviceTax;
	float total;

	public Bill(float baseAmount) {
		this.baseAmount = baseAmount;
		this.vat = 12 / 100 * baseAmount;
		this.serviceTax = 5 / 100 * baseAmount;
		this.total = baseAmount + vat + serviceTax;
	}

	public float getBillAmount() {
		return this.total;
	}

	public String toString() {
		return "Details - " + "Base amount - " + baseAmount + "Vat - " + vat + "service Tax - " + serviceTax
				+ "Total bill - " + total;
	}
}
