package br.ce.wcaquino.servicos.mathers;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import br.ce.wcaquino.utils.DataUtils;

public class DiaSemanaMather extends TypeSafeMatcher<Date> {
	
	private Integer diaSemana;

	public DiaSemanaMather(Integer diaSemana) {
		this.diaSemana = diaSemana;
	}
	
	public void describeTo(Description description) {
		Calendar data= Calendar.getInstance();
		data.set(Calendar.DAY_OF_WEEK, diaSemana);
		String dataExtenso = data.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, new Locale("pt", "BR"));
		description.appendText(dataExtenso);
	}

	@Override
	protected boolean matchesSafely(Date data) {
		return DataUtils.verificarDiaSemana(data, diaSemana);
	}

}
