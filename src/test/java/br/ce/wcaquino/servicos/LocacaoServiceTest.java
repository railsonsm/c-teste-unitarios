package br.ce.wcaquino.servicos;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

import java.util.Date;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.exception.FilmeSemEstoqueExption;
import br.ce.wcaquino.exception.LocadoraException;
import br.ce.wcaquino.utils.DataUtils;

public class LocacaoServiceTest {
	
	private  LocacaoService service = new LocacaoService();
	
	@Rule
	public ErrorCollector error = new ErrorCollector();

	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	//executa antes do teste
	@Before
	public void setup() {
		service = new LocacaoService();
	}	

	@After
	public void tearDown() {
		
	}
	
	//executa antes da classe do teste
	@BeforeClass
	public static void setupClass() {

	}	

	@AfterClass
	public static void tearDownClass() {
		
	}
	
	@Test
	public void testeLocacao() throws Exception {
		// cenario
		
		Usuario usuario = new Usuario("Railson");
		Filme filme = new Filme("Filme 1", 1, 5.0);

		// acao
		Locacao locacao = service.alugarFilme(usuario, filme);
		
		// verificao
		
		//checkThat checa todos os teste e não para no primeiro quando da erro como o assertThat	
		error.checkThat(locacao.getValor(), is(equalTo(5.0)));
		error.checkThat(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()), is(true));
		error.checkThat(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)),
				is(true));

	}

	//forma elegante
	@Test(expected = FilmeSemEstoqueExption.class)
	public void testeLocacao_filmeSemEstoque() throws Exception {
		// cenario
		
		Usuario usuario = new Usuario("Railson");
		Filme filme = new Filme("Filme 1", 0, 5.0);

		// acao
		service.alugarFilme(usuario, filme);
	}
	
	
	//forma robusta, permite um controle maior sobre o teste
	@Test
	public void restLocacaoUsuarioVazio() throws FilmeSemEstoqueExption {
		// cenario

		Filme filme = new Filme("Filme 1", 1, 5.0);

		// acao
		try {
			service.alugarFilme(null, filme);
			Assert.fail();
		} catch (LocadoraException e) {
			Assert.assertThat(e.getMessage(), is("Usuario vazio"));
		}
	}

	//melhor forma de tratar excpetion
	@Test
	public void testeLocacaoFilmeVazio() throws FilmeSemEstoqueExption, LocadoraException {
		// cenario
		Usuario usuario = new Usuario("Railson");
		
		exception.expect(LocadoraException.class);
		exception.expectMessage("Filme vazio");
		// acao
		service.alugarFilme(usuario, null);
		
		
	}

}
