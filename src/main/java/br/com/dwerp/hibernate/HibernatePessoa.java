package br.com.dwerp.hibernate;

import java.io.Serializable;

import javax.enterprise.context.Dependent;

import br.com.dwerp.dao.DAOPessoa;
import br.com.dwerp.entidade.Pessoa;

@Dependent
public class HibernatePessoa extends DAOGenericoHibernate<Pessoa> implements DAOPessoa,Serializable {
	private static final long serialVersionUID = 1L;
	
	public HibernatePessoa(){
		super(Pessoa.class);
	}


}
