package br.com.dwerp.hibernate;

import java.io.Serializable;

import javax.enterprise.context.Dependent;

import br.com.dwerp.dao.DAOEstrutura;
import br.com.dwerp.entidade.Estrutura;
import br.com.dwerp.hibernate.generico.DAOGenericoHibernate;

@Dependent
public class HibernateEstrutura extends DAOGenericoHibernate<Estrutura> implements DAOEstrutura,Serializable {
	private static final long serialVersionUID = 1L;
	
	public HibernateEstrutura(){
		super(Estrutura.class);
	}


}
