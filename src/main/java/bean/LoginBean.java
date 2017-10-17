package br.com.caelum.livraria.bean;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.caelum.livraria.dao.UsuarioDao;
import br.com.caelum.livraria.modelo.Usuario;

@Named
@ViewScoped
public class LoginBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6891623185316861600L;
	
	@Inject
	UsuarioDao usuarioDao;
	
	@Inject
	FacesContext contexto;
	
	Usuario usuario = new Usuario();

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public String efetuarLogin() {
		
		System.out.println("Fazendo login do usuario " + this.usuario.getEmail());
		
		boolean usuarioExiste = usuarioDao.existe(usuario);
		
		if(usuarioExiste){
			
			contexto.getExternalContext().getSessionMap().put("usuarioLogado", this.usuario);
			
			return "livro?faces-redirect=true";
		}
		
		contexto.addMessage(null, new FacesMessage("Login Inválido."));
		contexto.getExternalContext().getFlash().setKeepMessages(true);
		
		return "login?faces-redirect=true";
		
	}
	
	public String deslogar(){
		
		contexto.getExternalContext().getSessionMap().remove("usuarioLogado");
		
		return "login?faces-redirect=true";
	}
	

}
