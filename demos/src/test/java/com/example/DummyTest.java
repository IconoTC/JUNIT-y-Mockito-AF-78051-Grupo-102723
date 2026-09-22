package com.example;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.Dummy;

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
	void testSuma() {
		var c = new Dummy();
		
		var actual = c.suma(1, 2);
		
		assertEquals(3,  actual);
	}

	@Test
	void testSumaIEEE() {
		var c = new Dummy();
		
		var actual = c.suma(0.1, 0.2);
		
		assertEquals(0.3,  actual);
	}

}
