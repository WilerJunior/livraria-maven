package br.com.caelum.livraria.bean;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.caelum.livraria.dao.AutorDao;
import br.com.caelum.livraria.dao.LivroDao;
import br.com.caelum.livraria.modelo.Autor;
import br.com.caelum.livraria.modelo.Livro;
import br.com.caelum.livraria.modelo.LivroDataModel;

@Named
@ViewScoped
public class LivroBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6891623185316861600L;
	
	private Livro livro = new Livro();
	private Integer autorId;	
	private Integer livroId;
	
	private List<String> generos = Arrays.asList("Romance", "Drama", "Ação");

	private List<Livro> livros;
	
	@Inject
	private LivroDataModel livroDataModel;

	@Inject
	private AutorDao autorDao;

	@Inject
	private LivroDao livroDao;	
	
	public LivroDataModel getLivroDataModel() {
		return livroDataModel;
	}

	public Integer getLivroId() {
		return livroId;
	}

	public void setLivroId(Integer livroId) {
		this.livroId = livroId;
	}

	public void setAutorId(Integer autorId) {
		this.autorId = autorId;
	}
	
	public Integer getAutorId() {
		return autorId;
	}	

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	public Livro getLivro() {
		return livro;
	}
	
	public List<Autor> getAutores (){
		return autorDao.listaTodos();
	}
	
	public List<Autor> getAutoresDoLivro(){
		return this.livro.getAutores();
	}
	
	public List<Livro> getLivros() {
		
		if(livros == null || livros.isEmpty()){			
			livros = livroDao.listaTodos();			
		}		
		
		return livros;
	}
	
	public void gravarAutor(){
		Autor autor = autorDao.buscaPorId(this.autorId);
		this.livro.adicionaAutor(autor);		
	}
	
	public void carregarLivroPelaId() {
		this.livro = livroDao.buscaPorId(this.livroId);
	}
	
	public String formAutor(){
		System.out.println("Chamando o formulário do Autor");
		return "autor?faces-redirect=true";
	}
	
	public void comecaComDigitoUm(FacesContext fc, UIComponent component, Object value) throws ValidatorException {
		String valor = value.toString();
		
		if(!valor.startsWith("1")){
			throw new ValidatorException(new FacesMessage("Deveria começar com 1"));
		}
	}
	
	public void carregar(Livro livro){
		this.livro = this.livroDao.buscaPorId(livro.getId());		
	}
	
	public void remover(Livro livro){
		System.out.println("Removendo autor");
		
		livroDao.remove(livro);
		
		this.livro = new Livro();
	}
	
	public void removerAutorDoLivro(Autor autor){
		this.livro.removeAutor(autor);
	}

	public void gravar() {
		System.out.println("Gravando livro " + this.livro.getTitulo());

		if (livro.getAutores().isEmpty()) {
			FacesContext.getCurrentInstance().addMessage("autor", new FacesMessage("Livro deve ter pelo menos um Autor."));
			
			return;
		}
		
		if(livro.getId() == null){
			livroDao.adiciona(this.livro);			
		} else {
			livroDao.atualiza(livro);
		}
		
		this.livros = livroDao.listaTodos();
		
		this.livro = new Livro();
	}
	
	public boolean precoEhMenor(Object valorColuna, Object filtroDigitado, Locale locale){
		
		String textoDigitado = (filtroDigitado == null) ? null : filtroDigitado.toString().trim();
		
		if(textoDigitado == null || textoDigitado.equals("")){
			return true;
		}
		
		if(valorColuna == null){
			return false;
		}
		
		try {
			Double precoDigitado = Double.valueOf(textoDigitado);
			Double precoColuna = (Double) valorColuna;
			
			return precoColuna.compareTo(precoDigitado) < 0;
			
		} catch (NumberFormatException e) {
			
			return false;			
		}	
		
	}

	public List<String> getGeneros() {
		return generos;
	}
}
