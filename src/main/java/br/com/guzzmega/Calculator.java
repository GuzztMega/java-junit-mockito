package br.com.guzzmega;

import org.springframework.stereotype.Component;

@Component
public class Calculator {

	public int sum(int a, int b) {
		return a + b;
	}
	
	public float divide(int num, int den) {
		return (float) num / den;
	}
}
