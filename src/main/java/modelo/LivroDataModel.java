package br.com.caelum.livraria.modelo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Spliterator;
import java.util.function.Consumer;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.com.caelum.livraria.dao.LivroDao;

@Named
@ViewScoped
public class LivroDataModel extends LazyDataModel<Livro> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7992443221840105119L;
	
	@Inject
	private LivroDao dao;
	
	@PostConstruct
	void init() {
		System.out.println("LDM: " + dao);
		super.setRowCount(dao.quantidadeDeElementos());
	}

	@Override
	public void forEach(Consumer<? super Livro> arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public Spliterator<Livro> spliterator() {
		// TODO Auto-generated method stub
		return null;
	}
		
	@Override
	public List<Livro> load(int inicio, int quantidade, String campoOrdenacao,
			SortOrder sentidoOrdenacao, Map<String, Object> filtros) {
		
		String titulo = (String) filtros.get("titulo");
		
		return dao.listaTodosPaginada(inicio, quantidade, "titulo", titulo);
	}
	
	

}
