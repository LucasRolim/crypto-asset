package com.crypto.cryptoassets.model;

import java.math.BigDecimal;

public class CsvWallet {

	private String symbol;
	private BigDecimal quantity;
	private BigDecimal price;

	public CsvWallet(String symbol, BigDecimal quantity, BigDecimal price) {
		super();
		this.symbol = symbol;
		this.quantity = quantity;
		this.price = price;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String toString() {
		return "CsvWallet{symbol='" + symbol + "\', quantity=" + quantity + ", price='" + price + "\'}";
	}
}
