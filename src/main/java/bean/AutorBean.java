package br.com.caelum.livraria.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.caelum.livraria.dao.AutorDao;
import br.com.caelum.livraria.modelo.Autor;
import br.com.caelum.livraria.tx.Transacional;
import br.com.caelum.livraria.util.RedirectView;

@Named
@ViewScoped
public class AutorBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 900421934312027872L;
	
	@Inject
	private AutorDao dao;
	
	private Autor autor = new Autor();	
	private Integer autorId;
		
	public Integer getAutorId() {
		return autorId;
	}

	public void setAutorId(Integer autorId) {
		this.autorId = autorId;
	}

	public Autor getAutor() {
		return autor;
	}
	
	public void setAutor(Autor autor) {
		this.autor = autor;
	}

	public List<Autor> getAutores(){
		
		return dao.listaTodos();		
	}
	
	@Transacional
	public void remover(Autor autor){
		dao.remove(autor);		
	}
	
	public void carregar(Autor autor){
		this.autor = autor;
	}
	
	public void carregarAutorPelaId(){
		this.autor = dao.buscaPorId(autorId);
	}

	@Transacional
	public RedirectView gravar() {
		System.out.println("Gravando autor " + this.autor.getNome());
		
		if(this.autor.getId() == null){
			dao.adiciona(this.autor);
		}
		else{
			dao.atualiza(this.autor);
		}		
		
		this.autor = new Autor();
		
		return new RedirectView("livro");
	}
}
