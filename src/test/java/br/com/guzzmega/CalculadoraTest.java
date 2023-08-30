package br.com.guzzmega;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CalculadoraTest {

	@Autowired
	private Calculadora calc;

	private int contador = 0;          // inicia somente uma única vez
	//private static int contador = 0; // inicia sempre em cada @Test

	@BeforeEach
	public void setup() {
		System.out.println("Test: ");
	}

	@AfterEach
	public void teardown() {
		System.out.println("Finished.\n");
	}

	@BeforeAll
	public static void setupAll() {
		System.out.println("--- Before All ---");
	}

	@AfterAll
	public static void teardownAll() {
		System.out.println("--- After All ---");
	}

	@Test
	public void testSomar() {
		System.out.println(++contador);
		assertTrue(calc.soma(2, 3) == 5);
		assertEquals(5, calc.soma(2, 3));
	}

	@Test
	public void assertivas() {
		System.out.println(++contador);
		assertEquals("Casa", "Casa");
		assertNotEquals("Casa", "casa");
		assertTrue("casa".equalsIgnoreCase("CASA"));
		assertTrue("Casa".endsWith("sa"));
		assertFalse("Casa".startsWith("ca"));

		List<String> s1 = new ArrayList<>();
		List<String> s2 = new ArrayList<>();
		List<String> s3 = null;

		assertEquals(s1, s2);
		assertSame(s1, s1);
		assertNotEquals(s1, s3);
		assertNull(s3);
		assertNotNull(s1);
		//		Assertions.fail("Falhou pelo motivo A");
	}

	@Test
	public void deveRetornarNumeroInteiroNaDivisao() {
		System.out.println(++contador);
		float resultado = calc.dividir(6, 2);
		assertEquals(3, resultado);
	}

	@Test
	public void deveRetornarNumeroNegativoNaDivisao() {
		System.out.println(++contador);
		float resultado = calc.dividir(6, -2);
		assertEquals(-3, resultado);
	}

	@Test
	public void deveRetornarNumeroDecimalNaDivisao() {
		System.out.println(++contador);
		float resultado = calc.dividir(10, 3);
		assertEquals(3.3333332538604736, resultado);
		assertEquals(3.33, resultado, 0.01);
	}

	@Test
	public void deveRetornarZeroComNumeradorZeroNaDivisao() {
		float resultado = calc.dividir(0, 2);
		assertEquals(0, resultado);
	}

	@Test
	public void deveLancarExcecaoQuandoDividirPorZero_Junit4() {
		System.out.println(++contador);
		try {
			float resultado = 10 / 0;
			fail("Deveria ter sido lançado uma exceção na divisão");
		} catch (ArithmeticException e) {
			assertEquals("/ by zero", e.getMessage());
		}
	}

	@Test
	public void deveLancarExcecaoQuandoDividirPorZero_Junit5() {
		System.out.println(++contador);
		ArithmeticException exception = assertThrows(ArithmeticException.class, () -> {
			float resultado = 10 / 0;
		});
		assertEquals("/ by zero", exception.getMessage());
	}

	@ParameterizedTest
	@CsvSource(value = {
		" 6;  2;  3",
		" 6; -2; -3",
		"10;  3;  3.3333332538604736",
		" 0;  2;  0"
	}, delimiter = ';')
	public void deveDividirCorretamente(int num, int den, float res){
		System.out.println(++contador);
		float resultado = calc.dividir(num, den);
		assertEquals(res,resultado);
	}
}
