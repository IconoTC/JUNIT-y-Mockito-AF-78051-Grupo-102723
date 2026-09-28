package com.example.demos;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeFalse;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import com.example.test.anotations.UnitTest;

@UnitTest
class PersonaTest {

	@Test
	@DisplayName("Se crea el objeto")
	@Tag("smoke")
	void testPersona() {
		var persona = new Persona(1, "Pepito", "Grillo", null);
		
		// assertEquals(new Persona(1, "Pepitooooo", "Grillo", null), persona); // muy general
		assertNotNull(persona);
		assertAll("Propiedades", 
			() -> assertEquals(1, persona.getId()), 
			() -> assertEquals("Pepito", persona.getNombre(), "nombre"), 
			() -> assertEquals("Grillo", persona.getApellidos(), "apellido"), 
			() -> assertNull(persona.getFNacimiento())
			);
//		assumeFalse(persona.getFNacimiento()==null);
		
	}
	@Test
	@DisplayName("testSetter")
	@Tag("smoke")
	void testSetter() {
		var persona = new Persona(0, "x", "", null);
		
		persona.setId(1);
		persona.setNombre("Pepito");
		persona.setApellidos("Grillo");
		
		assertAll("Propiedades", 
			() -> assertEquals(1, persona.getId()), 
			() -> assertEquals("Pepito", persona.getNombre(), "nombre"), 
			() -> assertEquals("Grillo", persona.getApellidos(), "apellido"), 
			() -> assertNull(persona.getFNacimiento())
			);
//		assumeFalse(persona.getFNacimiento()==null);
		
	}
		
	Persona persona;
	
	@ParameterizedTest(name = "{index} => con el nombre ''{0}''")
	@ValueSource(strings = {"PEPE", "Manuel" })	
	@DisplayName("Se crea el objeto con intantanea")
//	@NullAndEmptySource
//	@RepeatedTest(value = 3, name = "{displayName} {currentRepetition}/{totalRepetitions}")
	void testInstantanePersona(String caso) {
		assertDoesNotThrow(() -> { persona = new Persona(1, caso, "Grillo", null); });
		
		assertNotNull(persona);
		assertEquals("Persona [id=1, nombre=" + caso + ", apellidos=Grillo, fNacimiento=null]", persona.toString()); // muy general
	}

	@Test
	void testIsEqual() {
//		assertEquals(new Persona(1, "pp", "Grillo", null), new Persona(1, "pp", "Grillo", null));
		var persona = new Persona(1, "Pepito", "Grillo", null);
		persona.equals(new Persona(1, "Pepito", "Grillo", null));
		assertTrue(true);
	}

//	@Test
//	void testIsValid() {
//		fail("Not yet implemented");
//	}

}
