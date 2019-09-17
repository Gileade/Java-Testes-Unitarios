package br.com.gile.leilao.builder;

import br.com.gile.leilao.dominio.Lance;
import br.com.gile.leilao.dominio.Leilao;
import br.com.gile.leilao.dominio.Usuario;

public class CriadorDeLeilao {

	private Leilao leilao;

	public CriadorDeLeilao para(String descricao) {
		this.leilao = new Leilao(descricao);
		return this;
	}

	public CriadorDeLeilao lance(Usuario usuario, double valor) {
		leilao.propoe(new Lance(usuario,valor));
		return this;
	}

	public Leilao constroi() {
		return leilao;
	}

}
