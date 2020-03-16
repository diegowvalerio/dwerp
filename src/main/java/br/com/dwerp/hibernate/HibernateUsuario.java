package br.com.dwerp.hibernate;

import java.io.Serializable;

import javax.enterprise.context.Dependent;

import br.com.dwerp.dao.DAOUsuario;
import br.com.dwerp.entidade.Usuario;

@Dependent
public class HibernateUsuario extends DAOGenericoHibernate<Usuario> implements DAOUsuario,Serializable {
	private static final long serialVersionUID = 1L;
	
	public HibernateUsuario(){
		super(Usuario.class);
	}

}
