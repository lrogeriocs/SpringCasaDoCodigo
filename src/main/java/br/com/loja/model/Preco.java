package br.com.loja.model;

import java.math.BigDecimal;

import javax.persistence.Embeddable;

//para que o Spring possa relacionar e portar os elementos de preço para dentro desta coleção, 
//devemos marcar a classe Preco com uma a anotação Embeddable:
@Embeddable
public class Preco {

	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public TipoPreco getTipo() {
		return tipo;
	}
	public void setTipo(TipoPreco tipo) {
		this.tipo = tipo;
	}
	private BigDecimal valor;
	private TipoPreco tipo;
}
