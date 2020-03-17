package br.com.dwerp.hibernate;

import java.io.Serializable;

import javax.enterprise.context.Dependent;

import br.com.dwerp.dao.DAOPessoa;
import br.com.dwerp.entidade.Cliente;

@Dependent
public class HibernatePessoa extends DAOGenericoHibernate<Cliente> implements DAOPessoa,Serializable {
	private static final long serialVersionUID = 1L;
	
	public HibernatePessoa(){
		super(Cliente.class);
	}


}
