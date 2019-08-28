package br.com.gile.leilao.testes;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import br.com.gile.leilao.dominio.Lance;
import br.com.gile.leilao.dominio.Leilao;
import br.com.gile.leilao.dominio.Usuario;
import br.com.gile.leilao.servico.Avaliador;

public class TesteDoAvaliador {

	@Test
	public void deveEntenderLancesEmOrdemCrescente() {
		// Cenário: 3 lances em ordem crescente
		Usuario joao = new Usuario("João");
		Usuario jose = new Usuario("José");
		Usuario maria = new Usuario("Maria");

		Leilao leilao = new Leilao("Playstation 3 novo");

		leilao.propoe(new Lance(joao, 250.0));
		leilao.propoe(new Lance(jose, 300.0));
		leilao.propoe(new Lance(maria, 400.0));

		// executando a ação
		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);

		// comparando a saída com o esperado
		double maiorEsperado = 400;
		double menorEsperado = 250;

		assertEquals(maiorEsperado, leiloeiro.getMaiorLance(), 0.00001);// Deve imprimir 400
		assertEquals(menorEsperado, leiloeiro.getMenorLance(), 0.00001);// Deve imprimir 250
	}

	@Test
	public void deveCalcularAMedia() {
		// Cenário: 3 lances em ordem crescente
		Usuario joao = new Usuario("João");
		Usuario jose = new Usuario("José");
		Usuario maria = new Usuario("Maria");

		Leilao leilao = new Leilao("Playstation 3 novo");

		leilao.propoe(new Lance(joao, 300));
		leilao.propoe(new Lance(jose, 400.0));
		leilao.propoe(new Lance(maria, 500.0));

		// executando a ação
		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);

		assertEquals(400, leiloeiro.getMedia(), 0.00001);// Deve imprimir 250
	}

	@Test
	public void testaZeroDeMedia() {
		// Cenário: 3 lances em ordem crescente
		Usuario joao = new Usuario("João");

		Leilao leilao = new Leilao("Playstation 3 novo");

		leilao.propoe(new Lance(joao, 0));

		// executando a ação
		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);

		assertEquals(0, leiloeiro.getMedia(), 0.00001);// Deve imprimir 250

	}

	@Test
	public void deveEntenderApenasUmLance() {
		// Cenário: 3 lances em ordem crescente
		Usuario joao = new Usuario("João");

		Leilao leilao = new Leilao("Playstation 3 novo");

		leilao.propoe(new Lance(joao, 200));

		// executando a ação
		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);

		assertEquals(200, leiloeiro.getMaiorLance(), 0.00001);// Deve imprimir 200
		assertEquals(200, leiloeiro.getMenorLance(), 0.00001);// Deve imprimir 200
		assertEquals(200, leiloeiro.getMedia(), 0.00001);// Deve imprimir 200
	}

	@Test
	public void deveEntenderLancesEmOrdemAleatoria() {
		// Cenário: 3 lances em ordem crescente
		Usuario joao = new Usuario("João");
		Usuario jose = new Usuario("José");
		Usuario maria = new Usuario("Maria");

		Leilao leilao = new Leilao("Playstation 3 novo");

		leilao.propoe(new Lance(joao, 8000.0));
		leilao.propoe(new Lance(jose, 5000.0));
		leilao.propoe(new Lance(maria, 6800.0));
		leilao.propoe(new Lance(jose, 9000.0));
		leilao.propoe(new Lance(joao, 3200.0));

		// executando a ação
		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);

		assertEquals(9000, leiloeiro.getMaiorLance(), 0.00001);
		assertEquals(3200, leiloeiro.getMenorLance(), 0.00001);
	}

	@Test
	public void deveEntenderLancesEmOrdemDecrescente() {
		// Cenário: 3 lances em ordem crescente
		Usuario joao = new Usuario("João");
		Usuario jose = new Usuario("José");
		Usuario maria = new Usuario("Maria");

		Leilao leilao = new Leilao("Playstation 3 novo");

		leilao.propoe(new Lance(joao, 8000.0));
		leilao.propoe(new Lance(jose, 7000.0));
		leilao.propoe(new Lance(maria, 6000.0));

		// executando a ação
		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);

		assertEquals(8000, leiloeiro.getMaiorLance(), 0.00001);
		assertEquals(6000, leiloeiro.getMenorLance(), 0.00001);
	}
	
	@Test
	public void deveEncontrarOsTresMaioresLances() {
		// Cenário: 3 lances em ordem crescente
		Usuario joao = new Usuario("João");
		Usuario jose = new Usuario("José");
		Usuario maria = new Usuario("Maria");

		Leilao leilao = new Leilao("Playstation 3 novo");

		leilao.propoe(new Lance(joao, 8000.0));
		leilao.propoe(new Lance(jose, 7000.0));
		leilao.propoe(new Lance(maria, 6000.0));
		leilao.propoe(new Lance(joao, 7500.0));
		leilao.propoe(new Lance(jose, 4500.0));
		
		// executando a ação
		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);

		List<Lance> maiores = leiloeiro.getTresMaiores();
		
		assertEquals(3, maiores.size());
		assertEquals(8000, maiores.get(0).getValor(), 0.00001);
		assertEquals(7500, maiores.get(1).getValor(), 0.00001);
		assertEquals(7000, maiores.get(2).getValor(),0.00001);
	}

	@Test
	public void deveDevolverTodosLancesCasoNaoHajaNoMinimo3() {
		// Cenário: 3 lances em ordem crescente
		Usuario joao = new Usuario("João");
		Usuario maria = new Usuario("Maria");

		Leilao leilao = new Leilao("Playstation 3 novo");

		leilao.propoe(new Lance(joao, 8000.0));
		leilao.propoe(new Lance(maria, 6000.0));
		
		// executando a ação
		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);

		List<Lance> maiores = leiloeiro.getTresMaiores();
		
		assertEquals(2, maiores.size());
		assertEquals(8000, maiores.get(0).getValor(), 0.00001);
		assertEquals(6000, maiores.get(1).getValor(), 0.00001);
	}
	
	@Test
    public void deveDevolverListaVaziaCasoNaoHajaLances() {
        Leilao leilao = new Leilao("Playstation 3 Novo");

        Avaliador leiloeiro = new Avaliador();
        leiloeiro.avalia(leilao);

        List<Lance> maiores = leiloeiro.getTresMaiores();

        assertEquals(0, maiores.size());
    }
}
