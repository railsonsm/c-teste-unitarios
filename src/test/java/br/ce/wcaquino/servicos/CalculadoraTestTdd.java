package br.ce.wcaquino.servicos;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.ce.wcaquino.exception.NaoPodeDividirPorZeroExcpetion;

public class CalculadoraTestTdd {
	
	private Calculadora calc;
	
	@Before
	public void init() {
		calc = new Calculadora();
	}

	@Test
	public void deveSomarDoisValores() {
		// cenario
		int a = 5;
		int b = 3;

		Calculadora calc = new Calculadora();

		// acao
		int result = calc.somar(a, b);

		// verificacao
		Assert.assertEquals(8, result);
	}

	@Test
	public void deveSubtrairDoisValores() {
		// cenario
		int a = 8;
		int b = 5;

		// acao
		int result = calc.subtracao(a, b);

		// verificacao
		Assert.assertEquals(3, result);
	}

	@Test
	public void deveDivirDoisValores() throws NaoPodeDividirPorZeroExcpetion {
		// cenario
		int a = 6;
		int b = 3;

		// acao
		int result = calc.divide(a, b);

		// verificacao
		Assert.assertEquals(2, result);
	}

	@Test(expected = NaoPodeDividirPorZeroExcpetion.class)
	public void deveLancarExcpetionAoDividirPorZero() throws NaoPodeDividirPorZeroExcpetion {
		// cenario
		int a = 6;
		int b = 0;

		// acao

		int result = calc.divide(a, b);

	}

}
