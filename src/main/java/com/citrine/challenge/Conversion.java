package com.citrine.challenge;

import java.math.BigDecimal;

public class Conversion {

	final String name;
	final String symbol;
	final String siName;
	final BigDecimal multiplier;
	
	public Conversion(String name, String symbol, String type, String multiplier) {
		
		this.name = name;
		this.symbol = symbol;
		this.siName = type;
		this.multiplier = new BigDecimal(multiplier);
	}

	public String getName() {
		return name;
	}

	public String getSymbol() {
		return symbol;
	}

	public String getSiName() {
		return siName;
	}

	public BigDecimal getMultiplier() {
		return multiplier;
	}
	
}

