package br.com.dwerp.hibernate;

import java.io.Serializable;

import javax.enterprise.context.Dependent;

import br.com.dwerp.dao.DAOCanal;
import br.com.dwerp.entidade.Canal;
import br.com.dwerp.hibernate.generico.DAOGenericoHibernate;

@Dependent
public class HibernateCanal extends DAOGenericoHibernate<Canal> implements DAOCanal,Serializable {
	private static final long serialVersionUID = 1L;
	
	public HibernateCanal(){
		super(Canal.class);
	}


}
