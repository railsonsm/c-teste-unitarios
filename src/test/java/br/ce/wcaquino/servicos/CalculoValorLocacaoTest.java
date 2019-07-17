package br.ce.wcaquino.servicos;

import static br.ce.wcaquino.servicos.builders.FilmeBuilder.umFilme;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.exception.FilmeSemEstoqueExption;
import br.ce.wcaquino.exception.LocadoraException;


@RunWith(Parameterized.class)
public class CalculoValorLocacaoTest {
	
	@Parameter
	public List<Filme> filmes;
	
	@Parameter(value = 1)
	public Double valorLocacao;
	
	@Parameter(value = 2)
	public String cenario;
	
	private LocacaoService service;
	
	@Before
	public void init() {
		service = new LocacaoService();
	}
	
	@Parameters(name = "{2}")
	public static Collection<Object[]> getParametros(){
		return Arrays.asList(new Object[][]{
			{Arrays.asList(umFilme().agora(),
					umFilme().agora(),
					umFilme().agora()), 11.0, "3 Filmes: 25%"},
			{Arrays.asList(umFilme().agora()
					,umFilme().agora()
					,umFilme().agora()
					,umFilme().agora()), 13.0, "4 Filmes: 50%"},
			{Arrays.asList(umFilme().agora()
					,umFilme().agora()
					,umFilme().agora()
					,umFilme().agora()
					,umFilme().agora()), 14.0, "5 Filmes: 75%"},
			{Arrays.asList(umFilme().agora()
					,umFilme().agora()
					,umFilme().agora()
					,umFilme().agora()
					,umFilme().agora()
					,umFilme().agora()), 14.0, "6 Filmes: 100%"}
		});
	}
	
	@Test
	public void deveCalcularValorLocacaoConsiderandoDescontos() throws FilmeSemEstoqueExption, LocadoraException {
		// cenacao
		Usuario usuario = new Usuario("Usuario 1");

		// acao
		Locacao locacao = service.alugarFilme(usuario, filmes);

		//verificacao com paramentos
		assertThat(locacao.getValor(), is(valorLocacao));
	}
	
	
	@Test
	public void print() {
		System.out.println(valorLocacao);
	}
	
	
	
	
	
}
