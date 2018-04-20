package com.citrine.challenge;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Objects;

public class UnitInfo implements Serializable {

	private static final long serialVersionUID = -4028561763948642515L;

	private String unit_name;
	private BigDecimal multiplication_factor;
	
	public UnitInfo() {
	}
	
	public UnitInfo(String unit_name, BigDecimal multiplication_factor) {
		this.unit_name = unit_name;
		this.multiplication_factor = multiplication_factor;
	}

	public UnitInfo(String unit_name, String multiplication_factor, MathContext mc) {
		this.unit_name = unit_name;
		this.multiplication_factor = new BigDecimal(multiplication_factor, mc);
	}

	public String getUnit_name() {
		return unit_name;
	}

	public BigDecimal getMultiplication_factor() {
		return multiplication_factor;
	}
	
	@Override
	public boolean equals(Object other) {
		
	    if (this == other)
	        return true;
	    if (other == null)
	        return false;
	    if (getClass() != other.getClass())
	        return false;

	    UnitInfo ui = (UnitInfo) other;
	    return Objects.equals(unit_name, ui.unit_name) &&
	            Objects.equals(multiplication_factor, ui.multiplication_factor);
	}

	public void append(char ch) {
		unit_name = unit_name + ch;
	}
 
	public void append(String str) {
		unit_name = unit_name + str;
	}
	
	public void replace(String str) {
		unit_name = str;
	}
	
	public void setName(String name) {
		unit_name = name;
	}

	public void setMultiplicationFactor(BigDecimal mf) {
		multiplication_factor = mf;
	}

}
