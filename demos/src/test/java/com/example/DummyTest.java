package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayName("Pruebas de la clase Dummy")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class DummyTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void test_Suma_dos_enteros() {
		var c = new Dummy();
		
		var actual = c.suma(1, 2);
		
		assertEquals(3,  actual);
	}
	
	@Test
	void test_Suma_dos_decimales() {
		var c = new Dummy();
		
		assertEquals(3.0, c.suma(2.0, 1.0));
	}

	@Test
	@DisplayName("Valida el error de precisión IEEE754")
	void testSumaIEEE() {
		var c = new Dummy();
		
		var actual = c.suma(0.1, 0.2);
		
		assertEquals(0.3,  actual);
		assertEquals(0.1,  c.suma(1, -0.9));
	}

}
