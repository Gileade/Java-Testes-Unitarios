package br.com.gile.leilao.dominio;

public class Lance {

	private Usuario usuario;
	private double valor;
	
	public Lance(Usuario usuario, double valor) {
		if (valor == 0) throw new IllegalArgumentException("O valor do lance não pode ser 0");
		if (valor < 0) throw new IllegalArgumentException("O valor do lance não pode ser negativo");
		this.usuario = usuario;
		this.valor = valor;
		
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public double getValor() {
		return valor;
	}
	
	
	
}
