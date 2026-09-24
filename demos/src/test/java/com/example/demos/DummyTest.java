package com.example.demos;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("Pruebas de la clase Dummy")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class DummyTest {
	Dummy fixure;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		fixure = new Dummy();
	}

	@Nested
	class Metodo_Suma {
		@Nested
		class OK {
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
			
			@ParameterizedTest(name = "{displayName} => {0} + {1} = {2}")
			@CsvSource({
				"1,2,3", // esto es para ...
				"1,-2,-1", 
				"0.1, 0.2, 0.3", 
				"1,-0.9,0.1", 
				"0,0,0" })
			@DisplayName("Sumar")
			void testSumas(double operando1, double operando2, double resultado) {
				var actual = fixure.suma(operando1, operando2);
				
				assertEquals(resultado,  actual);
//				assertEquals(operando1 + operando2,  actual);
			}
			
			@ParameterizedTest(name = "{displayName} => {0} + {1} = {2}")
			@CsvFileSource(files = "casos-de-suma.csv", numLinesToSkip = 1)
			@DisplayName("Dirigido por datos")
			void testSumasFichero(double operando1, double operando2, double resultado) {
				var actual = fixure.suma(operando1, operando2);
				
				assertEquals(resultado,  actual);
			}
			
		}
		@Nested
		class KO {
			@Test
			@DisplayName("Valida el error de precisión IEEE754")
			void testSumaIEEE() {
				var c = new Dummy();
				
				var actual = c.suma(0.1, 0.2);
				
				assertEquals(0.3,  actual);
				assertEquals(0.1,  c.suma(1, -0.9)); // mala practica
			}
			
		}
		
	}
	@Nested
	class Metodo_Divide {
		@Nested
		class OK {
			@Test
			@DisplayName("Divide dos enteros")
			void testDivideEnteros() {
				var actual = fixure.divide(1, 2);
				
				assertEquals(0,  actual);
			}
			@Test
			@DisplayName("Divide dos decimales")
			void testDivideReales() {
				var actual = fixure.divide(1.0, 2);
				
				assertEquals(0.5,  actual);
			}
			
		}
		@Nested
		class KO {
			@Test
			@DisplayName("Divide por 0")
			// @Disabled("Pendiente de solucion")
			void testDivideEnteros() {
				var ex = assertThrows(Exception.class, () -> fixure.divide(1, 0));
			}
			@Test
			@DisplayName("Divide por 0 real")
			// @Disabled("Pendiente de solucion")
			void testDivideDecimal() {
//				var actual = fixure.divide((double)1.0, (double)0.0);
				var ex = assertThrows(ArithmeticException.class, () -> fixure.divide(1.0, 0.0));
				assertEquals("/ by zero", ex.getMessage());
//				assertEquals(Double.POSITIVE_INFINITY,  fixure.divide(1.0, 0.0));
			}
			@Test
			@DisplayName("Divide por 0 real Sin Assert")
			// @Disabled("Pendiente de solucion")
			void testDivideDecimalSinAssert() {
				try {
					fixure.divide((double)1.0, (double)0.0);
					fail("No ha dado la excepción");
				} catch (ArithmeticException ex) {
					assertEquals("/ by zero", ex.getMessage());
				} catch (Exception ex) {
					fail("Ha dado una excepción " + ex.getClass().getCanonicalName());
				}
			}
			
		}
		
	}


}
