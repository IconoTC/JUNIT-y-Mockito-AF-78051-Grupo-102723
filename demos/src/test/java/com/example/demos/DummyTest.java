package com.example.demos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.lang.reflect.InvocationTargetException;
import java.time.Duration;
import java.time.temporal.TemporalUnit;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.ClassOrderer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestClassOrder;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import com.example.test.anotations.Smoke;
import com.example.test.anotations.UnitTest;
import com.example.test.utils.PrivateMethod;

@DisplayName("Pruebas de la clase Dummy")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@TestClassOrder(ClassOrderer.OrderAnnotation.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
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
	@Order(10)
	class Metodo_Suma {
		@Nested
		@Order(1)
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
			double result;
			
			@Test
			@Timeout(unit = TimeUnit.SECONDS, value = 100)
			void test_Suma_Lenta() throws InterruptedException {
				var c = new Dummy();
				
//				assertTimeout(Duration.ofMillis(10), () -> result = c.sumaLenta(2.0, 1.0));
//				assertEquals(3.0, result);
				assertEquals(3.0, c.sumaLenta(2.0, 1.0));
			}
			
			
			@ParameterizedTest(name = "{displayName} => {0} + {1} = {2}")
			@CsvSource({
				"1,2,3", // esto es para ...
				"1,-2,-1", 
				"0.1, 0.2, 0.3", 
				"1,-0.9,0.1", 
				"0,0,0" })
			@DisplayName("Sumar")
			//@Tag("smocke")
			@Smoke
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
		@Order(2)
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
	@UnitTest
	@Order(40)
	class Metodo_Divide {
		@Nested
		@Order(1)
		class OK {
			@Test
			@DisplayName("Divide dos enteros")
			void testDivideEnteros() {
				var actual = fixure.divide(1, 2);
				
				assertEquals(0,  actual);
			}
			@Test
			@DisplayName("Divide dos decimales")
			@Smoke
			@UnitTest
			void testDivideReales() {
				var actual = fixure.divide(1.0, 2);
				
				assertEquals(0.5,  actual);
			}
			
		}
		@Nested
		@Order(2)
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

	@Nested
	@UnitTest
	@Order(5)
	class Metodo_Privado {
		@Test
		void testRoundIEEE754() throws NoSuchMethodException, SecurityException, IllegalAccessException, InvocationTargetException {
//			var actual = fixure.roundIEEE754(0.1 + 0.2);
			var actual = PrivateMethod.exec(fixure, "roundIEEE754", new Class[] { double.class }, (0.1 + 0.2));
			
			assertEquals(0.3,  actual);
		}
	}
	
//	Reglas de validación del bisiesto
//	Divisible por 4: Si el año se puede dividir exactamente entre 4, 
//		por lo general es bisiesto (como 2024 o 2028).
//	Excepción de los siglos (divisible por 100): Si el año termina en dos ceros y es divisible entre 100,
//		no es bisiesto.
//	Contraexcepción (divisible por 400): Si ese mismo año de fin de siglo también es divisible entre 400,
//		sí es bisiesto. Por ejemplo, el año 1900 no fue bisiesto, pero el año 2000 sí lo fue. 
	@Nested
	@UnitTest
	@Order(1)
	class Metodo_EsBisiesto {
		@ParameterizedTest(name = "El año {0} ES bisiesto")
		@ValueSource(ints = {2024, 2000})
		void OK(int caso) {
			var actual = fixure.esBisiesto(caso);
			assertTrue(actual);
		}
		@ParameterizedTest(name = "El año {0} NO ES bisiesto")
		@ValueSource(ints = {2023, 1900})
		void KO(int caso) {
			var actual = fixure.esBisiesto(caso);
			assertFalse(actual);
		}
	}

}
