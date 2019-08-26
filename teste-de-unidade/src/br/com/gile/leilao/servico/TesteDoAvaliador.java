package br.com.gile.leilao.servico;

import br.com.gile.leilao.dominio.Lance;
import br.com.gile.leilao.dominio.Leilao;
import br.com.gile.leilao.dominio.Usuario;

public class TesteDoAvaliador {

	public static void main(String[] args) {
		Usuario joao = new Usuario("João");
		Usuario jose = new Usuario("José");
		Usuario maria = new Usuario("Maria");
		
		Leilao leilao = new Leilao("Playstation 3 novo");
		
		leilao.propoe(new Lance(joao,300.0));
		leilao.propoe(new Lance(joao,400.0));
		leilao.propoe(new Lance(joao,250.0));
		
		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);
		
		System.out.println(leiloeiro.getMaiorLance());//Deve imprimir 400
	}

}
