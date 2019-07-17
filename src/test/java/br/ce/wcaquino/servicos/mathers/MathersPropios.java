package br.ce.wcaquino.servicos.mathers;

import java.util.Calendar;

public class MathersPropios {
	public static DiaSemanaMather caiEm(Integer diaSemana) {
		return new DiaSemanaMather(diaSemana);
	}
	
	public static DiaSemanaMather caiNumaSegunda() {
		return new DiaSemanaMather(Calendar.MONDAY);
	}
	
	public static DataDiferencaDiasMatcher ehHojeComDiferencaDias(Integer qtdDias) {
		return new DataDiferencaDiasMatcher(qtdDias);
	}
	
	public static DataDiferencaDiasMatcher ehHoje() {
		return new DataDiferencaDiasMatcher(0);
	}
}
