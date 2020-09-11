package br.com.dwerp.hibernate;

import java.io.Serializable;

import javax.enterprise.context.Dependent;

import br.com.dwerp.dao.DAOUsuarioModulo;
import br.com.dwerp.entidade.UsuarioModulo;
import br.com.dwerp.hibernate.generico.DAOGenericoHibernate;

@Dependent
public class HibernateUsuarioModulo extends DAOGenericoHibernate<UsuarioModulo> implements DAOUsuarioModulo,Serializable {
	private static final long serialVersionUID = 1L;
	
	public HibernateUsuarioModulo(){
		super(UsuarioModulo.class);
	}


}
