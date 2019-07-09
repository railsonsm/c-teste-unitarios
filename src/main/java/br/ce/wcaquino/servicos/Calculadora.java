package br.ce.wcaquino.servicos;

import br.ce.wcaquino.exception.NaoPodeDividirPorZeroExcpetion;

public class Calculadora {

	public int somar(int a, int b) {
		return a+b;
	}

	public int subtracao(int a, int b) {
		return a-b;
	}

	public int divide(int a, int b) throws NaoPodeDividirPorZeroExcpetion {
		if(b==0)
			throw new NaoPodeDividirPorZeroExcpetion();
		return a/b;
	}

}
