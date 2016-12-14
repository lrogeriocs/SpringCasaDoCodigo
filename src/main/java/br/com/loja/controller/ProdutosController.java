package br.com.loja.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.loja.daos.ProdutoDAO;
import br.com.loja.model.Produto;

@Controller
public class ProdutosController {
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
