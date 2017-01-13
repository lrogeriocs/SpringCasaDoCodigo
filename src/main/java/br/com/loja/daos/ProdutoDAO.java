package br.com.loja.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.loja.model.Produto;

@Repository
@Transactional
public class ProdutoDAO {
	
	@PersistenceContext
	private EntityManager manager;
	
	public void gravar(Produto produto){
		
		manager.persist(produto);
		
	}

	public List<Produto> listar() {
		// TODO Auto-generated method stub
		return manager.createQuery("Select p from Produto p", Produto.class).getResultList();
	}

}
