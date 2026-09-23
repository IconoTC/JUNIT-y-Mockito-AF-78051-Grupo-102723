package com.example;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Dummy {
	public double suma(double a, double b) {
		return roundIEEE754(a + b);
	}
	public double divide(int a, int b) {
		return roundIEEE754(a / b);
	}
	public double divide(double a, double b) {
		if(b == 0) 
			throw new ArithmeticException("/ by zero");
		if(b < 0) 
			throw new IllegalArgumentException("b no puede ser negativo");
		return a / b;
	}
	

	private double roundIEEE754(double o) {
		return BigDecimal.valueOf(o)
				.setScale(16, RoundingMode.HALF_UP)
				.doubleValue();
	}

}
