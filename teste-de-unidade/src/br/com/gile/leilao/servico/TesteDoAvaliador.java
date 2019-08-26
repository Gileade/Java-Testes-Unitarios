package br.com.gile.leilao.servico;

import br.com.gile.leilao.dominio.Lance;
import br.com.gile.leilao.dominio.Leilao;
import br.com.gile.leilao.dominio.Usuario;

public class TesteDoAvaliador {

	public static void main(String[] args) {
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
		
		//exibindo a saída
		System.out.println(leiloeiro.getMaiorLance());//Deve imprimir 400
		System.out.println(leiloeiro.getMenorLance());//Deve imprimir 250
	}

}
