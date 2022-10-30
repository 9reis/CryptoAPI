package com.reis.cryptoApp.dto;

import java.math.BigDecimal;

public class CoinTransationDTO {
	
	private String name; 
	private BigDecimal quantity;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigDecimal getQuantity() {
		return quantity;
	}
	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}
	
	
}
