package br.com.dwerp.hibernate;

import java.io.Serializable;

import javax.enterprise.context.Dependent;

import br.com.dwerp.dao.DAOCest;
import br.com.dwerp.entidade.Cest;
import br.com.dwerp.hibernate.generico.DAOGenericoHibernate;

@Dependent
public class HibernateCest extends DAOGenericoHibernate<Cest> implements DAOCest,Serializable {
	private static final long serialVersionUID = 1L;
	
	public HibernateCest(){
		super(Cest.class);
	}


}
