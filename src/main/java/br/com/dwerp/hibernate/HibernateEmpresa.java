package br.com.dwerp.hibernate;

import java.io.Serializable;

import javax.enterprise.context.Dependent;

import br.com.dwerp.dao.DAOEmpresa;
import br.com.dwerp.entidade.Empresa;
import br.com.dwerp.hibernate.generico.DAOGenericoHibernate;

@Dependent
public class HibernateEmpresa extends DAOGenericoHibernate<Empresa> implements DAOEmpresa,Serializable {
	private static final long serialVersionUID = 1L;
	
	public HibernateEmpresa(){
		super(Empresa.class);
	}


}
