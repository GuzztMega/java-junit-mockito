package br.com.guzzmega;

import org.springframework.stereotype.Component;

@Component
public class Calculadora {

	public int soma(int a, int b) {
		return a + b;
	}
	
	public float dividir(int num, int den) {
		return (float) num / den;
	}
}
