package br.com.loja.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.loja.daos.ProdutoDAO;
import br.com.loja.infra.FileSaver;
import br.com.loja.model.Produto;
import br.com.loja.model.TipoPreco;
import br.com.loja.validation.ProdutoValidation;

@Controller
@RequestMapping("/produtos")
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
	
	@Autowired
	private FileSaver fileSaver;
	
	@InitBinder
	public void initBinder(WebDataBinder binder){
		binder.addValidators(new ProdutoValidation());
	}
	
	@RequestMapping("/form")
	public ModelAndView form(Produto produto){
		 ModelAndView modelAndView = new ModelAndView("produtos/form");
        modelAndView.addObject("tipos", TipoPreco.values());

      
		return modelAndView;
	}
	@RequestMapping(method=RequestMethod.POST)
	public ModelAndView gravar(MultipartFile sumario, @Valid Produto produto, BindingResult result, RedirectAttributes redirectAttributes){
		
		
		System.out.println(sumario.getOriginalFilename());
		if(result.hasErrors()){
			return form(produto);
		}
		String path = fileSaver.write("arquivos-sumario", sumario);
		produto.setSumarioPath(path);
		dao.gravar(produto);
	
		/**
		 * O Flash Scoped é um escopo rápido. Os objetos que adicionamos nele através 
		 * do método addFlashAttribute ficam vivos de um request para outro, enquanto 
		 *o navegador executa um redirect (usando o código de status 302).
		 */
		
		redirectAttributes.addFlashAttribute("sucesso", "Produto Cadastrado Com sucesso!");
	 
		/* devemos sempre fazer um redirect após o formulário enviar um POST Pois ao fazer F5 o navegador repete o ultimo request que ele realizou, e quando esse resquest é um POST, 
		todos os dados que foram enviados também são repetidos. Se você realizou um insert no banco de dados, esse insert será repetido. Ou mesmo se realizou alguma operações que envia e-mail, por exemplo, o e-mail será enviado duas vezes ao pressionar F5.
		Para evitar qualquer problema de dados reenviados, realizamos um redirect após um formulário com POST.*/
		
		return new ModelAndView("redirect:/produtos");
		
	}
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView listar(){
		
		ModelAndView modelAndView = new ModelAndView("produtos/listar");
		List<Produto> produtos = dao.listar();
		modelAndView.addObject("produtos", produtos);
		return modelAndView;
	}
	
	@RequestMapping("/detalhe/{id}")
    public ModelAndView detalhe(@PathVariable("id") Integer id){
        ModelAndView modelAndView = new ModelAndView("/produtos/detalhe");
        Produto produto = dao.find(id);
        modelAndView.addObject("produto", produto);
        return modelAndView;
    }
}
