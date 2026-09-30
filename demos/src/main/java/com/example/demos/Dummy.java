package com.example.demos;

import java.time.LocalDate;

public interface Dummy {

	double suma(double a, double b);

	double divide(int a, int b);

	double divide(double a, double b);

	double sumaLenta(double a, double b) throws InterruptedException;

	boolean esBisiesto(int año);
	
	byte edad(LocalDate nacimiento);

}