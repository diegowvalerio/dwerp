package br.com.dwerp.hibernate;

import java.io.Serializable;

import javax.enterprise.context.Dependent;

import br.com.dwerp.dao.DAOEstado;
import br.com.dwerp.entidade.Estado;


@Dependent
public class HibernateEstado extends DAOGenericoHibernate<Estado> implements DAOEstado,Serializable {
	private static final long serialVersionUID = 1L;
	
	public HibernateEstado(){
		super(Estado.class);
	}

}
