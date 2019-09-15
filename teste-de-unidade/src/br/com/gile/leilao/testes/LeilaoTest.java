package br.com.gile.leilao.testes;


import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.com.gile.leilao.dominio.Lance;
import br.com.gile.leilao.dominio.Leilao;
import br.com.gile.leilao.dominio.Usuario;
import br.com.gile.leilao.servico.Avaliador;

public class LeilaoTest {
	
	@Test
	public void deveReceberUmLance() {
		Leilao leilao = new Leilao("Macbook Pro 15");
		
		leilao.propoe(new Lance(new Usuario("Steve Jobs"), 2000));
		
		assertEquals(2000, leilao.getLances().get(0).getValor(), 0.00001);
	}
	
	@Test
	public void deveReceberVariosLances() {
		Leilao leilao = new Leilao("Macbook Pro 15");
		
		leilao.propoe(new Lance(new Usuario("Gile"), 2000));
		leilao.propoe(new Lance(new Usuario("Steve"), 2500));
		leilao.propoe(new Lance(new Usuario("Wonder"), 3000));
		
		assertEquals(2000, leilao.getLances().get(0).getValor(), 0.00001);
		assertEquals(2500, leilao.getLances().get(1).getValor(), 0.00001);
		assertEquals(3000, leilao.getLances().get(2).getValor(), 0.00001);
	}
	
	@Test
	public void naoDeveAceitarDoisLancesEmSequenciaDoMesmoUsuario() {
		Usuario gile = new Usuario("Gile");
		
		Leilao leilao = new Leilao("Playstation 4");
		
		leilao.propoe(new Lance(gile, 2000));
		leilao.propoe(new Lance(gile, 3000));
		
		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);

		assertEquals(1, leilao.getLances().size());
		assertEquals(2000, leilao.getLances().get(0).getValor(),0.00001);
	}
	
	@Test
	public void naoDeveAceitarMaisDoQue5LancesDeUmMesmoUsuario() {
		Usuario gile = new Usuario("Gile");
		Usuario steve = new Usuario("Steve");
		
		Leilao leilao = new Leilao("Playstation 4");
		leilao.propoe(new Lance(gile, 1000));
		leilao.propoe(new Lance(steve, 2000));
		leilao.propoe(new Lance(gile, 3000));
		leilao.propoe(new Lance(steve, 4000));
		leilao.propoe(new Lance(gile, 5000));
		leilao.propoe(new Lance(steve, 6000));
		leilao.propoe(new Lance(gile, 7000));
		leilao.propoe(new Lance(steve, 8000));
		leilao.propoe(new Lance(gile, 9000));
		leilao.propoe(new Lance(steve, 10000));
		
		//deve ser ignorado
		leilao.propoe(new Lance(gile, 11000));
		
		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);
		
		assertEquals(10, leilao.getLances().size());
		int ultimo = leilao.getLances().size()-1;
		assertEquals(10000, leilao.getLances().get(ultimo).getValor(),0.00001);
	}
}
