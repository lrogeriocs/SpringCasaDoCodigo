package br.com.loja.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.loja.daos.ProdutoDAO;
import br.com.loja.model.Produto;

@Controller
public class ProdutosController {
	
	/**
	 * A anotação @Autowired serve para que nós não nos preocupemos em criar 
	 * manualmente o ProdutoDAO no Controller O Spring fará isso automaticamente.
	 *  Mas para isso, o Spring precisa "conhecer" o ProdutoDAO. 
	 *  Em outras palavras dizemos que devemos definir que o ProdutoDAO será 
	 *  gerenciado pelo Spring. Para isso devemos marcar o ProdutoDAO com a anotação @Repository.
	 */
	@Autowired
	private ProdutoDAO dao;
	
	@RequestMapping("produtos/form")
	public String form(){
		return "produtos/form";
	}
	@RequestMapping("/produtos")
	public String gravar(Produto produto){
		dao.gravar(produto);
		System.out.println(produto);
		return "produtos/ok";
		
	}
}
