package com.example.demos;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class PersonaTest {

	@Test
	@DisplayName("Se crea el objeto")
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
	}
	
	@ParameterizedTest(name = "{index} => con el nombre ''{0}''")
	@ValueSource(strings = {"PEPE", "Manuel" })	
	@DisplayName("Se crea el objeto con intantanea")
//	@RepeatedTest(value = 3, name = "{displayName} {currentRepetition}/{totalRepetitions}")
	void testInstantanePersona(String caso) {
		var persona = new Persona(1, caso, "Grillo", null);
		
		assertNotNull(persona);
		assertEquals("Persona [id=1, nombre=" + caso + ", apellidos=Grillo, fNacimiento=null]", persona.toString()); // muy general
	}

//	@Test
//	void testIsValid() {
//		fail("Not yet implemented");
//	}

}
