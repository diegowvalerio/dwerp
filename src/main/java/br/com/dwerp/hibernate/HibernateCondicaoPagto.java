package br.com.dwerp.hibernate;

import java.io.Serializable;

import javax.enterprise.context.Dependent;

import br.com.dwerp.dao.DAOCondicaoPagto;
import br.com.dwerp.entidade.CondPgto;

@Dependent
public class HibernateCondicaoPagto extends DAOGenericoHibernate<CondPgto> implements DAOCondicaoPagto,Serializable {
	private static final long serialVersionUID = 1L;
	
	public HibernateCondicaoPagto(){
		super(CondPgto.class);
	}


}
