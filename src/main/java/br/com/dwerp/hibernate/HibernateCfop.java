package br.com.dwerp.hibernate;

import java.io.Serializable;

import javax.enterprise.context.Dependent;

import br.com.dwerp.dao.DAOCfop;
import br.com.dwerp.entidade.Cfop;
import br.com.dwerp.hibernate.generico.DAOGenericoHibernate;

@Dependent
public class HibernateCfop extends DAOGenericoHibernate<Cfop> implements DAOCfop,Serializable {
	private static final long serialVersionUID = 1L;
	
	public HibernateCfop(){
		super(Cfop.class);
	}


}
