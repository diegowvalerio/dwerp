package br.com.dwerp.hibernate;

import java.io.Serializable;

import javax.enterprise.context.Dependent;

import br.com.dwerp.dao.DAOModulo;
import br.com.dwerp.entidade.Modulo;
import br.com.dwerp.hibernate.generico.DAOGenericoHibernate;

@Dependent
public class HibernateModulo extends DAOGenericoHibernate<Modulo> implements DAOModulo,Serializable {
	private static final long serialVersionUID = 1L;
	
	public HibernateModulo(){
		super(Modulo.class);
	}


}
