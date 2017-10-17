package br.com.caelum.livraria.dao;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.caelum.livraria.log.Log;
import br.com.caelum.livraria.modelo.Livro;

public class LivroDao implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 727611910839150859L;

	@Inject
	EntityManager em;
	
	private DAO<Livro> livroDao;
	
	@PostConstruct
	void init() {
		livroDao = new DAO<Livro>(em, Livro.class);
	}

	@Log
	public void adiciona(Livro t) {
		livroDao.adiciona(t);
	}

	@Log
	public void atualiza(Livro t) {
		livroDao.atualiza(t);
	}

	@Log
	public Livro buscaPorId(Integer id) {
		return livroDao.buscaPorId(id);
	}

	@Log
	public int contaTodos() {
		return livroDao.contaTodos();
	}

	public boolean equals(Object arg0) {
		return livroDao.equals(arg0);
	}

	@Log
	public void remove(Livro t) {
		livroDao.remove(t);
	}

	@Log
	public List<Livro> listaTodos() {
		return livroDao.listaTodos();
	}

	@Log
	public List<Livro> listaTodosPaginada(int firstResult, int maxResults,
			String coluna, String valor) {
		return livroDao.listaTodosPaginada(firstResult, maxResults, coluna,
				valor);
	}

	@Log
	public int quantidadeDeElementos() {
		return livroDao.quantidadeDeElementos();
	}

}
