package br.com.gile.leilao.servico;

import org.junit.Assert;
import org.junit.Test;

import br.com.gile.leilao.dominio.Lance;
import br.com.gile.leilao.dominio.Leilao;
import br.com.gile.leilao.dominio.Usuario;

public class TesteDoAvaliador {

	@Test
	public void deveEntenderLancesEmOrdemCrescente() {
		//Cenário: 3 lances em ordem crescente
		Usuario joao = new Usuario("João");
		Usuario jose = new Usuario("José");
		Usuario maria = new Usuario("Maria");
		
		Leilao leilao = new Leilao("Playstation 3 novo");
		
		leilao.propoe(new Lance(joao,250.0));
		leilao.propoe(new Lance(jose,300.0));
		leilao.propoe(new Lance(maria,400.0));
		
		//executando a ação
		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);
		
		//comparando a saída com o esperado
		double maiorEsperado = 400;
		double menorEsperado = 250;
		
		Assert.assertEquals(maiorEsperado, leiloeiro.getMaiorLance(),0.00001);//Deve imprimir 400
		Assert.assertEquals(menorEsperado, leiloeiro.getMenorLance(),0.00001);//Deve imprimir 250
	}

}
