package br.com.gile.leilao.testes;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.com.gile.leilao.builder.CriadorDeLeilao;
import br.com.gile.leilao.dominio.Lance;
import br.com.gile.leilao.dominio.Leilao;
import br.com.gile.leilao.dominio.Usuario;
import br.com.gile.leilao.servico.Avaliador;

public class LeilaoTest {

	@Test
	public void deveReceberUmLance() {
		Leilao leilao = new CriadorDeLeilao().para("Macbook Pro 15").constroi();
		assertEquals(0, leilao.getLances().size());

		leilao.propoe(new Lance(new Usuario("Steve Jobs"), 2000));

		assertEquals(1, leilao.getLances().size());
		assertEquals(2000.0, leilao.getLances().get(0).getValor(), 0.00001);
	}

	@Test
	public void deveReceberVariosLances() {
		Leilao leilao = new CriadorDeLeilao().para("Macbook Pro 15").lance(new Usuario("Steve Jobs"), 2000)
				.lance(new Usuario("Steve Wozniak"), 3000).constroi();

		assertEquals(2, leilao.getLances().size());
		assertEquals(2000.0, leilao.getLances().get(0).getValor(), 0.00001);
		assertEquals(3000.0, leilao.getLances().get(1).getValor(), 0.00001);

	}

	@Test
	public void naoDeveAceitarDoisLancesEmSequenciaDoMesmoUsuario() {
		Usuario steveJobs = new Usuario("Steve Jobs");
		Leilao leilao = new CriadorDeLeilao().para("Macbook Pro 15").lance(steveJobs, 2000.0).lance(steveJobs, 3000.0)
				.constroi();

		assertEquals(1, leilao.getLances().size());
		assertEquals(2000.0, leilao.getLances().get(0).getValor(), 0.00001);
	}

	public void naoDeveAceitarMaisDoQue5LancesDeUmMesmoUsuario() {
		Usuario steveJobs = new Usuario("Steve Jobs");
		Usuario billGates = new Usuario("Bill Gates");

		Leilao leilao = new CriadorDeLeilao().para("Macbook Pro 15").lance(steveJobs, 2000).lance(billGates, 3000)
				.lance(steveJobs, 4000).lance(billGates, 5000).lance(steveJobs, 6000).lance(billGates, 7000)
				.lance(steveJobs, 8000).lance(billGates, 9000).lance(steveJobs, 10000).lance(billGates, 11000)
				.lance(steveJobs, 12000).constroi();

		assertEquals(10, leilao.getLances().size());
		int ultimo = leilao.getLances().size() - 1;
		assertEquals(11000.0, leilao.getLances().get(ultimo).getValor(), 0.00001);
	}

}
