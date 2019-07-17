package br.ce.wcaquino.servicos;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Assume;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;
import org.junit.runners.MethodSorters;

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
	public void deveAlugarFilme() throws Exception {
		// cenario
		
		Usuario usuario = new Usuario("Railson");
		Filme filme = new Filme("Filme 1", 1, 5.0);

		// acao
		Locacao locacao = service.alugarFilme(usuario, Arrays.asList(filme));
		
		// verificao	
		//checkThat checa todos os teste e não para no primeiro quando da erro como o assertThat	
		error.checkThat(locacao.getValor(), is(equalTo(5.0)));
		error.checkThat(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()), is(true));
		error.checkThat(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)),
				is(true));

	}

	//forma elegante
	@Test(expected = FilmeSemEstoqueExption.class)
	public void deveLancaExcpetionAoAlugarFilmeSemEstoque() throws Exception {
		// cenario
		
		Usuario usuario = new Usuario("Railson");
		Filme filme = new Filme("Filme 1", 0, 5.0);

		// acao
		service.alugarFilme(usuario, Arrays.asList(filme));
	}
	
	
	//forma robusta, permite um controle maior sobre o teste
	@Test
	public void naoDeveAlugarFilmeSemUsuario() throws FilmeSemEstoqueExption {
		// cenario
		Filme filme = new Filme("Filme 1", 1, 5.0);

		// acao
		try {
			service.alugarFilme(null, Arrays.asList(filme));
			Assert.fail();
		} catch (LocadoraException e) {
			Assert.assertThat(e.getMessage(), is("Usuario vazio"));
		}
	}

	//melhor forma de tratar excpetion
	@Test
	public void naoDeveAlugarFilmeSemFilme() throws FilmeSemEstoqueExption, LocadoraException {
		// cenario
		Usuario usuario = new Usuario("Railson");
		
		exception.expect(LocadoraException.class);
		exception.expectMessage("Filme vazio");
		// acao
		service.alugarFilme(usuario, null);
		
	}
	
	@Test
	public void devePagar75PorCentroNoFilme3() throws FilmeSemEstoqueExption, LocadoraException {
		//cenacao
		Usuario usuario = new Usuario("Usuario 1");
		List<Filme> filmes = Arrays.asList(new Filme("Filme 1", 2, 4.0),new Filme("Filme 2", 2, 4.0),new Filme("Filme 3", 2, 4.0));
	
		//acao
		Locacao locacao = service.alugarFilme(usuario, filmes);
		
		//verificacao
		//4(f1)+(4f2)+(3f3 com desconto 75) = 11
		assertThat(locacao.getValor(), is(11.0));
	}
	
	
	@Test
	public void devePagar50PorCentroNoFilme4() throws FilmeSemEstoqueExption, LocadoraException {
		//cenacao
		Usuario usuario = new Usuario("Usuario 1");
		List<Filme> filmes = Arrays.asList(new Filme("Filme 1", 2, 4.0)
				,new Filme("Filme 2", 2, 4.0)
				,new Filme("Filme 3", 2, 4.0)
				,new Filme("Filme 4", 2, 4.0));
	
		//acao
		Locacao locacao = service.alugarFilme(usuario, filmes);
		
		//verificacao
		//(f1=4)+(f2=4)+(f3=3 com desconto 75)+(f4=2 com desconto de 50) = 13
		assertThat(locacao.getValor(), is(13.0));
	}
	
	@Test
	public void devePagar25PorCentroNoFilme5() throws FilmeSemEstoqueExption, LocadoraException {
		//cenacao
		Usuario usuario = new Usuario("Usuario 1");
		List<Filme> filmes = Arrays.asList(new Filme("Filme 1", 2, 4.0)
				,new Filme("Filme 2", 2, 4.0)
				,new Filme("Filme 3", 2, 4.0)
				,new Filme("Filme 4", 2, 4.0)
				,new Filme("Filme 5", 2, 4.0));
	
		//acao
		Locacao locacao = service.alugarFilme(usuario, filmes);
		
		//verificacao
		//(f1=4)+(f2=4)+(f3=3 com desconto 75)+(f4=2 com desconto de 50)+(f5=1 com desconto de 25) = 14
		assertThat(locacao.getValor(), is(14.0));
	}
	
	@Test
	public void devePagar0NoFilme6() throws FilmeSemEstoqueExption, LocadoraException {
		//cenacao
		Usuario usuario = new Usuario("Usuario 1");
		List<Filme> filmes = Arrays.asList(new Filme("Filme 1", 2, 4.0)
				,new Filme("Filme 2", 2, 4.0)
				,new Filme("Filme 3", 2, 4.0)
				,new Filme("Filme 4", 2, 4.0)
				,new Filme("Filme 5", 2, 4.0)
				,new Filme("Filme 6", 2, 4.0));
	
		//acao
		Locacao locacao = service.alugarFilme(usuario, filmes);
		
		//verificacao
		//(f1=4)+(f2=4)+(f3=3 com desconto 75)+(f4=2 com desconto de 50)+(f5=1 com desconto de 25) = 14
		assertThat(locacao.getValor(), is(14.0));
	}
	
	@Test
	//@Ignore nao executa o teste, mas aparece na runs q ele estar skipped
	public void naoDeveDevolverFilmeNoDomingo() throws FilmeSemEstoqueExption, LocadoraException {
		
		//executa o teste de acordo com o assume no caso no domingo
		//Assumebasically significa "não execute este teste se estas condições não se aplicarem"
		Assume.assumeTrue(DataUtils.verificarDiaSemana(new Date(), Calendar.MONDAY));
		
		//cenario
		Usuario usuario = new Usuario();
		List<Filme>filmes = Arrays.asList(new Filme("Filme 1", 1, 5.0));
		
		//acao
		Locacao locacao = service.alugarFilme(usuario, filmes);
		
		//verificacao
		Boolean ehDomingo = DataUtils.verificarDiaSemana(locacao.getDataRetorno(), Calendar.MONDAY);
		Assert.assertTrue(ehDomingo);
	}
	
	

}
