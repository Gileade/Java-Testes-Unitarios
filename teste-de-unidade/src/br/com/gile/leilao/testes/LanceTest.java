package br.com.gile.leilao.testes;

import org.junit.Before;
import org.junit.Test;

import br.com.gile.leilao.builder.CriadorDeLeilao;
import br.com.gile.leilao.dominio.Leilao;
import br.com.gile.leilao.dominio.Usuario;
import br.com.gile.leilao.servico.Avaliador;

public class LanceTest {
	
	private Avaliador leiloeiro;
	private Usuario joao;
	
	@Before
	public void setUp() {
		this.leiloeiro = new Avaliador();
		
		this.joao = new Usuario("João");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void naoDeveAceitarUmLanceZerado() {
	    Leilao leilao = new CriadorDeLeilao()
	        .para("Playstation 4 Novo")
			.lance(joao,0)
			.constroi();

	    leiloeiro.avalia(leilao);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void naoDeveAceitarUmLanceNegativo() {
	    Leilao leilao = new CriadorDeLeilao()
	        .para("Playstation 4 Novo")
			.lance(joao,-40)
			.constroi();

	    leiloeiro.avalia(leilao);
	}

}
