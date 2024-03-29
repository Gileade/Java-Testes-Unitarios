package br.com.gile.leilao.testes;

import static org.junit.Assert.assertEquals;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.com.gile.leilao.builder.CriadorDeLeilao;
import br.com.gile.leilao.dominio.Lance;
import br.com.gile.leilao.dominio.Leilao;
import br.com.gile.leilao.dominio.Usuario;
import br.com.gile.leilao.servico.Avaliador;

public class AvaliadorTest {

	private Avaliador leiloeiro;
	private Usuario jose;
	private Usuario maria;
	private Usuario joao;

	@Before
	public void setUp() {
		this.leiloeiro = new Avaliador();
		
		this.joao = new Usuario("João");
		this.jose = new Usuario("José");
		this.maria = new Usuario("Maria");
	}
	
	@Test(expected=RuntimeException.class)
	public void naoDeveAvaliarLeiloesSemNenhumLanceDado() {
	    Leilao leilao = new CriadorDeLeilao()
	        .para("Playstation 3 Novo")
	        .constroi();

	    leiloeiro.avalia(leilao);
	}
	
	@Test
	public void deveEntenderLancesEmOrdemCrescente() {
		// Cenário: 3 lances em ordem crescente
		Leilao leilao = new CriadorDeLeilao()
				.para("Playstation3 novo")
				.lance(joao,250.0)
				.lance(jose,300.0)
				.lance(maria, 400.0)
				.constroi();
		// executando a ação
		leiloeiro.avalia(leilao);

		assertThat(leiloeiro.getMenorLance(), equalTo(250.0));
		assertThat(leiloeiro.getMaiorLance(), equalTo(400.0));
	}

	@Test
	public void deveCalcularAMedia() {
		// Cenário: 3 lances em ordem crescente
		Leilao leilao = new CriadorDeLeilao()
				.para("Playstation3 novo")
				.lance(joao,300.0)
				.lance(jose,400.0)
				.lance(maria, 500.0)
				.constroi();
		
		// executando a ação
		leiloeiro.avalia(leilao);

		assertThat(leiloeiro.getMedia(), equalTo(400.0));
	}

	@Test(expected=IllegalArgumentException.class)
	public void testaZeroDeMedia() {
		// Cenário: 3 lances em ordem crescente
		Leilao leilao = new CriadorDeLeilao()
				.para("Playstation3 novo")
				.lance(joao,0)
				.constroi();
		
		// executando a ação
		leiloeiro.avalia(leilao);

		assertThat(leiloeiro.getMedia(),equalTo(0));
	}

	@Test
	public void deveEntenderApenasUmLance() {
		// Cenário: 3 lances em ordem crescente
		Leilao leilao = new CriadorDeLeilao()
				.para("Playstation3 novo")
				.lance(joao,200.0)
				.constroi();
		
		// executando a ação
		leiloeiro.avalia(leilao);

		assertThat(leiloeiro.getMaiorLance(),equalTo(200.0));
		assertThat(leiloeiro.getMenorLance(),equalTo(200.0));
		assertThat(leiloeiro.getMedia(),equalTo(200.0));
	}

	@Test
	public void deveEntenderLancesEmOrdemAleatoria() {
		// Cenário: 3 lances em ordem crescente
		Leilao leilao = new CriadorDeLeilao()
				.para("Playstation3 novo")
				.lance(joao,8000.0)
				.lance(jose,5000.0)
				.lance(maria, 6800.0)
				.lance(jose, 9000.0)
				.lance(joao, 3200.0)
				.constroi();

		// executando a ação
		leiloeiro.avalia(leilao);

		assertThat(leiloeiro.getMaiorLance(),equalTo(9000.0));
		assertThat(leiloeiro.getMenorLance(),equalTo(3200.0));
	}

	@Test
	public void deveEntenderLancesEmOrdemDecrescente() {
		// Cenário: 3 lances em ordem crescente
		Leilao leilao = new CriadorDeLeilao()
				.para("Playstation3 novo")
				.lance(joao,8000.0)
				.lance(jose,7000.0)
				.lance(maria, 6000.0)
				.constroi();

		// executando a ação
		leiloeiro.avalia(leilao);

		assertThat(leiloeiro.getMaiorLance(),equalTo(8000.0));
		assertThat(leiloeiro.getMenorLance(),equalTo(6000.0));
	}
	
	@Test
	public void deveEncontrarOsTresMaioresLances() {
		// Cenário: 3 lances em ordem crescente
		Leilao leilao = new CriadorDeLeilao()
				.para("Playstation3 novo")
				.lance(joao,8000.0)
				.lance(jose,7000.0)
				.lance(maria, 6000.0)
				.lance(joao,7500.0)
				.lance(jose,4500.0)
				.constroi();

		// executando a ação
		leiloeiro.avalia(leilao);

		List<Lance> maiores = leiloeiro.getTresMaiores();
		assertEquals(3, maiores.size());
		
		assertThat(maiores,hasItems(
					new Lance(joao,8000.0),
					new Lance(joao,7500.0),
					new Lance(jose,7000.0)
		));
	}

	@Test
	public void deveDevolverTodosLancesCasoNaoHajaNoMinimo3() {
		// Cenário: 3 lances em ordem crescente		
		Leilao leilao = new CriadorDeLeilao()
				.para("Playstation3 novo")
				.lance(joao,8000.0)
				.lance(maria, 6000.0)
				.constroi();

        leiloeiro.avalia(leilao);

        List<Lance> maiores = leiloeiro.getTresMaiores();
		
		assertEquals(2, maiores.size());
		
		assertThat(maiores,hasItems(
				new Lance(joao,8000.0),
				new Lance(maria,6000.0)
		));
	}
	
}
