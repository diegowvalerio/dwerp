package br.com.dwerp.hibernate;

import java.io.Serializable;

import javax.enterprise.context.Dependent;

import br.com.dwerp.dao.DAOUnidade;
import br.com.dwerp.entidade.Unidade;
import br.com.dwerp.hibernate.generico.DAOGenericoHibernate;

@Dependent
public class HibernateUnidade extends DAOGenericoHibernate<Unidade> implements DAOUnidade,Serializable {
	private static final long serialVersionUID = 1L;
	
	public HibernateUnidade(){
		super(Unidade.class);
	}


}
