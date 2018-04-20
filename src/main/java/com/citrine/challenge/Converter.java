package com.citrine.challenge;

import java.math.BigDecimal;
import java.math.MathContext;

public class Converter {

	private static final String OPERATORS = "()/*";

	static final MathContext MATH_CONTEXT = new MathContext(14);
			
	private Conversion[] table = {
		new Conversion("minute", "min", "s", "60"),
		new Conversion("hour", "h", "s", "3600"),
		new Conversion("day", "d", "s", "86400"),
		new Conversion("degree", "°", "rad", "1.7453292519943e-2"),
		new Conversion(null, "'", "rad", "2.9088820866572e-4"),
		new Conversion("second", "\"", "rad", "4.8481368110954e-6"), 
		new Conversion("hectare", "ha", "m2", "10000"),
		new Conversion("litre", "L", "m3", "0.001"),
		new Conversion("tonne", "t", "kg", "1000.0")
	};
	
	private Conversion getConversion(String name) {
		
		if (name != null) {
			for (Conversion conversion : table) {
				if (name.equals(conversion.name) || name.equals(conversion.symbol)) return conversion;
			}
		}
		
		throw new IllegalArgumentException("Could not find " + name + " in list of valid unit names and symbols");
	}
	
	private void validate(String name) {
		
		int paren = 0;
		for (int index = 0; index < name.length(); index++) {
			Character ch = name.charAt(index);
			if (ch == ')') paren--;
			if (ch == '(') paren++;
			if (paren < 0) {
				throw new IllegalArgumentException("The unit string " + name + " has mismatched parenthesis");
			}
		}
		
		if (paren != 0) {
			throw new IllegalArgumentException("The unit string " + name + " has mismatched parenthesis");
		}
	}

	private BigDecimal evaluate(String equation) {
		
		String numStr = "";
		BigDecimal value = BigDecimal.ONE;
		
		for (int index = 0; index < equation.length(); index++) {
			Character ch = equation.charAt(index);
			if (OPERATORS.contains(ch.toString())) {
				if (!numStr.isEmpty()) value = new BigDecimal(numStr, MATH_CONTEXT);
				if (ch == ')') {
					return value;
				} else if (ch == '(') {
					value = evaluate(equation.substring(index+1));
					index = equation.indexOf(")", index+1);
					System.out.println(index);
				} else if (ch == '*') {
					return value.multiply(evaluate(equation.substring(index+1)), MATH_CONTEXT);
				} else if (ch == '/') {
					return value.divide(evaluate(equation.substring(index+1)), MATH_CONTEXT);
				}
			} else {
				numStr = numStr + ch;
			}
		}
		
		return new BigDecimal(numStr, MATH_CONTEXT);
	}

	public UnitInfo convert(String units) {
		
		String unit = "";
		String nameString = "";
		String multiplierString = "";
		units = units.replaceAll("\\s","");
		validate(units);
		
		for (Character ch : units.toCharArray()) {
			if (OPERATORS.contains(ch.toString())) {
				if (!unit.isEmpty()) {
					Conversion con = getConversion(unit);
					multiplierString = multiplierString + con.multiplier;
					nameString = nameString + con.siName;
					unit = "";
				}
				multiplierString = multiplierString + ch;
				nameString = nameString + ch;
			} else {
				unit = unit + ch;
			}
		}
		
		if (!unit.isEmpty()) {
			Conversion con = getConversion(unit);
			multiplierString = multiplierString + con.multiplier;
			nameString = nameString + con.siName;
		}

		BigDecimal multiplier = evaluate(multiplierString);
		return new UnitInfo(nameString, multiplier);
	}
	
}
