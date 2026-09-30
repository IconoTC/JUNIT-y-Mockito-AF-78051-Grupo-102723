package com.example.demos;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class DummyImpl implements Dummy {
	@Override
	public double suma(double a, double b) {
		return roundIEEE754(a + b);
	}

	@Override
	public double divide(int a, int b) {
		return roundIEEE754(a / b);
	}

	@Override
	public double divide(double a, double b) {
		if (b == 0)
			throw new ArithmeticException("/ by zero");
		if (b < 0)
			throw new IllegalArgumentException("b no puede ser negativo");
		return a / b;
	}

	private double roundIEEE754(double o) {
		return BigDecimal.valueOf(o).setScale(16, RoundingMode.HALF_UP).doubleValue();
	}

	@Override
	public double sumaLenta(double a, double b) throws InterruptedException {
		Thread.sleep(1000);
		return roundIEEE754(a + b);
	}

	@Override
	public boolean esBisiesto(int año) {
		return (esMultiploDe4(año) && noEsMultiploDe100(año)) || esMultiploDe400(año);
//		if(año == 2024) return true;
//		if(año == 2023) return false;
//		throw new RuntimeException("No contemplado");
	}

	private boolean esMultiploDe400(int año) {
		return año % 400 == 0;
	}

	private boolean noEsMultiploDe100(int año) {
		return año % 100 != 0;
	}

	private boolean esMultiploDe4(int año) {
		return año % 4 == 0;
	}

	public byte edad(LocalDate nacimiento) {
		return (byte) ChronoUnit.YEARS.between(nacimiento, LocalDate.now());
	}	
}
