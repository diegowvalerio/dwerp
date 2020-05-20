package br.com.dwerp.hibernate;

import java.io.Serializable;

import javax.enterprise.context.Dependent;

import br.com.dwerp.dao.DAOCidade;
import br.com.dwerp.entidade.Cidade;
import br.com.dwerp.hibernate.generico.DAOGenericoHibernate;


@Dependent
public class HibernateCidade extends DAOGenericoHibernate<Cidade> implements DAOCidade,Serializable {
	private static final long serialVersionUID = 1L;
	
	public HibernateCidade(){
		super(Cidade.class);
	}


}
