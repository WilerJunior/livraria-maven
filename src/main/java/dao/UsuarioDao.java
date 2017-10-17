package br.com.caelum.livraria.dao;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.caelum.livraria.modelo.Usuario;

@SuppressWarnings("serial")
@Named
@ViewScoped
public class UsuarioDao implements Serializable {
	
	@Inject
	EntityManager em;
	
	public boolean existe(Usuario usuario){
		
		TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u WHERE "
				+ " u.email = :pEmail AND u.senha = :pSenha", Usuario.class);
		
		query.setParameter("pEmail", usuario.getEmail());
		query.setParameter("pSenha", usuario.getSenha());
		
		try {
			Usuario resultado = query.getSingleResult();
		} catch (NoResultException ex) {
			return false;
		}	
		
		return true;
	}

}
