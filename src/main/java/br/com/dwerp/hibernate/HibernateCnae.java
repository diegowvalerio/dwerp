package br.com.dwerp.hibernate;

import java.io.Serializable;

import javax.enterprise.context.Dependent;

import br.com.dwerp.dao.DAOCnae;
import br.com.dwerp.entidade.Cnae;
import br.com.dwerp.hibernate.generico.DAOGenericoHibernate;

@Dependent
public class HibernateCnae extends DAOGenericoHibernate<Cnae> implements DAOCnae,Serializable {
	private static final long serialVersionUID = 1L;
	
	public HibernateCnae(){
		super(Cnae.class);
	}


}
