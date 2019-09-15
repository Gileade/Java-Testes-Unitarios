package br.com.gile.leilao.testes;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.com.gile.leilao.bissexto.AnoBissexto;

public class BissextoTest {

	
	@Test
	public void deveRetornarAnoBissexto() {
		AnoBissexto anoBissexto = new AnoBissexto();

		assertEquals(true, anoBissexto.isAnoBissexto(2016));
		assertEquals(true, anoBissexto.isAnoBissexto(2012));
	}

	@Test
	public void naoDeveRetornarAnoBissexto() {
		AnoBissexto anoBissexto = new AnoBissexto();

		assertEquals(false, anoBissexto.isAnoBissexto(2015));
		assertEquals(false, anoBissexto.isAnoBissexto(2011));
	}
}
