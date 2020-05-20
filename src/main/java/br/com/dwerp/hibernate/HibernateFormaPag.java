package br.com.dwerp.hibernate;

import java.io.Serializable;

import javax.enterprise.context.Dependent;

import br.com.dwerp.dao.DAOFormaPag;
import br.com.dwerp.entidade.FormaPag;
import br.com.dwerp.hibernate.generico.DAOGenericoHibernate;

@Dependent
public class HibernateFormaPag extends DAOGenericoHibernate<FormaPag> implements DAOFormaPag,Serializable {
	private static final long serialVersionUID = 1L;
	
	public HibernateFormaPag(){
		super(FormaPag.class);
	}


}
