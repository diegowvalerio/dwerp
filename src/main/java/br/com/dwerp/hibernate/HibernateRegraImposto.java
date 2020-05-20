package br.com.dwerp.hibernate;

import java.io.Serializable;

import javax.enterprise.context.Dependent;

import br.com.dwerp.dao.DAORegraImposto;
import br.com.dwerp.entidade.RegraImposto;
import br.com.dwerp.hibernate.generico.DAOGenericoHibernate;

@Dependent
public class HibernateRegraImposto extends DAOGenericoHibernate<RegraImposto> implements DAORegraImposto,Serializable {
	private static final long serialVersionUID = 1L;
	
	public HibernateRegraImposto(){
		super(RegraImposto.class);
	}


}
