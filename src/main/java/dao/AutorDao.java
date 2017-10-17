package br.com.caelum.livraria.dao;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.caelum.livraria.log.Log;
import br.com.caelum.livraria.modelo.Autor;

public class AutorDao implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2191062213140569510L;

	@Inject
	EntityManager em;
	
	private DAO<Autor> dao;
	
	@PostConstruct
	void init() {
		dao = new DAO<Autor>(this.em, Autor.class);
	}
	
	@Log
	public void adiciona(Autor t) {
		dao.adiciona(t);
	}

	@Log
	public void atualiza(Autor t) {
		dao.atualiza(t);
	}

	@Log
	public Autor buscaPorId(Integer id) {
		return dao.buscaPorId(id);
	}

	@Log
	public int contaTodos() {
		return dao.contaTodos();
	}

	public boolean equals(Object arg0) {
		return dao.equals(arg0);
	}

	@Log
	public void remove(Autor t) {
		dao.remove(t);
	}

	@Log
	public List<Autor> listaTodos() {
		return dao.listaTodos();
	}

	@Log
	public List<Autor> listaTodosPaginada(int firstResult, int maxResults,
			String coluna, String valor) {
		return dao.listaTodosPaginada(firstResult, maxResults, coluna, valor);
	}

	@Log
	public int quantidadeDeElementos() {
		return dao.quantidadeDeElementos();
	}

}
