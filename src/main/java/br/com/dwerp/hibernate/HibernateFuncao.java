package br.com.dwerp.hibernate;

import java.io.Serializable;

import javax.enterprise.context.Dependent;

import br.com.dwerp.dao.DAOFuncao;
import br.com.dwerp.entidade.Funcao;
import br.com.dwerp.hibernate.generico.DAOGenericoHibernate;

@Dependent
public class HibernateFuncao extends DAOGenericoHibernate<Funcao> implements DAOFuncao,Serializable {
	private static final long serialVersionUID = 1L;
	
	public HibernateFuncao(){
		super(Funcao.class);
	}


}
