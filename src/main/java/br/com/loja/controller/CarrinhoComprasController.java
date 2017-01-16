package br.com.loja.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import br.com.loja.daos.ProdutoDAO;
import br.com.loja.model.CarrinhoCompras;
import br.com.loja.model.CarrinhoItem;
import br.com.loja.model.Produto;
import br.com.loja.model.TipoPreco;



@Controller
@RequestMapping("/carrinho")
@Scope(value= WebApplicationContext.SCOPE_REQUEST)
public class CarrinhoComprasController {

	@Autowired
	private ProdutoDAO dao;
	@Autowired
	private CarrinhoCompras carrinho;
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView itens(){
	    return new ModelAndView("/carrinho/itens");
	}
	
	@RequestMapping("/add")
	public ModelAndView add(Integer produtoId, TipoPreco tipoPreco){
		ModelAndView modelAndView = new ModelAndView("redirect:/carrinho");
		CarrinhoItem carrinhoItem = criaItem(produtoId, tipoPreco);
		carrinho.add(carrinhoItem);
		return modelAndView;
	}

	private CarrinhoItem criaItem(Integer produtoId, TipoPreco tipoPreco) {
		Produto produto = dao.find(produtoId); 
		CarrinhoItem carrinhoItem =new CarrinhoItem(produto, tipoPreco);
		return carrinhoItem;
	}
}
