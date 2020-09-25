package br.com.dwerp.hibernate;

import java.io.Serializable;

import javax.enterprise.context.Dependent;

import br.com.dwerp.dao.DAOFechamento;
import br.com.dwerp.entidade.Fechamento;
import br.com.dwerp.hibernate.generico.DAOGenericoHibernate;

@Dependent
public class HibernateFechamento extends DAOGenericoHibernate<Fechamento> implements DAOFechamento,Serializable {
	private static final long serialVersionUID = 1L;
	
	public HibernateFechamento(){
		super(Fechamento.class);
	}


}
