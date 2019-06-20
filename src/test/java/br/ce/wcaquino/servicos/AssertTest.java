package br.ce.wcaquino.servicos;

import org.junit.Assert;
import org.junit.Test;

import br.ce.wcaquino.entidades.Usuario;

public class AssertTest {
	@Test
	public void teste() {
		Assert.assertTrue(true);
		Assert.assertFalse(false);
		Assert.assertEquals(1, 1);
		Assert.assertEquals(0.51, 0.51, 0.01);
		Assert.assertEquals(Math.PI, 3.14, 0.01);
		Assert.assertTrue("bola".equalsIgnoreCase("Bola"));
		Assert.assertTrue("bola".startsWith("bo"));
		
		Usuario u1 = new Usuario("Railson");
		Usuario u2 = new Usuario("Railson");
		Usuario u3 = new Usuario("Railson");
		u3 = u2;
		
		//compara refencia se n tiver equals no metodo, se tiver compara os atributos do equals 
		Assert.assertEquals(u1, u2);
		
		//comprada referencia
		Assert.assertSame(u2, u3);
		
		u3 = null;
		
		Assert.assertTrue(u3==null);
		Assert.assertNull(null);
		
	}
}
