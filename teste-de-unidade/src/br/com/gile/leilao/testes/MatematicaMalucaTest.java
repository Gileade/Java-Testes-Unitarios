package br.com.gile.leilao.testes;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import br.com.gile.leilao.matematica.MatematicaMaluca;

public class MatematicaMalucaTest {

	@Test
	public void deveRetornarNumeroMaioresQue30() {
		MatematicaMaluca mm = new MatematicaMaluca();
		assertEquals(50*4, mm.contaMaluca(50));
	}
	
	@Test
	public void deveRetornarNumeroMaiorQue10EMenorQue30() {
		MatematicaMaluca mm = new MatematicaMaluca();
		assertEquals(15 * 3, mm.contaMaluca(15));
	}
	
	@Test
    public void deveMultiplicarNumerosMenoresQue10() {
        MatematicaMaluca matematica = new MatematicaMaluca();
        assertEquals(5*2, matematica.contaMaluca(5));
    }
	
}
