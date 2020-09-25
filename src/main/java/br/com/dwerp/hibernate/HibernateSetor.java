package br.com.dwerp.hibernate;

import java.io.Serializable;

import javax.enterprise.context.Dependent;

import br.com.dwerp.dao.DAOSetor;
import br.com.dwerp.entidade.Setor;
import br.com.dwerp.hibernate.generico.DAOGenericoHibernate;

@Dependent
public class HibernateSetor extends DAOGenericoHibernate<Setor> implements DAOSetor,Serializable {
	private static final long serialVersionUID = 1L;
	
	public HibernateSetor(){
		super(Setor.class);
	}


}
