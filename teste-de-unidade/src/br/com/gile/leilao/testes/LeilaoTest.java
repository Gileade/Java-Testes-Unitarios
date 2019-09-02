package br.com.gile.leilao.testes;


import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.com.gile.leilao.dominio.Lance;
import br.com.gile.leilao.dominio.Leilao;
import br.com.gile.leilao.dominio.Usuario;
import br.com.gile.leilao.servico.Avaliador;

public class LeilaoTest {

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
}
