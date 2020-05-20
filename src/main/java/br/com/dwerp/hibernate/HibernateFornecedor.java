package br.com.dwerp.hibernate;

import java.io.Serializable;

import javax.enterprise.context.Dependent;

import br.com.dwerp.dao.DAOFornecedor;
import br.com.dwerp.entidade.Fornecedor;
import br.com.dwerp.hibernate.generico.DAOGenericoHibernate;

@Dependent
public class HibernateFornecedor extends DAOGenericoHibernate<Fornecedor> implements DAOFornecedor,Serializable {
	private static final long serialVersionUID = 1L;
	
	public HibernateFornecedor(){
		super(Fornecedor.class);
	}


}
